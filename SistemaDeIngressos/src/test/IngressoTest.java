package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.ErrorException;
import main.Ingresso;
import main.enums.StatusIngresso;
import main.enums.TipoIngresso;

public class IngressoTest {
	
	private Ingresso ingressoNormal;
    private Ingresso ingressoVip;
    private Ingresso ingressoMeiaEntrada;
	
	@BeforeEach
    void setUp() {
		ingressoNormal = new Ingresso(1, TipoIngresso.NORMAL, 10.0);
		ingressoVip = new Ingresso(2, TipoIngresso.VIP, 10.0);
        ingressoMeiaEntrada = new Ingresso(3, TipoIngresso.MEIA_ENTRADA, 10.0);
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
        
        ingressoMeiaEntrada.marcarComoVendido();
        assertEquals(StatusIngresso.VENDIDO, ingressoMeiaEntrada.getStatus());
    }
	
	@Test
    public void testPrecoIngressoNormal() {
        assertEquals(10.0, ingressoNormal.getPreco(), 0.1);
    }
	
	@Test
    public void testPrecoIngressoVip() {
        assertEquals(20.0, ingressoVip.getPreco(), 0.1);
    }

    @Test
    public void testPrecoIngressoMeiaEntrada() {
        assertEquals(5.0, ingressoMeiaEntrada.getPreco(), 0.1);
    }
    
    @Test
    public void testPrecoIngressoNormalComDesconto() {
    	ingressoNormal.aplicarDesconto(10);
    	assertEquals(9.0, ingressoNormal.getPrecoComDesconto(), 0.1);
    }
    
    @Test
    public void testPrecoIngressoVipComDesconto() {
    	ingressoVip.aplicarDesconto(10);
    	assertEquals(18.0, ingressoVip.getPrecoComDesconto(), 0.1);
    }
    
    @Test
    public void testPrecoIngressoMeiaEntradaComDesconto() {
    	ingressoMeiaEntrada.aplicarDesconto(10);
    	assertEquals(5.0, ingressoMeiaEntrada.getPrecoComDesconto(), 0.1);
    }
    
    @Test
    public void testDescontoNoLimite() {
    	ingressoNormal.aplicarDesconto(25);
    	ingressoVip.aplicarDesconto(25);
    	ingressoMeiaEntrada.aplicarDesconto(25);
    	
    	assertEquals(7.5, ingressoNormal.getPrecoComDesconto(), 0.1);
    	assertEquals(15.0, ingressoVip.getPrecoComDesconto(), 0.1);
    	assertEquals(5.0, ingressoMeiaEntrada.getPrecoComDesconto(), 0.1);
    }
    
    @Test
    public void testAplicarDescontoAcimaDoLimite() {
    	try {
    		ingressoNormal.aplicarDesconto(26);
            fail("Esperava a exceção ErrorException");
        } catch (ErrorException e) {
            assertEquals("Desconto não pode ser maior que 25%", e.getMessage());
        }
    }
	

}
