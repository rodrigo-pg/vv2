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
                data
        );
        List<Conta> contas = Arrays.asList(
                Conta.build(1L, 300.0, data),
                Conta.build(2L, 500.0, data),
                Conta.build(3L, 650.0, data)
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
                data
        );
        List<Conta> contas = Arrays.asList(
                Conta.build(1L, 300.0, data),
                Conta.build(2L, 500.0, data),
                Conta.build(3L, 650.0, data)
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
                data
        );
        List<Conta> contas = Arrays.asList(
                Conta.build(1L, 300.0, data),
                Conta.build(2L, 500.0, data),
                Conta.build(3L, 650.0, data)
        );
        Processador processador = new Processador(
                contas,
                fatura
        );

        assertEquals(processador.getFatura(), fatura);
    }
}