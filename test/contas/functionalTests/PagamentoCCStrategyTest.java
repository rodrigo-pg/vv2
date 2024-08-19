package contas.functionalTests;

import contas.domain.entities.Conta;
import contas.domain.entities.Fatura;
import contas.domain.entities.Pagamento;
import contas.domain.enums.FaturaStatus;
import contas.domain.enums.TipoPagamento;
import contas.domain.strategies.pagamento.PagamentoCCStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PagamentoCCStrategyTest {

    @Test
    @DisplayName("Dado que a conta do pagamento possui uma data menor que 15 dias da fatura, quando ele for efetuado, o valor do pagamento deve zerar")
    void testDataPagamentoIgualLimite() {
        LocalDate dataPagamento = LocalDate.parse("2023-02-05");
        LocalDate dataFatura = LocalDate.parse("2023-02-20");
        Fatura fatura = Fatura.build("Rodrigo", 700.0, dataFatura, FaturaStatus.PENDENTE, 1L);
        PagamentoCCStrategy pagamentoCCStrategy = new PagamentoCCStrategy();
        Conta conta = Conta.build(
                1L,
                500.0,
                dataPagamento,
                TipoPagamento.CARTAO_CREDITO
        );
        Pagamento pagamento = Pagamento.build(conta.getValorPago(), dataPagamento, pagamentoCCStrategy, 1L, 1L);

        pagamento.efetuarPagamento(conta, fatura);

        assertEquals(conta.getValorPago(), pagamento.getValor().intValue());
    }

    @Test
    @DisplayName("Dado que a conta do pagamento possui uma data menor que 15 dias da fatura, quando ele for efetuado, o valor do pagamento deve zerar")
    void testDataLimite() {
        LocalDate dataPagamento = LocalDate.parse("2023-02-06");
        LocalDate dataFatura = LocalDate.parse("2023-02-20");
        Fatura fatura = Fatura.build("Rodrigo", 700.0, dataFatura, FaturaStatus.PENDENTE, 1L);
        PagamentoCCStrategy pagamentoCCStrategy = new PagamentoCCStrategy();
        Conta conta = Conta.build(
                1L,
                500.0,
                dataPagamento,
                TipoPagamento.CARTAO_CREDITO
        );
        Pagamento pagamento = Pagamento.build(conta.getValorPago(), dataPagamento, pagamentoCCStrategy, 1L, 1L);

        pagamento.efetuarPagamento(conta, fatura);

        assertEquals(0, pagamento.getValor().intValue());
    }

    @Test
    @DisplayName("Dado que a conta do pagamento possui uma data posterior a fatura, quando ele for efetuado, o valor do pagamento deve zerar")
    void testDataContaPosteriorFatura() {
        LocalDate dataPagamento = LocalDate.parse("2023-02-21");
        LocalDate dataFatura = LocalDate.parse("2023-02-20");
        Fatura fatura = Fatura.build("Rodrigo", 700.0, dataFatura, FaturaStatus.PENDENTE, 1L);
        PagamentoCCStrategy pagamentoCCStrategy = new PagamentoCCStrategy();
        Conta conta = Conta.build(
                1L,
                500.0,
                dataPagamento,
                TipoPagamento.CARTAO_CREDITO
        );
        Pagamento pagamento = Pagamento.build(conta.getValorPago(), dataPagamento, pagamentoCCStrategy, 1L, 1L);

        pagamento.efetuarPagamento(conta, fatura);

        assertEquals(0, pagamento.getValor().intValue());
    }

    @Test
    @DisplayName("Dado que a conta do pagamento possui uma data de pelo menos 15 dias da fatura, quando ele for efetuado, o pagamento deve ser efetuado")
    void testDataLimiteCorreta() {
        LocalDate dataPagamento = LocalDate.parse("2023-02-04");
        LocalDate dataFatura = LocalDate.parse("2023-02-20");
        Fatura fatura = Fatura.build("Rodrigo", 700.0, dataFatura, FaturaStatus.PENDENTE, 1L);
        PagamentoCCStrategy pagamentoCCStrategy = new PagamentoCCStrategy();
        Conta conta = Conta.build(
                1L,
                500.0,
                dataPagamento,
                TipoPagamento.CARTAO_CREDITO
        );
        Pagamento pagamento = Pagamento.build(conta.getValorPago(), dataPagamento, pagamentoCCStrategy, 1L, 1L);

        pagamento.efetuarPagamento(conta, fatura);

        assertEquals(conta.getValorPago(), pagamento.getValor().intValue());
    }

}
