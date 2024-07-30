package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Ingresso;
import main.LoteIngressos;
import main.TipoIngresso;


public class LoteIngressosTest {
	private LoteIngressos lote;
	private List<Ingresso> ingressos;
	
	@BeforeEach
    void setUp() {
		ingressos = preparaLoteIngresso();
        lote = new LoteIngressos(1, ingressos, 15.0);
        
    }
	
	private List<Ingresso> preparaLoteIngresso() {
		int totalIngressos = 100;

        List<Ingresso> ingressos = new ArrayList<>();
        for (int i = 0; i < totalIngressos; i++) {
            if (i < 20) {
                ingressos.add(new Ingresso(i, TipoIngresso.VIP));
            } else if (i < 30) {
                ingressos.add(new Ingresso(i, TipoIngresso.MEIA_ENTRADA));
            } else {
                ingressos.add(new Ingresso(i, TipoIngresso.NORMAL));
            }
        }
        
        return ingressos;
		
	}
	
	@Test
	void testCriacaoLote() {
		assertEquals(1, lote.getId());
		assertEquals(ingressos, lote.getIngressos());
		assertEquals(15.0, lote.getDesconto());
        
	}

	@Test
    void testCriacaoLoteComDistribuicaoCorreta() {
		assertEquals(100, lote.getIngressos().size());
    }
	
	@Test
    void testCriacaoLoteComDistribuicaoIncorreta() {
        int totalIngressos = 100;

        List<Ingresso> ingressos = new ArrayList<>();
        for (int i = 0; i < totalIngressos; i++) {
            if (i < 11) {
                ingressos.add(new Ingresso(i, TipoIngresso.VIP));
            } else if (i < 21) {
                ingressos.add(new Ingresso(i, TipoIngresso.MEIA_ENTRADA));
            } else {
                ingressos.add(new Ingresso(i, TipoIngresso.NORMAL));
            }
        }
        
        try {
            new LoteIngressos(1, ingressos, 15.0);
            fail("Deveria lançar uma exceção aqui");
        } catch (IllegalArgumentException e) {
            assertEquals("Distribuição de ingressos está incorreta", e.getMessage());
        }
       
    }

	
	@Test
    void testAplicarDesconto() {
        lote.aplicarDesconto(10);

        assertEquals(18.0, ingressos.get(0).getPreco());
        assertEquals(5.0, ingressos.get(20).getPreco());
        assertEquals(9.0, ingressos.get(30).getPreco());
    }

    @Test
    void testDescontoMaximo() {
    	lote.aplicarDesconto(25);

        assertEquals(15.0, ingressos.get(0).getPreco());
        assertEquals(5.0, ingressos.get(20).getPreco());
        assertEquals(7.5, ingressos.get(30).getPreco());
    }
    
    @Test
    void testAplicaDescontoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            lote.aplicarDesconto(26);
        }, "Desconto não pode ser maior que 25%");
    }

}
