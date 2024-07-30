package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Ingresso;
import main.StatusIngresso;
import main.TipoIngresso;

public class IngressoTest {
	
	private Ingresso ingressoNormal;
    private Ingresso ingressoVip;
    private Ingresso ingressoMeiaEntrada;
	
	@BeforeEach
    void setUp() {
		ingressoNormal = new Ingresso(1, TipoIngresso.NORMAL);
		ingressoVip = new Ingresso(2, TipoIngresso.VIP);
        ingressoMeiaEntrada = new Ingresso(3, TipoIngresso.MEIA_ENTRADA);
    }
	
	@Test
	void testCriacaoIngresso() {
		assertEquals(1, ingressoNormal.getId());
		assertEquals(TipoIngresso.NORMAL, ingressoNormal.getTipo());
		assertEquals(2, ingressoVip.getId());
		assertEquals(TipoIngresso.VIP, ingressoVip.getTipo());
		assertEquals(3, ingressoMeiaEntrada.getId());
		assertEquals(TipoIngresso.MEIA_ENTRADA, ingressoMeiaEntrada.getTipo());
        
	}
	
	@Test
	void testStatusInicial() {
        assertEquals(StatusIngresso.NAO_VENDIDO, ingressoNormal.getStatus());
        assertEquals(StatusIngresso.NAO_VENDIDO, ingressoVip.getStatus());
        assertEquals(StatusIngresso.NAO_VENDIDO, ingressoMeiaEntrada.getStatus());
    }
	
	@Test
    void testMarcarComoVendido() {
        ingressoNormal.marcarComoVendido();
        assertEquals(StatusIngresso.VENDIDO, ingressoNormal.getStatus());

        ingressoVip.marcarComoVendido();
        assertEquals(StatusIngresso.VENDIDO, ingressoVip.getStatus());
    }
	
	@Test
    public void testPrecoIngressoNormal() {
        assertEquals(10.0, ingressoNormal.getPreco());
    }
	
	@Test
    public void testPrecoIngressoVip() {
        assertEquals(20.0, ingressoVip.getPreco());
    }

    @Test
    public void testPrecoIngressoMeiaEntrada() {
        assertEquals(5.0, ingressoMeiaEntrada.getPreco());
    }
	

}
