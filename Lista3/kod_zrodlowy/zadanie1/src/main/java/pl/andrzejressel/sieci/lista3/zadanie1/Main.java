package pl.andrzejressel.sieci.lista3.zadanie1;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import pl.andrzejressel.sieci.lista3.zadanie1.action.Check;
import pl.andrzejressel.sieci.lista3.zadanie1.action.Decode;
import pl.andrzejressel.sieci.lista3.zadanie1.action.Encode;
import pl.andrzejressel.sieci.lista3.zadanie1.crc.CRC;
import pl.andrzejressel.sieci.lista3.zadanie1.crc.CRC8;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {

        if (args.length <= 1) {
            printUsage();
            return;
        }

        CRC crc = new CRC8();

        String data = Files.toString(new File(args[1]), Charsets.UTF_8);

        switch (args[0]) {

            case "encode":
                String encode = Encode.encodeData(data, 8, crc);
                Files.write(encode, new File(args[2]), Charsets.UTF_8);
                break;

            case "decode":
                String decode = Decode.decodeData(data, crc);
                Files.write(decode, new File(args[2]), Charsets.UTF_8);
                break;

            case "check":
                String frame = Check.checkData(data, crc);

                if (frame != null) {
                    System.out.println("Ramka " + frame + " jest nieprawidłowa");
                } else {
                    System.out.println("Ramka jest prawidłowa");
                }

        }


    }

    private static void printUsage() {
        System.out.println("USAGE: <encode/decode/check> <Plik z danymi> <Plik wynikowy>");
    }

}
