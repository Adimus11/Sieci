package pl.andrzejressel.sieci.lista3.zadanie2;

public class Host extends Thread {

    private Cable cab;
    private String data;
    private Integer id;

    public Host(Cable c, String data, int id) {
        this.cab = c;
        this.data = data;
        this.id = id;
    }


    @Override
    public void run() {

        int wait = 2;
        int waitNumber = 0;
        int oldSize;

        while (waitNumber < 10) {

            wait *= 2;

            if (wait > 512) {
                wait = 512;
                waitNumber++;
            }

            try {
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean jammed = false;

            while (!cab.canTransmit()) {
            }

            for (char characker : ("[START" + id.toString() + "]" + data + "[END" + id.toString() + "]").toCharArray()) {

                oldSize = cab.length();

                cab.add(String.valueOf(characker));

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //Czy jest zaburzenie
                if (oldSize + 1 != cab.length()) {
                    jammed = true;
                    cab.jam();
                    break;
                }

            }

            //Udało się wysłać całość
            if (!jammed) {
                break;
            }

        }

    }

}
