package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.enums.StatusFinanceiro;
import main.java.enums.TipoIngresso;
import main.java.model.Ingresso;
import main.java.model.LoteIngressos;
import main.java.model.Relatorio;
import main.java.model.Show;


public class ShowTest {
	private Show show;
	private LoteIngressos lote;
	private List<Ingresso> ingressos;
	
	@BeforeEach
    void setUp() {
		ingressos = preparaLoteIngresso();
        lote = new LoteIngressos(1, ingressos, 15.0);
		show = new Show("29/07/2024", "Artista Teste", 1000.0, 2000.0, lote, true);
    }
	
	
	@Test
	void testCriacaoShow() {
		assertEquals("29/07/2024", show.getData());
		assertEquals("Artista Teste", show.getArtista());
        assertEquals(1000.0, show.getCache(), 0.1);
        assertEquals(2000.0, show.getDespesasInfraestrutura(), 0.1);
        assertEquals(lote, show.getLoteIngressos());
        assertTrue(show.isDataEspecial());	
	}
	
	
	@Test
	public void testGerarRelatorioBasico() {
		List<Ingresso> ingressos = preparaLoteIngresso();
		LoteIngressos lote = new LoteIngressos(1, ingressos, 15.0);
		lote.aplicarDesconto();
        
	    show.adicionarLote(lote);

	    Relatorio relatorio = show.gerarRelatorio();

	    assertEquals(100, relatorio.getNumeroIngressosVIP());
	    assertEquals(50, relatorio.getNumeroIngressosMeiaEntrada());
	    assertEquals(350, relatorio.getNumeroIngressosNormal());
	    assertEquals(1625.0, relatorio.getReceitaLiquida(), 0.01);
	    assertEquals(StatusFinanceiro.LUCRO, relatorio.getStatusFinanceiro());
	}
	
	@Test
	public void testGerarRelatorioComPrejuizo() {
		Show show2 = new Show("29/07/2024", "Artista Teste", 1000.0, 3500.0, lote, true);
		List<Ingresso> ingressos = preparaLoteIngresso();
		LoteIngressos lote = new LoteIngressos(1, ingressos, 15.0);
		lote.aplicarDesconto();
        
		show2.adicionarLote(lote);

	    Relatorio relatorio = show2.gerarRelatorio();

	    assertEquals(100, relatorio.getNumeroIngressosVIP());
	    assertEquals(50, relatorio.getNumeroIngressosMeiaEntrada());
	    assertEquals(350, relatorio.getNumeroIngressosNormal());
	    assertEquals(-100, relatorio.getReceitaLiquida(), 0.01);
	    assertEquals(StatusFinanceiro.PREJUÍZO, relatorio.getStatusFinanceiro());
	}
	
	@Test
	public void testGerarRelatorioEstavel() {
		Show show2 = new Show("29/07/2024", "Artista Teste", 1000.0, 3925.0, lote, false);
		List<Ingresso> ingressos = preparaLoteIngresso();
		LoteIngressos lote = new LoteIngressos(1, ingressos, 15.0);
		lote.aplicarDesconto();
        
		show2.adicionarLote(lote);

	    Relatorio relatorio = show2.gerarRelatorio();

	    assertEquals(100, relatorio.getNumeroIngressosVIP());
	    assertEquals(50, relatorio.getNumeroIngressosMeiaEntrada());
	    assertEquals(350, relatorio.getNumeroIngressosNormal());
	    assertEquals(0, relatorio.getReceitaLiquida(), 0.01);
	    assertEquals(StatusFinanceiro.ESTÁVEL, relatorio.getStatusFinanceiro());
	}
	
	private List<Ingresso> preparaLoteIngresso() {
		int totalIngressos = 500;

        List<Ingresso> ingressos = new ArrayList<>();
        for (int i = 0; i < totalIngressos; i++) {
            if (i < 100) {
                ingressos.add(new Ingresso(i, TipoIngresso.VIP, 10.0));
            } else if (i < 150) {
                ingressos.add(new Ingresso(i, TipoIngresso.MEIA_ENTRADA, 10.0));
            } else {
                ingressos.add(new Ingresso(i, TipoIngresso.NORMAL, 10.0));
            }
        }
        
        for(Ingresso ingresso: ingressos) {
        	ingresso.marcarComoVendido();
        }
        
        return ingressos;
		
	}
	
}
