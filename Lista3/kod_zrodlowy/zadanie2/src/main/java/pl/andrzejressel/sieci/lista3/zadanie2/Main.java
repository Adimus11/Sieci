package pl.andrzejressel.sieci.lista3.zadanie2;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Cable c = new Cable();

        Host host1 = new Host1(c);
        Host host2 = new Host2(c);
        Host host3 = new Host3(c);

        host1.start();
        host2.start();
        host3.start();

        host1.join();
        host2.join();
        host3.join();

        System.out.println(c.toString());

    }

}
