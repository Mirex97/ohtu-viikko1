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
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
        varasto = new Varasto(-5);
        assertEquals("Tilavuus ei nolla!", 0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuusJaSaldo() {
        /*Testataan virheellisillä arvoilla*/
        varasto = new Varasto(-10, -15);
        assertEquals("Tilavuus ei nolla!", 0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals("Saldo ei nolla!", 0, varasto.getSaldo(), vertailuTarkkuus);
        varasto = new Varasto (10, 15);
        assertEquals("Saldo ei korjannut ylimääräisiä saldoja!", 10, varasto.getSaldo(), vertailuTarkkuus);
        /*Testataan oikeilla arvoilla*/
        varasto = new Varasto(10, 5);
        assertEquals("Tilavuus ei oikein!", 10, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals("Ei ollut saldo oikein!", 5, varasto.getSaldo(), vertailuTarkkuus);

    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void vaaraLisaysEiLisaaSaldoa() {
        varasto.lisaaVarastoon(-5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        varasto.lisaaVarastoon(100);
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
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
    public void ottaminenEdesToimii() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(-2);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
        double maara = varasto.otaVarastosta(10);
        assertEquals(varasto.getSaldo(), 0, vertailuTarkkuus);
        assertEquals(8, maara, vertailuTarkkuus);
    }
    
    @Test
    public void merkkijonoEsitysOikein() {
        assertEquals("Esitys ei ollut sama!",varasto.toString(), "saldo = " + 0.0 + ", vielä tilaa " + 10.0);
    }

}
