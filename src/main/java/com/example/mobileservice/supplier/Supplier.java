package com.example.mobileservice.supplier;

import com.example.mobileservice.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Supplier {

    private Map<Long, Order> orderById = new HashMap<>();

    private final TaskScheduler taskScheduler;

    public Supplier(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    public Long orderPart(Order order) {
        orderById.put(order.getId(), order);
        System.out.println("New order: " + order);
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
