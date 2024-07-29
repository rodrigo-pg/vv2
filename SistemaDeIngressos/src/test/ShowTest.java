package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Show;


public class ShowTest {
	private Show show;
	
	@BeforeEach
    void setUp() {
		show = new Show();
        show.setData("29/07/2024");
        show.setArtista("Artista");
        show.setCache(1000.0);
        show.setDespesasInfraestrutura(2000.0);
        show.setDataEspecial(true);
    }
	
	@Test
	void testCriacaoShow() {
		assertEquals("29/07/2024", show.getData());
		
		
	}

}
