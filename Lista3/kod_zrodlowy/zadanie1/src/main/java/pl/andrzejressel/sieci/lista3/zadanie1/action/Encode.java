package pl.andrzejressel.sieci.lista3.zadanie1.action;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import pl.andrzejressel.sieci.lista3.zadanie1.crc.CRC;

public class Encode {

    public static String encodeData(String data, int frameSize, CRC crcType) {

        String flag = "01111110";

        StringBuilder toReturn = new StringBuilder();

        Iterable<String> result = Splitter.fixedLength(frameSize).split(data);
        String[] frames = Iterables.toArray(result, String.class);


        for (String frame : frames) {

            String framePart = "";

            //Obliczanie CRC
            String crc = crcType.encode(frame);

            framePart += frame;
            framePart += crc;

            //Rozpychanie bitów
            framePart = framePart.replace("11111", "111110");

            toReturn.append(flag).append(framePart);


        }


        return toReturn.toString();
    }


}
