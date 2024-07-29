package test;

import org.junit.jupiter.api.Test;

import main.Show;


public class ShowTest {
	
	@Test
	void testCriacaoShow() {
		Show show = new Show();
		show.setData("29/07/2024");
		show.setData("Artista teste");
		show.setCache(1000.0);
		show.setDespesasInfraestrutura(2000.0);
		show.setDataEspecial(true);
		
	}

}
