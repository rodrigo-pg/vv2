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
import main.java.model.Show;

public class ShowTest {
	private List<Ingresso> ingressos = preparaLoteIngresso();
	private LoteIngressos lote = new LoteIngressos(1, ingressos, 15.0);
	private Show show = new Show("29/07/2024", "Artista Teste", 1000.0, 2000.0, lote, true);
	

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

}
