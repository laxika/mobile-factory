package com.example.mobileservice.supplier;

import com.example.mobileservice.Order;
import com.example.mobileservice.task.TaskScheduler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Supplier {

    private static final Logger logger = LogManager.getLogger(Supplier.class);

    private final TaskScheduler taskScheduler = new TaskScheduler();

    public void orderPart(final Order order) {
        logger.info("New order: " + order);

        taskScheduler.executeTask(order::nextStatus);

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
