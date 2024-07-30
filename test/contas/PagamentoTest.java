package contas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoTest {

    @Test
    @DisplayName("Dado que um valor foi fornecido na criação de um pagamento, esse mesmo valor deveria constar no pagamento")
    void testCriacaoPagamento() {
        double valor = 200.0;
        Pagamento pagamento = Pagamento.build(valor);

        assertEquals(pagamento.getValor(), valor);
    }
}