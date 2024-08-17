package contas.functionalTests;

import contas.domain.entities.Conta;
import contas.domain.entities.Fatura;
import contas.domain.entities.Pagamento;
import contas.domain.enums.FaturaStatus;
import contas.domain.enums.TipoPagamento;
import contas.domain.strategies.pagamento.PagamentoTransferenciaStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PagamentoTransferenciaStrategyTest {
    @Test
    @DisplayName("Dado que a conta do pagamento possui uma data posterior a fatura, quando ele for efetuado, o valor do pagamento deve zerar")
    void testValorAposDataFatura() {
        LocalDate dataConta = LocalDate.parse("2024-05-27");
        LocalDate dataPagamento = LocalDate.parse("2024-05-26");
        LocalDate dataFatura = LocalDate.parse("2024-05-26");
        Fatura fatura = Fatura.build("Rodrigo", 700.0, dataFatura, FaturaStatus.PENDENTE, 1L);
        PagamentoTransferenciaStrategy pagamentoBoletoStrategy = new PagamentoTransferenciaStrategy();
        Conta conta = Conta.build(
                1L,
                500.0,
                dataConta,
                TipoPagamento.BOLETO
        );
        double valor = 600;
        Pagamento pagamento = Pagamento.build(valor, dataPagamento, pagamentoBoletoStrategy, 1L, 1L);

        pagamentoBoletoStrategy.efetuarPagamento(pagamento, conta, fatura);

        assertEquals(0, pagamento.getValor().intValue());
    }

    @Test
    @DisplayName("Dado que a conta do pagamento possui uma data igual a fatura, quando ele for efetuado, o valor do pagamento deve ser contabilizado")
    void testValorAposDataIgualDataFatura() {
        LocalDate dataConta = LocalDate.parse("2024-05-26");
        LocalDate dataPagamento = LocalDate.parse("2024-05-26");
        LocalDate dataFatura = LocalDate.parse("2024-05-26");
        Fatura fatura = Fatura.build("Rodrigo", 700.0, dataFatura, FaturaStatus.PENDENTE, 1L);
        PagamentoTransferenciaStrategy pagamentoBoletoStrategy = new PagamentoTransferenciaStrategy();
        Conta conta = Conta.build(
                1L,
                500.0,
                dataConta,
                TipoPagamento.BOLETO
        );
        double valor = 600;
        Pagamento pagamento = Pagamento.build(valor, dataPagamento, pagamentoBoletoStrategy, 1L, 1L);

        pagamentoBoletoStrategy.efetuarPagamento(pagamento, conta, fatura);

        assertEquals(valor, pagamento.getValor().intValue());
    }

    @Test
    @DisplayName("Dado que a conta do pagamento possui uma data menor que a fatura, quando ele for efetuado, o valor do pagamento deve ser contabilizado")
    void testValorAposDataMenorDataFatura() {
        LocalDate dataConta = LocalDate.parse("2024-05-24");
        LocalDate dataPagamento = LocalDate.parse("2024-05-26");
        LocalDate dataFatura = LocalDate.parse("2024-05-26");
        Fatura fatura = Fatura.build("Rodrigo", 700.0, dataFatura, FaturaStatus.PENDENTE, 1L);
        PagamentoTransferenciaStrategy pagamentoBoletoStrategy = new PagamentoTransferenciaStrategy();
        Conta conta = Conta.build(
                1L,
                500.0,
                dataConta,
                TipoPagamento.BOLETO
        );
        double valor = 600;
        Pagamento pagamento = Pagamento.build(valor, dataPagamento, pagamentoBoletoStrategy, 1L, 1L);

        pagamentoBoletoStrategy.efetuarPagamento(pagamento, conta, fatura);

        assertEquals(valor, pagamento.getValor().intValue());
    }
}
