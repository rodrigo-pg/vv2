package contas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoTest {

    @Test
    @DisplayName("Dado que um valor foi fornecido na criação de um pagamento, esse mesmo valor deveria constar no pagamento")
    void testValorInformadoPagamento() {
        LocalDate data = LocalDate.parse("2024-05-25");
        double valor = 200.0;
        Pagamento pagamento = Pagamento.build(valor, data);

        assertEquals(pagamento.getValor(), valor);
    }

    @Test
    @DisplayName("Dado que uma data foi fornecido na criação de um pagamento, essa mesma data deveria constar no pagamento")
    void testDataInformadaPagamento() {
        LocalDate data = LocalDate.parse("2024-05-25");
        double valor = 200.0;
        Pagamento pagamento = Pagamento.build(valor, data);

        assertEquals(pagamento.getData(), data);
    }
}