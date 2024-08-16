package test.functionalTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import main.java.enums.StatusFinanceiro;
import main.java.enums.TipoIngresso;
import main.java.model.Relatorio;

public class RelatorioTest {
	
	// Técnica: Tabelas de Decisão: testes de criação do relatorio
	@Test
    @DisplayName("Regra 1: Relatório criado com número de ingressos vendidos, receita líquida e status informados")
    public void testRelatorioCriadoComTodosOsDadosInformados() {
        Map<TipoIngresso, Integer> numIngressosVendidos = new HashMap<>();
        numIngressosVendidos.put(TipoIngresso.VIP, 100);
        numIngressosVendidos.put(TipoIngresso.MEIA_ENTRADA, 50);
        numIngressosVendidos.put(TipoIngresso.NORMAL, 200);

        new Relatorio(numIngressosVendidos, 10000.0, StatusFinanceiro.LUCRO);
    }

    @Test
    @DisplayName("Regra 2: Relatório não criado sem número de ingressos vendidos informado")
    public void testRelatorioNaoCriadoSemNumeroIngressosVendidos() {
        try {
        	new Relatorio(null, 10000.0, StatusFinanceiro.LUCRO);
            fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Número de ingressos vendidos deve ser informado", e.getMessage());
        }
    }

    @Test
    @DisplayName("Regra 3: Relatório não criado sem receita líquida informada")
    public void testRelatorioNaoCriadoSemReceitaLiquida() {
        Map<TipoIngresso, Integer> numIngressosVendidos = new HashMap<>();
        numIngressosVendidos.put(TipoIngresso.VIP, 100);
        numIngressosVendidos.put(TipoIngresso.MEIA_ENTRADA, 50);
        numIngressosVendidos.put(TipoIngresso.NORMAL, 200);
        
        try {
        	new Relatorio(numIngressosVendidos, null, StatusFinanceiro.LUCRO);
            fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Receita líquida deve ser informada", e.getMessage());
        }
    }

    @Test
    @DisplayName("Regra 4: Relatório não criado sem status informado")
    public void testRelatorioNaoCriadoSemStatusInformado() {
        Map<TipoIngresso, Integer> numIngressosVendidos = new HashMap<>();
        numIngressosVendidos.put(TipoIngresso.VIP, 100);
        numIngressosVendidos.put(TipoIngresso.MEIA_ENTRADA, 50);
        numIngressosVendidos.put(TipoIngresso.NORMAL, 200);
        
        try {
        	new Relatorio(numIngressosVendidos, 10000.0, null);
            fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Status financeiro deve ser informado", e.getMessage());
        }
    }

}
