package contas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.AppError;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

class PagamentoBoletoStrategyTest {

    @Test
    @DisplayName("Dado que o pagamento foi informado com um valor menor que o mínimo para essa estratégia, quando ele for efetuado, deve retornar um erro de validação")
    void testLimiteMinimoPagamento() {
        PagamentoBoletoStrategy pagamentoBoletoStrategy = new PagamentoBoletoStrategy();
        LocalDate data = LocalDate.parse("2024-05-25");
        Fatura fatura = Fatura.build("Rodrigo", 700.0, data);
        Conta conta = Conta.build(
                1L,
                500.0,
                data
        );
        double valor = 0.005;
        Pagamento pagamento = Pagamento.build(valor, data, pagamentoBoletoStrategy, 1L, 1L);

        Optional<AppError> resultado = pagamentoBoletoStrategy.efetuarPagamento(pagamento, conta, fatura);

        assertTrue(resultado.isPresent());
    }

    @Test
    @DisplayName("Dado que o pagamento foi informado com um valor maior que o máximo para essa estratégia, quando ele for efetuado, deve retornar um erro de validação")
    void testLimiteMaximoPagamento() {
        PagamentoBoletoStrategy pagamentoBoletoStrategy = new PagamentoBoletoStrategy();
        LocalDate data = LocalDate.parse("2024-05-25");
        Fatura fatura = Fatura.build("Rodrigo", 700.0, data);
        Conta conta = Conta.build(
                1L,
                500.0,
                data
        );
        double valor = 5001;
        Pagamento pagamento = Pagamento.build(valor, data, pagamentoBoletoStrategy, 1L, 1L);

        Optional<AppError> resultado = pagamentoBoletoStrategy.efetuarPagamento(pagamento, conta, fatura);

        assertTrue(resultado.isPresent());
    }

    @Test
    @DisplayName("Dado que o pagamento foi informado com uma data posterior à data da conta, quando ele for efetuado, seu valor deve ser acrescido 10%")
    void testAtrasoPagamento() {
        PagamentoBoletoStrategy pagamentoBoletoStrategy = new PagamentoBoletoStrategy();
        LocalDate data = LocalDate.parse("2024-05-25");
        LocalDate dataPagamento = LocalDate.parse("2024-05-26");
        Fatura fatura = Fatura.build("Rodrigo", 700.0, data);
        Conta conta = Conta.build(
                1L,
                500.0,
                data
        );
        double valor = 600;
        Pagamento pagamento = Pagamento.build(valor, dataPagamento, pagamentoBoletoStrategy, 1L, 1L);
        double valorComJuros = pagamento.getValor() + (pagamento.getValor() * 0.1);

        Optional<AppError> resultado = pagamentoBoletoStrategy.efetuarPagamento(pagamento, conta, fatura);

        assertEquals(valorComJuros, pagamento.getValor().floatValue());
    }
}