package pl.andrzejressel.sieci.lista4;

public class Main {

    public static void main(String[] args)
            throws Exception {

        if (args.length == 0) {
            printUsage();
            System.exit(1);
        }

        String type = args[0];

        switch (type) {

            case "receiver":
                new Z2Forwarder(Integer.parseInt(args[1]), Integer.parseInt(args[2])).start();
                break;

            case "sender":
                new Z2Sender(Integer.parseInt(args[1]), Integer.parseInt(args[2])).start();
                break;

            case "forwarder":
                new Z2Forwarder(Integer.parseInt(args[1]), Integer.parseInt(args[2])).start();
                break;

            case "quickstart":
                new Z2Receiver(6002, 6003).start();
                new Z2Forwarder(6001, 6002).start();
                new Z2Forwarder(6003, 6000).start();
                new Z2Sender(6000, 6001).start();

                break;

            default:
                printUsage();
                break;

        }


    }

    private static void printUsage() {
        System.out.println("USAGE: <receiver/sender/forwarder/quickstart> <port1> <port2>");

    }

}
