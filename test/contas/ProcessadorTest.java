package contas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcessadorTest {

    @Test
    @DisplayName("Dado atributos válidos e requeridos, quando o processador fosse instanciado, a criação deveria ocorrer sem problemas")
    void testCriacaoProcessador() {
        List<Conta> contas = Arrays.asList(
                Conta.build(1L, 300.0),
                Conta.build(2L, 500.0),
                Conta.build(3L, 650.0)
        );
        Processador processador = new Processador(contas);

        assertInstanceOf(Processador.class, processador);
    }

    @Test
    @DisplayName("Dado que o processador foi criado com uma lista de contas, quando as contas fossem consultadas, deveriam ser as mesmas da criação")
    void testContasProcessador() {
        List<Conta> contas = Arrays.asList(
                Conta.build(1L, 300.0),
                Conta.build(2L, 500.0),
                Conta.build(3L, 650.0)
        );
        Processador processador = new Processador(
                contas
        );

        assertEquals(processador.getContas(), contas);
    }
}