package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Show;


public class ShowTest {
	private Show show;
	
	@BeforeEach
    void setUp() {
		show = new Show("29/07/2024", "Artista Teste", 1000.0, 2000.0, new ArrayList<>(), true);
    }
	
	@Test
	void testCriacaoShow() {
		assertEquals("29/07/2024", show.getData());
		assertEquals("Artista Teste", show.getArtista());
        assertEquals(1000.0, show.getCache());
        assertEquals(2000.0, show.getDespesasInfraestrutura());
        assertNull(show.getLoteIngressos());
        assertTrue(show.isDataEspecial());	
	}
	
	

}
