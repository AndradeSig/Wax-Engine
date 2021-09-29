package org.wax.engine.IO;

public class WaxThread {

    private Thread thread;
    private int ID;
    private String name;

    public WaxThread(String name, int ID)
    {
        this.ID = ID;
        this.name = name;
    }

    public void enable(Runnable target)
    {
        thread = new Thread(target);
        thread.start();
    }

    public void stop()
    {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void interval(int time)
    {
        try {
            thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // --------------- GETS ---------------

    public String getName()
    {
        return name;
    }

    public int getID()
    {
        return ID;
    }
}
