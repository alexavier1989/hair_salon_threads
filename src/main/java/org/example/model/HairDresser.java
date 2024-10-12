package org.example.model;

import java.util.Random;

public class HairDresser implements Runnable{

    private final HairSalon hairSalon;
    private final String hairDresserName;


    public HairDresser(HairSalon hairSalon, String hairDresserName) {
        this.hairSalon = hairSalon;
        this.hairDresserName = hairDresserName;
    }


    @Override
    public void run() {
        Random random = new Random();
        try {
            while (true){
                Customer client = hairSalon.getCustomer();
                System.out.println("START==> " + hairDresserName + " begins to attend to " + client.getName());
                int executionTime = random.nextInt(10) + 1;
                Thread.sleep(executionTime * 1000);
                System.out.println("END<== " + hairDresserName + " has finished attending to " + client.getName() + " by " + executionTime + " seconds.");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
