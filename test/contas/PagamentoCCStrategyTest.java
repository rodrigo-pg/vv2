package contas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.AppError;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoCCStrategyTest {

    @Test
    @DisplayName("Dado que a conta do pagamento possui uma data menor que 15 dias da fatura, quando ele for efetuado, o valor do pagamento deve zerar")
    void testDataLimite() {
        LocalDate dataConta = LocalDate.parse("2024-05-23");
        LocalDate dataPagamento = LocalDate.parse("2024-05-26");
        LocalDate dataFatura = LocalDate.parse("2024-05-26");
        Fatura fatura = Fatura.build("Rodrigo", 700.0, dataFatura, FaturaStatus.PENDENTE);
        PagamentoCCStrategy pagamentoBoletoStrategy = new PagamentoCCStrategy();
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
    @DisplayName("Dado que a conta do pagamento possui uma data posterior a fatura, quando ele for efetuado, o valor do pagamento deve zerar")
    void testDataContaPosteriorFatura() {
        LocalDate dataConta = LocalDate.parse("2024-05-27");
        LocalDate dataPagamento = LocalDate.parse("2024-05-26");
        LocalDate dataFatura = LocalDate.parse("2024-05-26");
        Fatura fatura = Fatura.build("Rodrigo", 700.0, dataFatura, FaturaStatus.PENDENTE);
        PagamentoCCStrategy pagamentoBoletoStrategy = new PagamentoCCStrategy();
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
    @DisplayName("Dado que a conta do pagamento possui uma data de pelo menos 15 dias da fatura, quando ele for efetuado, o pagamento deve ser efetuado")
    void testDataLimiteCorreta() {
        LocalDate dataConta = LocalDate.parse("2024-05-10");
        LocalDate dataPagamento = LocalDate.parse("2024-05-26");
        LocalDate dataFatura = LocalDate.parse("2024-05-26");
        Fatura fatura = Fatura.build("Rodrigo", 700.0, dataFatura, FaturaStatus.PENDENTE);
        PagamentoCCStrategy pagamentoBoletoStrategy = new PagamentoCCStrategy();
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