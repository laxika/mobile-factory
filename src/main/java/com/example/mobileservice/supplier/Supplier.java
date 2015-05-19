package com.example.mobileservice.supplier;

import com.example.mobileservice.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Supplier {

    private static final Logger logger = LogManager.getLogger(Supplier.class);
    private final TaskScheduler taskScheduler;

    public Supplier(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    public void orderPart(Order order) {
        logger.info("New order: " + order);

        taskScheduler.scheduleSatisfyOrderNeedsTask(new SatisfyOrderNeedsTask(order));
        order.nextStatus();
    }

    public boolean isReadyForShipment(Order order) {
        return order.getStatus() == Order.Status.READY_FOR_SHIPMENT;
    }

    public void shipOrder(Order order) {
        logger.info("Order shipped: " + order);

        order.nextStatus();
    }

}
