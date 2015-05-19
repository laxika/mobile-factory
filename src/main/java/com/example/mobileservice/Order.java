package com.example.mobileservice;

public class Order {

    private long id;
    private PartType type;
    private int quantity;
    private Status status;

    public Order(long id) {
        this.id = id;
        this.status = Status.WAITING_FOR_ORDER;
    }

    public Status getStatus() {
        return status;
    }

    public void nextStatus() {
        if (status.ordinal() == Status.values().length) {
            throw new IllegalStateException("Already reached the last status step.");
        }

        status = Status.values()[status.ordinal() + 1];
    }

    public PartType getType() {
        return type;
    }

    public void setType(PartType type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", type=" + type + ", quantity=" + quantity + ", status=" + status + '}';
    }

    public enum Status {
        WAITING_FOR_ORDER,
        ORDERED,
        READY_FOR_SHIPMENT,
        SHIPPED
    }
}
