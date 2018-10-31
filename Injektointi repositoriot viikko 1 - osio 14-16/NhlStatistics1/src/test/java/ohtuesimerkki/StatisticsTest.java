
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        @Override
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;
    
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp(){
        stats = new Statistics(readerStub);
    }  

    @After
    public void tearDown() {
    }

    @Test
    public void konsturktoriToimii() {
        assertTrue(stats != null);
    }
    
    @Test
    public void etsintaLoytaaPelaajanTaiEi() {
        assertTrue(stats.search("joku ukkeli") == null);
        assertTrue(stats.search("Kurri") != null);
    }
    
    @Test
    public void joukkueenPelaajatEiTyhjä() {
        
        assertTrue(stats.team("wat").isEmpty());
        List<Player> teami = stats.team("EDM");
        assertTrue(!teami.isEmpty());
        for (Player pelaaja : teami) {
            assertTrue("Pelaaja" + pelaaja.getName() + " ei kuulu joukkueeseen EDM!",pelaaja.getTeam() == "EDM");
        }
    }
    
    @Test
    public void topPisteetOikein() {
        List<Player> scorers = stats.topScorers(3);
        assertTrue("Ei ollut oikea määrä pelaajia listalla",scorers.size() == 4);
    }
    
}
