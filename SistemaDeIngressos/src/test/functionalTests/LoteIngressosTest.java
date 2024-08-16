package test.functionalTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import main.java.enums.TipoIngresso;
import main.java.model.Ingresso;
import main.java.model.LoteIngressos;

public class LoteIngressosTest {
	

	//Teste para verificar o Percentual de ingressos VIP, NORMAL e MEIA_ENTRADA
	@Test
    public void testePercentualIngressosCaso1() {
		List<Ingresso> ingressos = preparaLoteIngressos(19, 0, 0);
 
        try {
        	new LoteIngressos(1, ingressos);
        	fail("Deveria lançar uma exceção aqui");
        }catch (IllegalArgumentException e) {
            assertEquals("Distribuição de ingressos está incorreta", e.getMessage());
        }
    }
	
    @Test
    public void testePercentualIngressosCaso2() {
        List<Ingresso> ingressos = preparaLoteIngressos(20, 70, 10);
        assertDoesNotThrow(() -> new LoteIngressos(1, ingressos));
    }

    @Test
    public void testePercentualIngressosCaso3() {
        List<Ingresso> ingressos = preparaLoteIngressos(25, 65, 10);
        assertDoesNotThrow(() -> new LoteIngressos(1, ingressos));
    }

    @Test
    public void testePercentualIngressosCaso4() {
        List<Ingresso> ingressos = preparaLoteIngressos(30, 60, 10);
        assertDoesNotThrow(() -> new LoteIngressos(1, ingressos));
    }

    @Test
    public void testePercentualIngressosCaso5() {
        List<Ingresso> ingressos = preparaLoteIngressos(31, 0, 0);
        try {
            new LoteIngressos(1, ingressos);
            fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Distribuição de ingressos está incorreta", e.getMessage());
        }
    }

    @Test
    public void testePercentualIngressosCaso6() {
        List<Ingresso> ingressos = preparaLoteIngressos(0, 0, 9);
        try {
            new LoteIngressos(1, ingressos);
            fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Distribuição de ingressos está incorreta", e.getMessage());
        }
    }

    @Test
    public void testePercentualIngressosCaso7() {
        List<Ingresso> ingressos = preparaLoteIngressos(0, 0, 11);
        try {
            new LoteIngressos(1, ingressos);
            fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Distribuição de ingressos está incorreta", e.getMessage());
        }
    }

    @Test
    public void testePercentualIngressosCaso8() {
        List<Ingresso> ingressos = preparaLoteIngressos(30, 59, 10);
        try {
            new LoteIngressos(1, ingressos);
            fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Distribuição de ingressos está incorreta", e.getMessage());
        }
    }

    @Test
    public void testePercentualIngressosCaso9() {
        List<Ingresso> ingressos = preparaLoteIngressos(30, 61, 10);
        try {
            new LoteIngressos(1, ingressos);
            fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Distribuição de ingressos está incorreta", e.getMessage());
        }
    }

    @Test
    public void testePercentualIngressosCaso10() {
        List<Ingresso> ingressos = preparaLoteIngressos(20, 69, 10);
        try {
            new LoteIngressos(1, ingressos);
            fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Distribuição de ingressos está incorreta", e.getMessage());
        }
    }

    @Test
    public void testePercentualIngressosCaso11() {
        List<Ingresso> ingressos = preparaLoteIngressos(20, 71, 10);
        try {
            new LoteIngressos(1, ingressos);
            fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Distribuição de ingressos está incorreta", e.getMessage());
        }
    }
    
    
    @Test
    @DisplayName("Regra 1: Lote criado quando ID é válido e conjunto de ingressos informado com desconto")
    public void testeCriacaoLoteRegra1() {
    	List<Ingresso> ingressos = preparaLoteIngressos(20, 70, 10);
        new LoteIngressos(1, ingressos, 10);
    }

    @Test
    @DisplayName("Regra 2: Lote criado quando ID é válido e conjunto de ingressos informado sem desconto")
    public void testeCriacaoLoteRegra2() {
    	List<Ingresso> ingressos = preparaLoteIngressos(20, 70, 10);
    	new LoteIngressos(1, ingressos);
    }

    @Test
    @DisplayName("Regra 3: Lote não criado quando ID é inválido e conjunto de ingressos informado")
    public void testeCriacaoLoteRegra3() {
    	List<Ingresso> ingressos = preparaLoteIngressos(20, 70, 10);
        
        try {
            new LoteIngressos(-1, ingressos, 10);
            fail("Deveria ter lançado uma exceção devido ao ID inválido.");
        } catch (IllegalArgumentException e) {
            assertEquals("ID do lote não pode ser vazio", e.getMessage());
        }
    }

    @Test
    @DisplayName("Regra 4: Lote não criado quando ID é válido e conjunto de ingressos não informado")
    public void testeCriacaoLoteRegra4() {
        try {
            new LoteIngressos(1, null, 0.10);
            fail("Deveria ter lançado uma exceção devido ao conjunto de ingressos não informado.");
        } catch (IllegalArgumentException e) {
            assertEquals("Conjunto de ingressos não pode ser nulo ou vazio", e.getMessage());
        } 
    }
	
	private List<Ingresso> preparaLoteIngressos(int percentualVIP, int percentualNormal, int percentualMeiaEntrada) {
		if(percentualVIP == 0) {
			percentualVIP = 10;
		}

		if(percentualMeiaEntrada == 0) {
			percentualMeiaEntrada = 20;
		}
		
		if(percentualNormal == 0) {
			 percentualNormal = 100 - (percentualMeiaEntrada + percentualVIP );
		} 
		

        List<Ingresso> ingressos = new ArrayList<>();


        for (int i = 0; i < percentualVIP; i++) {
            ingressos.add(new Ingresso(i, TipoIngresso.VIP, 10.0));
        }

        for (int i = percentualVIP; i < percentualVIP + percentualMeiaEntrada; i++) {
            ingressos.add(new Ingresso(i, TipoIngresso.MEIA_ENTRADA, 10.0));
        }

        for (int i = percentualVIP + percentualMeiaEntrada; i < percentualVIP + percentualMeiaEntrada + percentualNormal; i++) {
            ingressos.add(new Ingresso(i, TipoIngresso.NORMAL, 10.0));
        }

        return ingressos;
    }

}
