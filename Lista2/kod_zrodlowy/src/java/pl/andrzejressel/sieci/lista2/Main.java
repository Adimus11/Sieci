package pl.andrzejressel.sieci.lista2;

import pl.andrzejressel.sieci.lista2.zadanie1.Zadanie1;
import pl.andrzejressel.sieci.lista2.zadanie2.Zadanie2;

public class Main {

    public static void main(String[] args) {

        if (args.length < 1 || args.length > 2) {
            printUsage();
            System.exit(1);
        }

        String zadanie = args[0];

        switch (zadanie) {

            case "zadanie1":
                new Zadanie1().start();
                break;

            case "zadanie2":
                new Zadanie2().start(args[1]);
                break;

            default:
                printUsage();
                System.exit(1);
                break;
        }


    }

    private static void printUsage() {
        System.out.println("USAGE: <zadanie1/zadanie2> <(gdy zadanie2) ścieżka do folderu z grafem>");

    }

}
