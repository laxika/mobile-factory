package com.example.mobileservice.supplier;

import com.example.mobileservice.Order;

public class SatisfyOrderNeedsTask implements Runnable {
    private Order order;

    public SatisfyOrderNeedsTask(Order order) {
        this.order = order;
    }

    @Override
    public void run() {
        order.nextStatus();
    }

}
