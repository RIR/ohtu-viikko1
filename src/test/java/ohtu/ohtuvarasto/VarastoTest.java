package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    Varasto varasto3;
    Varasto varasto4;
    Varasto varasto5;
    Varasto varasto6;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2 = new Varasto(0);
        varasto3 = new Varasto(15, -1);
        varasto4 = new Varasto(15, 3);
        varasto5 = new Varasto(15, 20);
        varasto6 = new Varasto(-2, 20);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaaminenLiikaaKasvattaaSaldonMaksimiksi() {
        varasto.lisaaVarastoon(20);

        // varaston saldo pitäisi olla sama kuin tilavuus eli 10
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
     @Test
    public void lisaaminenNegatiivisellaArvollaPoistuuMetodistaSuoraan() {
        varasto.lisaaVarastoon(-20);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenLiikaaNollaaSaldon() {
        varasto.otaVarastosta(20);

        // varaston saldo pitäisi olla sama kuin tilavuus eli 10
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenNegatiivisellaArvollaEiVaikutaSaldoonJaPalauttaaNollanDoublena() {
        double otettu = varasto.otaVarastosta(-20);
        assertEquals(0.0, otettu, vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoHaluttaessaKayttokelvottomanVaraston() {
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuusKunKaksiParametria() {
        assertEquals(15, varasto3.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaSaldoNollaKunAnnettuMiinusSaldo() {
        assertEquals(0, varasto3.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaSaldoksiAnnettuSaldo() {
        assertEquals(3, varasto4.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaSaldoksiTilavuusKunAnnettuSaldoIsompiKuinTilavuus() {
        assertEquals(15, varasto5.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uusiVarastoNegatiivisellaTilavuudellaLuoVarastonNollaTilavuudella() {
        assertEquals(0, varasto6.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void stringToStringPalauttaaMerkkiJonoesityksenOikeillaArvoilla() {
        String varastoString = "saldo = " + 0.5 + ", vielä tilaa " + 10.0;
        assertEquals(varastoString, varasto.toString());
    }
}
