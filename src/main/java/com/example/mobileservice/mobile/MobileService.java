package com.example.mobileservice.mobile;

import com.example.mobileservice.*;
import com.example.mobileservice.supplier.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class MobileService {

    private static final Logger logger = LogManager.getLogger(MobileService.class);
    private static final AtomicLong counter = new AtomicLong();

    private HashMap<PartType, Integer> stock = new HashMap<>();
    private List<WorkSheet> worksheetsToProcess = new ArrayList<>();

    private Map<PartType, Order> orderIdByPartType = new HashMap<>();
    private Supplier supplier;

    public MobileService(Supplier supplier) {
        this.supplier = supplier;
    }

    public synchronized WorkSheet sendIn(Mobile mobile) {
        WorkSheet worksheet = createWorkSheet(mobile);
        collectFailingParts(worksheet);
        worksheetsToProcess.add(worksheet);
        return worksheet;
    }

    public synchronized boolean replaceAllPossibleFailingParts(WorkSheet ws) {
        if (ws.partsToReplace == null) {
            //nothing to replace
            ws.status = WorkSheet.Status.FINISHED;
            return true;
        }
        for (Iterator<Part> it = ws.partsToReplace.iterator(); it.hasNext(); ) {
            Part partToReplace = it.next();
            PartType type = partToReplace.type;
            Integer inStockQuantity = stock.get(type);
            if (inStockQuantity != null && inStockQuantity > 0) {
                inStockQuantity = inStockQuantity - 1;
                stock.put(type, inStockQuantity);
                if (ws.replacedParts == null) {
                    ws.replacedParts = new ArrayList<>();
                }
                ws.replacedParts.add(new ReplacedPart(type, partToReplace.name));
                it.remove();
            } else {
                addOrderForMissingPart(type);
                ws.status = WorkSheet.Status.WAITING_FOR_PARTS;
            }
        }

        if (ws.partsToReplace.isEmpty()) {
            ws.status = WorkSheet.Status.FINISHED;
            return true;
        } else {
            return false;
        }
    }

    public synchronized void addOrderForMissingPart(PartType type) {
        if (type == null) {
            throw new IllegalArgumentException("partType cannot be null");
        }

        if (!orderIdByPartType.containsKey(type)) {
            Order order = new Order(counter.incrementAndGet());
            order.setType(type);
            order.setQuantity(5);

            orderIdByPartType.put(type, order);
        }
    }

    public synchronized void processWorksheets() {

        for (Iterator<WorkSheet> it = worksheetsToProcess.iterator(); it.hasNext(); ) {
            WorkSheet workSheet = it.next();
            boolean finished = replaceAllPossibleFailingParts(workSheet);
            if (finished) {
                it.remove();
            }
        }
    }

    public synchronized WorkSheet createWorkSheet(Mobile mobile) {
        WorkSheet ws = new WorkSheet();
        ws.mobile = mobile;
        ws.receivedOn = new Date();
        ws.status = WorkSheet.Status.RECEIVED;
        return ws;
    }

    public synchronized void collectFailingParts(WorkSheet sheet) {

        sheet.status = WorkSheet.Status.STARTED_TO_REPAIR;

        Mobile mobile = sheet.mobile;

        if (mobile.getDisplay().failing) {
            addPartToReplace(sheet, mobile.getDisplay());
        }
        if (mobile.getKeyboard() != null && mobile.getKeyboard().failing) {
            addPartToReplace(sheet, mobile.getKeyboard());
        }
        if (mobile.getMicrophone().failing) {
            addPartToReplace(sheet, mobile.getMicrophone());
        }
        if (mobile.getMotherBoard().failing) {
            addPartToReplace(sheet, mobile.getMotherBoard());
        }
        if (mobile.getPowerSwitch().failing) {
            addPartToReplace(sheet, mobile.getPowerSwitch());
        }
        if (mobile.getSpeaker().failing) {
            addPartToReplace(sheet, mobile.getSpeaker());
        }
        if (mobile.getVolumeButtons().failing) {
            addPartToReplace(sheet, mobile.getVolumeButtons());
        }
    }

    public synchronized void addPartToReplace(WorkSheet sheet, Part part) {
        if (sheet.partsToReplace == null) {
            sheet.partsToReplace = new ArrayList<>();
        }
        sheet.partsToReplace.add(part);
    }

    public synchronized void orderParts() {
        if (!orderIdByPartType.isEmpty()) {
            for (Map.Entry<PartType, Order> entry : orderIdByPartType.entrySet()) {
                Order order = entry.getValue();

                if(order != null && order.getStatus() == Order.Status.WAITING_FOR_ORDER) {
                    supplier.orderPart(entry.getValue());
                }
            }
        }
    }

    public synchronized void pollSupplier() {
        if (!orderIdByPartType.isEmpty()) {
            List<Order> orderIds = new ArrayList<>(orderIdByPartType.values());
            for (Order order : orderIds) {
                if (order != null) {
                    if (supplier.isOrderArrived(order)) {
                        logger.info("Order shipped: " + order);

                        order.nextStatus();

                        stock.put(order.getType(), order.getQuantity());
                        orderIdByPartType.remove(order.getType());
                    }
                }
            }
        }
    }
}
