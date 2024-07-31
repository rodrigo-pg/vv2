package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Ingresso;
import main.LoteIngressos;
import main.Relatorio;
import main.Show;
import main.enums.StatusFinanceiro;
import main.enums.TipoIngresso;


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
        assertEquals(1000.0, show.getCache());
        assertEquals(2000.0, show.getDespesasInfraestrutura());
        assertNull(show.getLoteIngressos());
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
