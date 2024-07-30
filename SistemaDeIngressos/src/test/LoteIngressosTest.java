package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import main.Ingresso;
import main.LoteIngressos;
import main.TipoIngresso;

public class LoteIngressosTest {
	private LoteIngressos lote;
	
	private Ingresso ingressoNormal;
	private Ingresso ingressoVip;
    private Ingresso ingressoMeiaEntrada;
	
	@BeforeEach
    void setUp() {
        lote = new LoteIngressos(1, 15.0);
       
        ingressoNormal = new Ingresso(1, TipoIngresso.NORMAL);
        ingressoVip = new Ingresso(2, TipoIngresso.VIP);
        ingressoMeiaEntrada = new Ingresso(3, TipoIngresso.MEIA_ENTRADA);
    }
	
	@Test
    void testAdicionarIngresso() {
		lote.adicionarIngresso(ingressoNormal);
        assertEquals(1, lote.getIngressos().size());
    }
	
	@Test
    void testAplicarDesconto() {
        lote.aplicarDesconto();

        assertEquals(17.0, ingressoVip.getPreco());
        assertEquals(8.5, ingressoNormal.getPreco());
        assertEquals(5.0, ingressoMeiaEntrada.getPreco());
    }

    @Test
    void testDescontoMaximo() {
        LoteIngressos loteMaxDesconto = new LoteIngressos(2, 25.0);
        Ingresso ingressoNormalMaxDesconto = new Ingresso(4, TipoIngresso.NORMAL);
        loteMaxDesconto.adicionarIngresso(ingressoNormalMaxDesconto);

        loteMaxDesconto.aplicarDesconto();
        assertEquals(7.5, ingressoNormalMaxDesconto.getPreco());
    }

}
