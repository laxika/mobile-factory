package com.example.mobileservice;

/**
 * @author vrg
 */
public class Order {

    private long id;
    private PartType type;
    private int quantity;
    private Status status;

    public Order(long id) {
        this.id = id;
        this.status = Status.WAITING_FOR_ORDER;
    }

    public long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status newStatus) {
        switch (newStatus) {
            case ORDERED:
                if (status != Status.WAITING_FOR_ORDER) {
                    throw new IllegalStateException("Illegal state transition: " + status + " -> " + newStatus);
                }
                break;
            case READY_FOR_SHIPMENT:
                if (status != Status.ORDERED) {
                    throw new IllegalStateException("Illegal state transition: " + status + " -> " + newStatus);
                }
                break;
            case SHIPPED:
                if (status != Status.READY_FOR_SHIPMENT) {
                    throw new IllegalStateException("Illegal state transition: " + status + " -> " + newStatus);
                }
                break;
            default:
                throw new IllegalStateException("Illegal state transition: " + status + " -> " + newStatus);
        }
        status = newStatus;
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
