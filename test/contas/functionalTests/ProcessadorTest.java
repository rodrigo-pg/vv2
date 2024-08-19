package contas.functionalTests;

import contas.application.Processador;
import contas.domain.entities.Conta;
import contas.domain.entities.Fatura;
import contas.domain.enums.FaturaStatus;
import contas.domain.enums.TipoPagamento;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ProcessadorTest {

    @Test
    @DisplayName("Dado que a soma das contas de uma fatura possui um valor maior ou igual a fatura, quando a fatura for processada, deve ser marcada como paga")
    void testCaso1Processador() {
        Fatura fatura = Fatura.build(
                "Rodrigo",
                1500.0,
                LocalDate.parse("2023-02-20"),
                FaturaStatus.PENDENTE,
                1L
        );
        List<Conta> contas = Arrays.asList(
                Conta.build(1L, 500.0, LocalDate.parse("2023-02-20"), TipoPagamento.BOLETO),
                Conta.build(2L, 400.0, LocalDate.parse("2023-02-20"), TipoPagamento.BOLETO),
                Conta.build(3L, 600.0, LocalDate.parse("2023-02-20"), TipoPagamento.BOLETO)
        );
        Processador processador = new Processador(
                contas,
                fatura
        );

        processador.processar();

        assertEquals(FaturaStatus.PAGA, processador.getFatura().getStatus());
    }

    @Test
    @DisplayName("Dado que a soma das contas de uma fatura possui um valor menor que fatura, quando a fatura for processada, deve ser marcada como pendente")
    void testCaso2Processador() {
        Fatura fatura = Fatura.build(
                "Rodrigo",
                1500.0,
                LocalDate.parse("2023-02-20"),
                FaturaStatus.PENDENTE,
                1L
        );
        List<Conta> contas = Arrays.asList(
                Conta.build(1L, 700.0, LocalDate.parse("2023-02-05"), TipoPagamento.CARTAO_CREDITO),
                Conta.build(2L, 400.0, LocalDate.parse("2023-02-17"), TipoPagamento.TRANSFERENCIA)
        );
        Processador processador = new Processador(
                contas,
                fatura
        );

        processador.processar();

        assertEquals(FaturaStatus.PENDENTE, processador.getFatura().getStatus());
    }

    @Test
    void testCaso3Processador() {
        Fatura fatura = Fatura.build(
                "Rodrigo",
                1500.0,
                LocalDate.parse("2023-02-20"),
                FaturaStatus.PENDENTE,
                1L
        );
        List<Conta> contas = Arrays.asList(
                Conta.build(1L, 1300.0, LocalDate.parse("2023-02-05"), TipoPagamento.BOLETO),
                Conta.build(2L, 200.0, LocalDate.parse("2023-02-05"), TipoPagamento.BOLETO)
        );
        Processador processador = new Processador(
                contas,
                fatura
        );

        processador.processar();

        assertEquals(FaturaStatus.PAGA, processador.getFatura().getStatus());
    }

    @Test
    void testCaso4Processador() {
        Fatura fatura = Fatura.build(
                "Rodrigo",
                1500.0,
                LocalDate.parse("2023-02-20"),
                FaturaStatus.PENDENTE,
                1L
        );
        List<Conta> contas = Arrays.asList(
                Conta.build(1L, 1300.0, LocalDate.parse("2023-02-05"), TipoPagamento.BOLETO),
                Conta.build(2L, 200.0, LocalDate.parse("2023-02-05"), TipoPagamento.CARTAO_CREDITO)
        );
        Processador processador = new Processador(
                contas,
                fatura
        );

        processador.processar();

        assertEquals(FaturaStatus.PAGA, processador.getFatura().getStatus());
    }

    @Test
    void testCaso5Processador() {
        Fatura fatura = Fatura.build(
                "Rodrigo",
                1500.0,
                LocalDate.parse("2023-02-20"),
                FaturaStatus.PENDENTE,
                1L
        );
        List<Conta> contas = Arrays.asList(
                Conta.build(1L, 1300.0, LocalDate.parse("2023-02-05"), TipoPagamento.CARTAO_CREDITO),
                Conta.build(2L, 200.0, LocalDate.parse("2023-02-05"), TipoPagamento.TRANSFERENCIA)
        );
        Processador processador = new Processador(
                contas,
                fatura
        );

        processador.processar();

        assertEquals(FaturaStatus.PAGA, processador.getFatura().getStatus());
    }

    @Test
    void testCaso6Processador() {
        Fatura fatura = Fatura.build(
                "Rodrigo",
                1500.0,
                LocalDate.parse("2023-02-20"),
                FaturaStatus.PENDENTE,
                1L
        );
        List<Conta> contas = Arrays.asList(
                Conta.build(1L, 1000.0, LocalDate.parse("2023-02-05"), TipoPagamento.BOLETO),
                Conta.build(2L, 300.0, LocalDate.parse("2023-02-05"), TipoPagamento.CARTAO_CREDITO),
                Conta.build(2L, 200.0, LocalDate.parse("2023-02-05"), TipoPagamento.TRANSFERENCIA)
        );
        Processador processador = new Processador(
                contas,
                fatura
        );

        processador.processar();

        assertEquals(FaturaStatus.PAGA, processador.getFatura().getStatus());
    }

    @Test
    void testCaso7Processador() {
        Fatura fatura = Fatura.build(
                "Rodrigo",
                1500.0,
                LocalDate.parse("2023-02-20"),
                FaturaStatus.PENDENTE,
                1L
        );
        List<Conta> contas = Arrays.asList(
                Conta.build(1L, 1300.0, LocalDate.parse("2023-02-05"), TipoPagamento.BOLETO),
                Conta.build(2L, 100.0, LocalDate.parse("2023-02-05"), TipoPagamento.CARTAO_CREDITO)
        );
        Processador processador = new Processador(
                contas,
                fatura
        );

        processador.processar();

        assertEquals(FaturaStatus.PENDENTE, processador.getFatura().getStatus());
    }

    @Test
    void testCaso8Processador() {
        Fatura fatura = Fatura.build(
                "Rodrigo",
                1500.0,
                LocalDate.parse("2023-02-20"),
                FaturaStatus.PENDENTE,
                1L
        );
        List<Conta> contas = Arrays.asList(
                Conta.build(1L, 1300.0, LocalDate.parse("2023-02-05"), TipoPagamento.BOLETO),
                Conta.build(2L, 100.0, LocalDate.parse("2023-02-05"), TipoPagamento.TRANSFERENCIA)
        );
        Processador processador = new Processador(
                contas,
                fatura
        );

        processador.processar();

        assertEquals(FaturaStatus.PENDENTE, processador.getFatura().getStatus());
    }

    @Test
    void testCaso9Processador() {
        Fatura fatura = Fatura.build(
                "Rodrigo",
                1500.0,
                LocalDate.parse("2023-02-20"),
                FaturaStatus.PENDENTE,
                1L
        );
        List<Conta> contas = Arrays.asList(
                Conta.build(1L, 1300.0, LocalDate.parse("2023-02-05"), TipoPagamento.TRANSFERENCIA),
                Conta.build(2L, 150.0, LocalDate.parse("2023-02-05"), TipoPagamento.CARTAO_CREDITO)
        );
        Processador processador = new Processador(
                contas,
                fatura
        );

        processador.processar();

        assertEquals(FaturaStatus.PENDENTE, processador.getFatura().getStatus());
    }
}
