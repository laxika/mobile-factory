package com.example.mobileservice;

public class SendMobileToServiceAndPollStatusTask implements Runnable {

    private Client client;

    public SendMobileToServiceAndPollStatusTask(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        client.sendMobileToService();
    }

}
