package org.example.model;

import java.util.LinkedList;
import java.util.Queue;

public class HairSalon {
    private static final int HAIR_DRESSERS_NUM = 4;
    private static final int CLIENTS_NUM = 10;

    private Queue<Customer> customerQueue =  new LinkedList<>();
    Object blocker = new Object();
    private Thread[] hairDressers;

    public HairSalon() {
        hairDressers = new Thread[HAIR_DRESSERS_NUM];
        for (int i = 0; i < HAIR_DRESSERS_NUM; i++) {
            hairDressers[i] = new Thread( new HairDresser(this, "hairDresser" + ( i + 1 )));
        }
    }

    public void init(){
        for (Thread hairDresser : hairDressers){
            hairDresser.start();
        }

        for (int i = 0; i <= CLIENTS_NUM; i++) {
            Customer customer = new Customer("Customer" + i);
            addCustomer(customer);
        }
    }

    public void addCustomer(Customer c){
        synchronized (blocker) {
            customerQueue.offer(c);
            blocker.notify();
        }
    }

    public Customer getCustomer() throws InterruptedException {
        synchronized (blocker){
            while (customerQueue.isEmpty()){
                blocker.wait();
            }
            return customerQueue.poll();
        }
    }
}