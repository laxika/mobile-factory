package com.example.mobileservice.supplier;

import com.example.mobileservice.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Supplier {

    private static final Logger logger = LogManager.getLogger(Supplier.class);
    private final TaskScheduler taskScheduler;

    public Supplier(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    public Long orderPart(Order order) {
        logger.info("New order: " + order);
        taskScheduler.scheduleTaskToRandomTime(new SatisfyOrderNeedsTask(order), 1, 10, TimeUnit.SECONDS);
        order.setStatus(Order.Status.ORDERED);
        return order.getId();
    }

    public boolean isReadyForShipment(Order order) {
        return order.getStatus() == Order.Status.READY_FOR_SHIPMENT;
    }

    public Order shipOrder(Order order) {
        try {
            order.setStatus(Order.Status.SHIPPED);
        } catch (IllegalStateException ex) {
            throw new IllegalArgumentException("Order should be in READY_FOR_SHIPMENT state in order to be shipped successfully.", ex);
        }
        System.out.println("Order shipped: " + order);
        return order;
    }

}
