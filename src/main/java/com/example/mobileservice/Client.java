package com.example.mobileservice;

import com.example.mobileservice.mobile.*;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Client {

    private final static AtomicLong counter = new AtomicLong();
    private final static MobileFactory mobileFactory = new MobileFactory();
    private final long id = counter.incrementAndGet();
    private final Mobile mobile;
    private MobileService service;

    public Client(MobileService service) {
        this.service = service;
        this.mobile = mobileFactory.newRandomMobile();
    }

    public void log(String message) {
        System.out.println("Client #" + id + ": " + message);
    }

    public void sendMobileToService() {
        log("Sending in mobile: " + mobile);
        WorkSheet workSheet = service.sendIn(mobile);
        WorkSheet.Status lastKnownStatus = workSheet.status;
        while (lastKnownStatus != WorkSheet.Status.FINISHED) {
            while (workSheet.status == lastKnownStatus) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            log("Status changed to " + workSheet.status);
            lastKnownStatus = workSheet.status;
        }
    }

}
