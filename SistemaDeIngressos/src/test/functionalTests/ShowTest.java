package test.functionalTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import main.java.enums.StatusFinanceiro;
import main.java.enums.TipoIngresso;
import main.java.model.Ingresso;
import main.java.model.LoteIngressos;
import main.java.model.Relatorio;
import main.java.model.Show;

public class ShowTest {
	private LoteIngressos lote = criarLoteIngressos(5000.00);
	
	// Técnica: Tabelas de Decisão : testes para a criação do show
    @Test
    @DisplayName("Regra 1: Show criado quando todos os parâmetros são definidos")
    public void testeCriacaoShowRegra1() {
        new Show("2024-12-01", "Artista Exemplo", 5000.00, 2000.00, lote, true);
    }

    @Test
    @DisplayName("Regra 2: Show criado quando todos os parâmetros são definidos e com a Data Especial com false")
    public void testeCriacaoShowRegra2() {
        new Show("2024-12-01", "Artista Exemplo", 5000.00, 2000.00, lote, false);
    }

    @Test
    @DisplayName("Regra 3: Show não criado quando Data do show não foi definida")
    public void testeCriacaoShowRegra3() {
    	try {
    		new Show(null, "Artista Exemplo", 5000.00, 2000.00, lote, true);
    		fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Data do show não pode ser nula", e.getMessage());
        }        
    }

    @Test
    @DisplayName("Regra 4: Show não criado quando Artista não foi definido")
    public void testeCriacaoShowRegra4() {
    	try {
    		new Show("2024-12-01", null, 5000.00, 2000.00, lote, true);
    		fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Artista não pode ser nulo", e.getMessage());
        }  
    }

    @Test
    @DisplayName("Regra 5: Show não criado quando Cachê não foi definido")
    public void testeCriacaoShowRegra5() {
    	try {
    		new Show("2024-12-01", "Artista Exemplo", 0.00, 2000.00, lote, true);
    		fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Cachê não pode ser zero", e.getMessage());
        }         
    }

    @Test
    @DisplayName("Regra 6: Show não criado quando Total de despesas não foi definido")
    public void testeCriacaoShowRegra6() {
    	try {
    		new Show("2024-12-01", "Artista Exemplo", 5000.00, 0.00, lote, true);
    		fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Despesas de infraestrutura não podem ser zero", e.getMessage());
        }   
    }

    @Test
    @DisplayName("Regra 7: Show não criado quando Lotes de ingressos não foi definidos")
    public void testeCriacaoShowRegra7() {
    	try {
    		new Show("2024-12-01", "Artista Exemplo", 5000.00, 2000.00, null, true);
    		fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Lotes de ingressos não podem ser nulos", e.getMessage());
        }  
    }
    
    
  // Técnica: Partições de Equivalência: testes do cálculo da Receita Líquida
    @Test
    @DisplayName("Caso 1: LUCRO - Receitas de 5.000,00, Despesas de 2.000,00, Cachê de 1.000,00, Data Especial: Não")
    public void testCalculoReceitaLiquidaCaso1() {
        LoteIngressos lote = criarLoteIngressos(5000.00);
        Show show = new Show("2024-08-15", "Artista 1", 1000.00, 2000.00, lote, false);

        Relatorio relatorio = show.gerarRelatorio();
        assertEquals(StatusFinanceiro.LUCRO, relatorio.getStatusFinanceiro());
        assertEquals(2000.00, relatorio.getReceitaLiquida(), 0.01);
    }
    
    @Test
    @DisplayName("Caso 2: ESTÁVEL - Receitas de 3.000,00, Despesas de 2.000,00, Cachê de 1.000,00, Data Especial: Não")
    public void testCalculoReceitaLiquidaCaso2() {
        LoteIngressos lote = criarLoteIngressos(3000.00);
        Show show = new Show("2024-08-15", "Artista 2", 1000.00, 2000.00, lote, false);

        Relatorio relatorio = show.gerarRelatorio();
        assertEquals(StatusFinanceiro.ESTÁVEL, relatorio.getStatusFinanceiro());
        assertEquals(0.00, relatorio.getReceitaLiquida(), 0.01);
    }
    
    @Test
    @DisplayName("Caso 3: PREJUÍZO - Receitas de 2.500,00, Despesas de 2.000,00, Cachê de 1.000,00, Data Especial: Sim")
    public void testCalculoReceitaLiquidaCaso3() {
        LoteIngressos lote = criarLoteIngressos(2500.00);
        Show show = new Show("2024-08-15", "Artista 3", 1000.00, 2000.00, lote, true);

        Relatorio relatorio = show.gerarRelatorio();
        assertEquals(StatusFinanceiro.PREJUÍZO, relatorio.getStatusFinanceiro());
        assertEquals(-800.00, relatorio.getReceitaLiquida(), 0.01);
    }

