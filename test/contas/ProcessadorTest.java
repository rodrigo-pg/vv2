package contas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcessadorTest {

    @Test
    @DisplayName("Dado atributos válidos e requeridos, quando o processador fosse instanciado, a criação deveria ocorrer sem problemas")
    void testCriacaoProcessador() {
        LocalDate data = LocalDate.parse("2024-05-25");
        Fatura fatura = Fatura.build(
                "Rodrigo",
                205.90,
                data,
                "PENDENTE"
        );
        List<Conta> contas = Arrays.asList(
                Conta.build(1L, 300.0, data, TipoPagamento.BOLETO),
                Conta.build(2L, 500.0, data, TipoPagamento.BOLETO),
                Conta.build(3L, 650.0, data, TipoPagamento.BOLETO)
        );
        Processador processador = new Processador(contas, fatura);

        assertInstanceOf(Processador.class, processador);
    }

    @Test
    @DisplayName("Dado que uma lista de contas foi fornecida para a processador, quando ele fosse criado, as contas deveriam iguais as fornecidas")
    void testContasProcessador() {
        LocalDate data = LocalDate.parse("2024-05-25");
        Fatura fatura = Fatura.build(
                "Rodrigo",
                205.90,
                data,
                "PENDENTE"
        );
        List<Conta> contas = Arrays.asList(
                Conta.build(1L, 300.0, data, TipoPagamento.BOLETO),
                Conta.build(2L, 500.0, data, TipoPagamento.BOLETO),
                Conta.build(3L, 650.0, data, TipoPagamento.BOLETO)
        );
        Processador processador = new Processador(
                contas,
                fatura
        );

        assertEquals(processador.getContas(), contas);
    }

    @Test
    @DisplayName("Dado que uma fatura foi fornecida para o processador, quando ele fosse criado, a fatura deveria ser igual a fornecida")
    void testFaturaProcessador() {
        LocalDate data = LocalDate.parse("2024-05-25");
        Fatura fatura = Fatura.build(
                "Rodrigo",
                205.90,
                data,
                "PENDENTE"
        );
        List<Conta> contas = Arrays.asList(
                Conta.build(1L, 300.0, data, TipoPagamento.BOLETO),
                Conta.build(2L, 500.0, data, TipoPagamento.BOLETO),
                Conta.build(3L, 650.0, data, TipoPagamento.BOLETO)
        );
        Processador processador = new Processador(
                contas,
                fatura
        );

        assertEquals(processador.getFatura(), fatura);
    }

    @Test
    void testCaso1Processador() {
        Fatura fatura = Fatura.build(
                "Rodrigo",
                1500.0,
                LocalDate.parse("2023-02-20"),
                "PENDENTE"
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

        assertEquals("PAGA", processador.getFatura().getStatus());
    }

    @Test
    void testCaso2Processador() {
        Fatura fatura = Fatura.build(
                "Rodrigo",
                1500.0,
                LocalDate.parse("2023-02-20"),
                "PENDENTE"
        );
        List<Conta> contas = Arrays.asList(
                Conta.build(1L, 700.0, LocalDate.parse("2023-02-05"), TipoPagamento.CARTAO_CREDITO),
                Conta.build(2L, 800.0, LocalDate.parse("2023-02-17"), TipoPagamento.TRANSFERENCIA)
        );
        Processador processador = new Processador(
                contas,
                fatura
        );

        processador.processar();

        assertEquals("PAGA", processador.getFatura().getStatus());
    }

    @Test
    void testCaso3Processador() {
        Fatura fatura = Fatura.build(
                "Rodrigo",
                1500.0,
                LocalDate.parse("2023-02-20"),
                "PENDENTE"
        );
        List<Conta> contas = Arrays.asList(
                Conta.build(1L, 700.0, LocalDate.parse("2023-02-06"), TipoPagamento.CARTAO_CREDITO),
                Conta.build(2L, 800.0, LocalDate.parse("2023-02-17"), TipoPagamento.TRANSFERENCIA)
        );
        Processador processador = new Processador(
                contas,
                fatura
        );

        processador.processar();

        assertEquals("PENDENTE", processador.getFatura().getStatus());
    }
}