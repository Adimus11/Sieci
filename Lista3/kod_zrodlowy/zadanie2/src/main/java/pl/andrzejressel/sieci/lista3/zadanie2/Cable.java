package pl.andrzejressel.sieci.lista3.zadanie2;

import java.util.Random;

public class Cable {

    private StringBuilder data = new StringBuilder();

    Random generator = new Random();

    public int length() {
        return data.length();
    }

    public boolean canTransmit() {
        int size = data.length();
        try {
            Thread.sleep(generator.nextInt(40));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return size == data.length();
    }

    public void add(String s) {
        data.append(s);
    }

    public synchronized void jam() {
        data.append("[JAM]");
    }

    public synchronized void end() {
        data.append("[END]");
    }

    @Override
    public String toString() {
        return data.toString();
    }

}
