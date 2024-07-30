package contas;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoTest {

    @Test
    @DisplayName("Dado que um valor foi fornecido na criação de um pagamento, esse mesmo valor deveria constar no pagamento")
    void testValorInformadoPagamento() {
        PagamentoStrategy strategy = new PagamentoBoletoStrategy();
        LocalDate data = LocalDate.parse("2024-05-25");
        double valor = 200.0;
        Pagamento pagamento = Pagamento.build(valor, data, strategy, 1L, 1L);

        assertEquals(pagamento.getValor(), valor);
    }

    @Test
    @DisplayName("Dado que uma data foi fornecido na criação de um pagamento, essa mesma data deveria constar no pagamento")
    void testDataInformadaPagamento() {
        PagamentoStrategy strategy = new PagamentoBoletoStrategy();
        LocalDate data = LocalDate.parse("2024-05-25");
        double valor = 200.0;
        Pagamento pagamento = Pagamento.build(valor, data, strategy, 1L, 1L);

        assertEquals(pagamento.getData(), data);
    }

    @Test
    @DisplayName("Dado que um meio de pagamento foi fornecido na criação de um pagamento, esse mesma meio deveria constar no pagamento")
    void testTipoInformadoPagamento() {
        PagamentoStrategy strategy = new PagamentoBoletoStrategy();
        LocalDate data = LocalDate.parse("2024-05-25");
        double valor = 200.0;
        Pagamento pagamento = Pagamento.build(valor, data, strategy, 1L, 1L);

        assertEquals(pagamento.getStrategy(), strategy);
    }

    @Test
    @DisplayName("Dado que uma conta foi fornecido na criação de um pagamento, esse mesma conta deveria constar no pagamento")
    void testContaIdInformadoPagamento() {
        PagamentoStrategy strategy = new PagamentoBoletoStrategy();
        LocalDate data = LocalDate.parse("2024-05-25");
        Long contaId = 1L;
        double valor = 200.0;
        Pagamento pagamento = Pagamento.build(valor, data, strategy, contaId, 1L);

        assertEquals(pagamento.getContaId(), contaId);
    }

    @Test
    @DisplayName("Dado que uma fatura foi fornecido na criação de um pagamento, esse mesma fatura deveria constar no pagamento")
    void testFaturaIdInformadoPagamento() {
        PagamentoStrategy strategy = new PagamentoBoletoStrategy();
        LocalDate data = LocalDate.parse("2024-05-25");
        Long contaId = 1L;
        Long faturaId = 1L;
        double valor = 200.0;
        Pagamento pagamento = Pagamento.build(valor, data, strategy, contaId, faturaId);

        assertEquals(pagamento.getFaturaId(), faturaId);
    }

    @Test
    @DisplayName("Dado que a efetuação do pagamento foi requisitada, a chamada deveria ser redirecionada para a estratégia de pagamento")
    void testExecutarPagamento() {
        PagamentoStrategy pagamentoStrategySpy = spy(new PagamentoBoletoStrategy());
        LocalDate data = LocalDate.parse("2024-05-25");
        double valor = 200.0;
        Pagamento pagamento = Pagamento.build(valor, data, pagamentoStrategySpy, 1L, 1L);

        pagamento.efetuarPagamento();

        verify(pagamentoStrategySpy, times(1)).efetuarPagamento();
    }
}