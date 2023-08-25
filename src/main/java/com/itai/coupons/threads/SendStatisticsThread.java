package com.itai.coupons.threads;

public class SendStatisticsThread extends Thread{
    private String text;
    public SendStatisticsThread(String text){
        this.text = text;
    }

    public void run(){
        // The following lines are a mock
        // it simulates sending the data to a statistics server

        // The 5 seconds sleep simulates the amount of time
        // it takes to send the request to the other save server and execute it there
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.text);
    }
}
