package com.example.mobileservice;

import com.example.mobileservice.supplier.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private final List<Client> clients = new ArrayList<>();
    private MobileService mobileService;
    private Supplier supplier;

    public Main() {
        supplier = new Supplier();
        mobileService = new MobileService(supplier);
        for (int i = 0; i < 100; i++) {
            Client client = new Client(mobileService);
            clients.add(client);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.sendMobilesToService();
        main.startWorkAtMobileService();
    }

    public void sendMobilesToService() {
        for (Client client : clients) {
            SendMobileToServiceAndPollStatusTask task = new SendMobileToServiceAndPollStatusTask(client);
            new Thread(task).start();
        }
    }

    public void startWorkAtMobileService() {
        while (true) {
            mobileService.pollSupplier();
            mobileService.processWorksheets();
            mobileService.orderParts();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