    @Test
    @DisplayName("Caso 4: LUCRO - Receitas de 4.500,00, Despesas de 2.000,00, Cachê de 1.500,00, Data Especial: Sim")
    public void testCalculoReceitaLiquidaCaso4() {
        LoteIngressos lote = criarLoteIngressos(4500.00);
        Show show = new Show("2024-08-15", "Artista 4", 1500.00, 2000.00, lote, true);

        Relatorio relatorio = show.gerarRelatorio();
        assertEquals(StatusFinanceiro.LUCRO, relatorio.getStatusFinanceiro());
        assertEquals(700.00, relatorio.getReceitaLiquida(), 0.01);
    }
    
    @Test
    @DisplayName("Caso 5: ESTÁVEL - Receitas de 3.300,00, Despesas de 2.000,00, Cachê de 1.000,00, Data Especial: Sim")
    public void testCalculoReceitaLiquidaCaso5() {
        LoteIngressos lote = criarLoteIngressos(3300.00);
        Show show = new Show("2024-08-15", "Artista 5", 1000.00, 2000.00, lote, true);

        Relatorio relatorio = show.gerarRelatorio();
        assertEquals(StatusFinanceiro.ESTÁVEL, relatorio.getStatusFinanceiro());
        assertEquals(0.00, relatorio.getReceitaLiquida(), 0.01);
    }

    @Test
    @DisplayName("Caso 6: PREJUÍZO - Receitas de 3.500,00, Despesas de 2.000,00, Cachê de 1.000,00, Data Especial: Não")
    public void testCalculoReceitaLiquidaCaso6() {
        LoteIngressos lote = criarLoteIngressos(2500.00);
        Show show = new Show("2024-08-15", "Artista 6", 1000.00, 2000.00, lote, false);

        Relatorio relatorio = show.gerarRelatorio();
        assertEquals(StatusFinanceiro.PREJUÍZO, relatorio.getStatusFinanceiro());
        assertEquals(-500.00, relatorio.getReceitaLiquida(), 0.01);
    }
    
    @Test
    @DisplayName("Caso 7: ERRO - Receita negativa")
    public void testCalculoReceitaLiquidaReceitaNegativaCaso7() {
        LoteIngressos lote = criarLoteIngressos(-1000.00);
        
        try {
        	new Show("2024-08-15", "Artista 7", 1000.00, 2000.00, lote, false).gerarRelatorio();
    		fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Receita negativa inválida", e.getMessage());
        }  
    }

    @Test
    @DisplayName("Caso 8: ERRO - Despesas negativas")
    public void testCalculoReceitaLiquidaDespesasNegativasCaso8() {
        LoteIngressos lote = criarLoteIngressos(5000.00);
        
        try {
        	new Show("2024-08-15", "Artista 8", 1000.00, -500.00, lote, false).gerarRelatorio();
    		fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Despesas negativas inválidas", e.getMessage());
        } 
    }

    @Test
    @DisplayName("Caso 9: ERRO - Cachê negativo")
    public void testCalculoReceitaLiquidaCacheNegativoCaso9() {
        LoteIngressos lote = criarLoteIngressos(5000.00);
        
        try {
        	new Show("2024-08-15", "Artista 9", -1000.00, 2000.00, lote, false).gerarRelatorio();
    		fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Cachê negativo inválido", e.getMessage());
        } 
    }
    
    private LoteIngressos criarLoteIngressos(double receita) {
        List<Ingresso> ingressos = new ArrayList<>();

        // Adicionar ingressos VIP
        for (int i = 1; i <= 2; i++) {
            ingressos.add(new Ingresso(i, TipoIngresso.VIP, 100.0));
        }

        // Adicionar ingresso Meia Entrada
        ingressos.add(new Ingresso(3, TipoIngresso.MEIA_ENTRADA, 100.0));

        // Adicionar ingressos Normais
        for (int i = 4; i <= 9; i++) {
            ingressos.add(new Ingresso(i, TipoIngresso.NORMAL, 100.0));
        }

        // Adicionar o último ingresso Normal com o valor baseado na receita
        Ingresso ultimoIngresso = new Ingresso(10, TipoIngresso.NORMAL, receita);
        ultimoIngresso.marcarComoVendido();
        ingressos.add(ultimoIngresso);

        return new LoteIngressos(1, ingressos);
    }

}
