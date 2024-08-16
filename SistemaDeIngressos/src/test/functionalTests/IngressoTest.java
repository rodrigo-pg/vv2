package test.functionalTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import main.java.enums.StatusIngresso;
import main.java.enums.TipoIngresso;
import main.java.model.Ingresso;

public class IngressoTest {
	
	private Ingresso ingressoNormal = new Ingresso(1, TipoIngresso.NORMAL, 10.0);
    private Ingresso ingressoVip = new Ingresso(2, TipoIngresso.VIP, 10.0);
    private Ingresso ingressoMeiaEntrada = new Ingresso(3, TipoIngresso.MEIA_ENTRADA, 10.0);
	
    // Técnica: Análise de Valores Limites : testes para verificar o desconto no ingresso dependendo do seu tipo (VIP, NORMAL e MEIA_ENTRADA)
	@Test
    @DisplayName("Desconto negativo deve resultar em erro")
    public void testeDescontoCaso12() {
        try {
            this.ingressoNormal.aplicarDesconto(-1);
            fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Desconto não pode ser maior que 25% e menor que 0%", e.getMessage());
        }
    }

    @Test
    @DisplayName("Desconto de 0% para ingresso VIP deve ser bem-sucedido")
    public void testeDescontoCaso13() {
    	this.ingressoVip.aplicarDesconto(0);
    }

    @Test
    @DisplayName("Desconto de 0% para ingresso NORMAL deve ser bem-sucedido")
    public void testeDescontoCaso14() {
    	this.ingressoNormal.aplicarDesconto(0);
    }

    @Test
    @DisplayName("Desconto aplicado para ingresso MEIA_ENTRADA deve resultar em erro")
    public void testeDescontoCaso15() {
        try {
        	this.ingressoMeiaEntrada.aplicarDesconto(0);
            fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Desconto não pode ser maior que 25% e menor que 0%", e.getMessage());
        }
    }

    @Test
    @DisplayName("Desconto de 14% aplicado para ingresso VIP deve ser bem-sucedido")
    public void testeDescontoCaso16() {
    	this.ingressoVip.aplicarDesconto(14);
    }

    @Test
    @DisplayName("Desconto de 14% aplicado para ingresso NORMAL deve ser bem-sucedido")
    public void testeDescontoCaso17() {
    	this.ingressoNormal.aplicarDesconto(14);
    }

    @Test
    @DisplayName("Desconto de 25% aplicado para VIP deve ser bem-sucedido")
    public void testeDescontoCaso18() {
    	this.ingressoVip.aplicarDesconto(25);
    }

    @Test
    @DisplayName("Desconto de 25% aplicado para ingresso NORMAL deve ser bem-sucedido")
    public void testeDescontoCaso19() {
    	this.ingressoNormal.aplicarDesconto(25);
    }

    @Test
    @DisplayName("Desconto de 26% aplicado para ingresso VIP deve resultar em erro")
    public void testeDescontoCaso20() {
        try {
        	this.ingressoVip.aplicarDesconto(26);
            fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Desconto não pode ser maior que 25% e menor que 0%", e.getMessage());
        }
    }

    @Test
    @DisplayName("Desconto de 26% aplicado para ingresso NORMAL deve resultar em erro")
    public void testeDescontoCaso21() {
        try {
        	this.ingressoNormal.aplicarDesconto(26);
            fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Desconto não pode ser maior que 25% e menor que 0%", e.getMessage());
        }
    }
    
    
    // Técnica: Partições de Equivalência: testes para verificação dos Preços dos Ingressos
    @Test
    @DisplayName("Preço do ingresso NORMAL deve ser igual ao preço de referência de R$ 10,00")
    public void testePrecoNormal() {

        assertEquals(10.00, this.ingressoNormal.getPrecoComDesconto(), 0.01);
    }

    @Test
    @DisplayName("Preço do ingresso VIP deve ser o dobro do preço de referência NORMAL de R$ 10,00")
    public void testePrecoVIP() {
        assertEquals(20.00, this.ingressoVip.getPrecoComDesconto(), 0.01);
    }

    @Test
    @DisplayName("Preço do ingresso MEIA_ENTRADA deve ser metade do preço de referência NORMAL de R$ 10,00")
    public void testePrecoMeiaEntrada() {
        assertEquals(5.00, this.ingressoMeiaEntrada.getPrecoComDesconto(), 0.01);
    }

    
    // Técnica: Tabelas de Decisão: testes de criação do ingresso
    @Test
    @DisplayName("Regra 1: Ingresso criado com id válido, tipo válido e status informado")
    public void testIngressoCriadoComIdETipoValidosEStatusInformado() {
        new Ingresso(1, TipoIngresso.VIP, StatusIngresso.VENDIDO, 10);
    }

    @Test
    @DisplayName("Regra 2: Ingresso criado com id válido, tipo válido e status não informado")
    public void testIngressoCriadoComIdETipoValidosSemStatusInformado() {
        new Ingresso(2, TipoIngresso.NORMAL, 10);
    }

    @Test
    @DisplayName("Regra 3: Ingresso não criado com id inválido")
    public void testIngressoNaoCriadoComIdInvalido() {
        try {
        	new Ingresso(-1, TipoIngresso.NORMAL, 10);
            fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("ID inválido", e.getMessage());
        }
    }

    @Test
    @DisplayName("Regra 4: Ingresso não criado com tipo inválido")
    public void testIngressoNaoCriadoComTipoInvalido() {
        try {
        	new Ingresso(1, null,10);
            fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Tipo de ingresso inválido", e.getMessage());
        }
    }
}
