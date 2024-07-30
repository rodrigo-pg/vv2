package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Ingresso;
import main.LoteIngressos;
import main.TipoIngresso;


public class LoteIngressosTest {
	private LoteIngressos lote;
	
	@BeforeEach
    void setUp() {
		List<Ingresso> ingressos = preparaLoteIngresso();
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

}
