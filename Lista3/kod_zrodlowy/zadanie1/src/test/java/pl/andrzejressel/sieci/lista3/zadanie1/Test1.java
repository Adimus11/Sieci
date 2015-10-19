package pl.andrzejressel.sieci.lista3.zadanie1;

import org.junit.Test;
import pl.andrzejressel.sieci.lista3.zadanie1.action.Check;
import pl.andrzejressel.sieci.lista3.zadanie1.action.Encode;
import pl.andrzejressel.sieci.lista3.zadanie1.crc.CRC;
import pl.andrzejressel.sieci.lista3.zadanie1.crc.CRC8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Test1 {

    CRC crc = new CRC8();

    @Test
    public void basicEncodeTests() {

        assertEquals(crc.encode("0"), "00000000");
        assertEquals(crc.encode("1"), "00110001");
        assertEquals(crc.encode("11"), "01010011");
    }

    @Test
    public void basicDecodeTests() {
        assertEquals(crc.decode("1010101" + crc.encode("1010101")), "1010101");
    }


    @Test
    public void encodeTest() {
        String test = Encode.encodeData("1111", 4, crc);
        assertEquals(test, "01111110111100101110");
    }

    @Test
    public void checkTest() {

        String toEncode = "101010111100011010110100101010100011";
        String encoded = Encode.encodeData(toEncode, 3, crc);

        assertTrue(Check.checkData(encoded, crc));

    }


}
