package contas.functionalTests;

import contas.domain.entities.Conta;
import contas.domain.entities.Fatura;
import contas.domain.entities.Pagamento;
import contas.domain.enums.FaturaStatus;
import contas.domain.enums.TipoPagamento;
import contas.domain.strategies.pagamento.PagamentoCCStrategy;
import contas.domain.strategies.pagamento.PagamentoTransferenciaStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PagamentoTransferenciaStrategyTest {
    @Test
    @DisplayName("Dado que a conta do pagamento possui uma data posterior a fatura, quando ele for efetuado, o valor do pagamento deve zerar")
    void testValorAposDataFatura() {
        LocalDate dataPagamento = LocalDate.parse("2023-02-21");
        LocalDate dataFatura = LocalDate.parse("2023-02-20");
        Fatura fatura = Fatura.build("Rodrigo", 700.0, dataFatura, FaturaStatus.PENDENTE, 1L);
        PagamentoTransferenciaStrategy pagamentoTransferenciaStrategy = new PagamentoTransferenciaStrategy();
        Conta conta = Conta.build(
                1L,
                500.0,
                dataPagamento,
                TipoPagamento.TRANSFERENCIA
        );
        Pagamento pagamento = Pagamento.build(conta.getValorPago(), dataPagamento, pagamentoTransferenciaStrategy, 1L, 1L);

        pagamento.efetuarPagamento(conta, fatura);

        assertEquals(0, pagamento.getValor().intValue());
    }

    @Test
    @DisplayName("Dado que a conta do pagamento possui uma data igual a fatura, quando ele for efetuado, o valor do pagamento deve ser contabilizado")
    void testValorAposDataIgualDataFatura() {
        LocalDate dataPagamento = LocalDate.parse("2023-02-20");
        LocalDate dataFatura = LocalDate.parse("2023-02-20");
        Fatura fatura = Fatura.build("Rodrigo", 700.0, dataFatura, FaturaStatus.PENDENTE, 1L);
        PagamentoTransferenciaStrategy pagamentoTransferenciaStrategy = new PagamentoTransferenciaStrategy();
        Conta conta = Conta.build(
                1L,
                500.0,
                dataPagamento,
                TipoPagamento.TRANSFERENCIA
        );
        Pagamento pagamento = Pagamento.build(conta.getValorPago(), dataPagamento, pagamentoTransferenciaStrategy, 1L, 1L);

        pagamento.efetuarPagamento(conta, fatura);

        assertEquals(conta.getValorPago(), pagamento.getValor().intValue());
    }

    @Test
    @DisplayName("Dado que a conta do pagamento possui uma data menor que a fatura, quando ele for efetuado, o valor do pagamento deve ser contabilizado")
    void testValorAposDataMenorDataFatura() {
        LocalDate dataPagamento = LocalDate.parse("2023-02-19");
        LocalDate dataFatura = LocalDate.parse("2023-02-20");
        Fatura fatura = Fatura.build("Rodrigo", 700.0, dataFatura, FaturaStatus.PENDENTE, 1L);
        PagamentoTransferenciaStrategy pagamentoTransferenciaStrategy = new PagamentoTransferenciaStrategy();
        Conta conta = Conta.build(
                1L,
                500.0,
                dataPagamento,
                TipoPagamento.TRANSFERENCIA
        );
        Pagamento pagamento = Pagamento.build(conta.getValorPago(), dataPagamento, pagamentoTransferenciaStrategy, 1L, 1L);

        pagamento.efetuarPagamento(conta, fatura);

        assertEquals(conta.getValorPago(), pagamento.getValor().intValue());
    }
}
