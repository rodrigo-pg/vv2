package test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Relatorio;
import main.enums.StatusFinanceiro;
import main.enums.TipoIngresso;

public class RelatorioTest {
	
private Relatorio relatorio;
	
	@BeforeEach
    void setUp() {
		Map<TipoIngresso, Integer> numIngressosVendidos = new HashMap<>();
        numIngressosVendidos.put(TipoIngresso.VIP, 100);
        numIngressosVendidos.put(TipoIngresso.MEIA_ENTRADA, 50);
        numIngressosVendidos.put(TipoIngresso.NORMAL, 350);

		relatorio = new Relatorio(numIngressosVendidos, 1625.00, StatusFinanceiro.LUCRO);
    }
	
	
	@Test
	void testCriacaoRelatorio() {
		assertEquals(100, relatorio.getNumeroIngressosVIP());
	    assertEquals(50, relatorio.getNumeroIngressosMeiaEntrada());
	    assertEquals(350, relatorio.getNumeroIngressosNormal());
	    assertEquals(1625.00, relatorio.getReceitaLiquida(), 0.01);
	    assertEquals(StatusFinanceiro.LUCRO, relatorio.getStatusFinanceiro());
	}
	
	@Test
    public void testStatusFinanceiroPrejuizo() {
        Map<TipoIngresso, Integer> numIngressosVendidos = new HashMap<>();
        numIngressosVendidos.put(TipoIngresso.VIP, 60);
        numIngressosVendidos.put(TipoIngresso.MEIA_ENTRADA, 30);
        numIngressosVendidos.put(TipoIngresso.NORMAL, 240);

        Double receitaLiquida = -500.00;
        StatusFinanceiro status = StatusFinanceiro.PREJUÍZO;

        Relatorio relatorio = new Relatorio(numIngressosVendidos, receitaLiquida, status);

        assertEquals(StatusFinanceiro.PREJUÍZO, relatorio.getStatusFinanceiro());
    }

}
