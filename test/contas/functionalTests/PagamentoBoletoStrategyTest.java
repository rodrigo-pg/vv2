package contas.functionalTests;

import contas.domain.entities.Conta;
import contas.domain.entities.Fatura;
import contas.domain.entities.Pagamento;
import contas.domain.enums.FaturaStatus;
import contas.domain.enums.TipoPagamento;
import contas.domain.strategies.pagamento.PagamentoBoletoStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.AppError;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PagamentoBoletoStrategyTest {


    @Test
    @DisplayName("Dado que o pagamento foi informado com um valor menor que o mínimo para essa estratégia, quando ele for efetuado, deve retornar um erro de validação")
    void testLimiteMinimoPagamento() {
        PagamentoBoletoStrategy pagamentoBoletoStrategy = new PagamentoBoletoStrategy();
        Fatura fatura = Fatura.build("Rodrigo", 700.0, LocalDate.parse("2023-02-20"), FaturaStatus.PENDENTE, 1L);
        Conta conta = Conta.build(
                1L,
                0.005,
                LocalDate.parse("2023-02-05"),
                TipoPagamento.BOLETO
        );
        Pagamento pagamento = Pagamento.build(conta.getValorPago(), LocalDate.parse("2023-02-05"), pagamentoBoletoStrategy, 1L, 1L);

        Optional<AppError> resultado = pagamentoBoletoStrategy.efetuarPagamento(pagamento, conta, fatura);

        assertTrue(resultado.isPresent());
    }

    @Test
    @DisplayName("Dado que o pagamento foi informado com um valor igual ao mínimo para essa estratégia, quando ele for efetuado, deve ser contabilizado")
    void testValorMinimoPagamento() {
        PagamentoBoletoStrategy pagamentoBoletoStrategy = new PagamentoBoletoStrategy();
        Fatura fatura = Fatura.build("Rodrigo", 700.0, LocalDate.parse("2023-02-20"), FaturaStatus.PENDENTE, 1L);
        Conta conta = Conta.build(
                1L,
                0.01,
                LocalDate.parse("2023-02-05"),
                TipoPagamento.BOLETO
        );
        Pagamento pagamento = Pagamento.build(conta.getValorPago(), LocalDate.parse("2023-02-05"), pagamentoBoletoStrategy, 1L, 1L);

        Optional<AppError> resultado = pagamentoBoletoStrategy.efetuarPagamento(pagamento, conta, fatura);

        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Dado que o pagamento foi informado com um valor maior que o máximo para essa estratégia, quando ele for efetuado, deve retornar um erro de validação")
    void testLimiteMaximoPagamento() {
        PagamentoBoletoStrategy pagamentoBoletoStrategy = new PagamentoBoletoStrategy();
        Fatura fatura = Fatura.build("Rodrigo", 700.0, LocalDate.parse("2023-02-20"), FaturaStatus.PENDENTE, 1L);
        Conta conta = Conta.build(
                1L,
                5001.0,
                LocalDate.parse("2023-02-05"),
                TipoPagamento.BOLETO
        );
        Pagamento pagamento = Pagamento.build(conta.getValorPago(), LocalDate.parse("2023-02-05"), pagamentoBoletoStrategy, 1L, 1L);

        Optional<AppError> resultado = pagamentoBoletoStrategy.efetuarPagamento(pagamento, conta, fatura);

        assertTrue(resultado.isPresent());
    }

    @Test
    @DisplayName("Dado que o pagamento foi informado com um valor igual o limite superior para essa estratégia, quando ele for efetuado, deve ser contabilizado")
    void testValorLimiteSuperiorPagamento() {
        PagamentoBoletoStrategy pagamentoBoletoStrategy = new PagamentoBoletoStrategy();
        LocalDate data = LocalDate.parse("2023-02-05");
        Fatura fatura = Fatura.build("Rodrigo", 700.0, LocalDate.parse("2023-02-20"), FaturaStatus.PENDENTE, 1L);
        Conta conta = Conta.build(
                1L,
                5000.0,
                data,
                TipoPagamento.BOLETO
        );
        Pagamento pagamento = Pagamento.build(conta.getValorPago(), data, pagamentoBoletoStrategy, 1L, 1L);

        Optional<AppError> resultado = pagamentoBoletoStrategy.efetuarPagamento(pagamento, conta, fatura);

        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Dado que o pagamento foi informado com um entre os limites para essa estratégia, quando ele for efetuado, deve ser contabilizado")
    void testValorIntermediarioPagamento() {
        PagamentoBoletoStrategy pagamentoBoletoStrategy = new PagamentoBoletoStrategy();
        LocalDate data = LocalDate.parse("2023-02-05");
        Fatura fatura = Fatura.build("Rodrigo", 700.0, LocalDate.parse("2023-02-20"), FaturaStatus.PENDENTE, 1L);
        Conta conta = Conta.build(
                1L,
                1500.0,
                data,
                TipoPagamento.BOLETO
        );
        Pagamento pagamento = Pagamento.build(conta.getValorPago(), data, pagamentoBoletoStrategy, 1L, 1L);

        Optional<AppError> resultado = pagamentoBoletoStrategy.efetuarPagamento(pagamento, conta, fatura);

        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Dado que o pagamento foi informado com uma data posterior à data da conta, quando ele for efetuado, seu valor deve ser acrescido 10%")
    void testAtrasoPagamento() {
        PagamentoBoletoStrategy pagamentoBoletoStrategy = new PagamentoBoletoStrategy();
        LocalDate data = LocalDate.parse("2023-02-20");
        LocalDate dataPagamento = LocalDate.parse("2023-02-21");
        Fatura fatura = Fatura.build("Rodrigo", 1200.0, data, FaturaStatus.PENDENTE, 1L);
        Conta conta = Conta.build(
                1L,
                1000.0,
                data,
                TipoPagamento.BOLETO
        );
        Pagamento pagamento = Pagamento.build(conta.getValorPago(), dataPagamento, pagamentoBoletoStrategy, 1L, 1L);
        double valorComJuros = pagamento.getValor() + (pagamento.getValor() * 0.1);

        pagamentoBoletoStrategy.efetuarPagamento(pagamento, conta, fatura);

        assertEquals(valorComJuros, pagamento.getValor().floatValue());
    }

}
