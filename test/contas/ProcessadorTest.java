package contas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessadorTest {

    @Test
    @DisplayName("Dado atributos válidos e requeridos, quando o processador fosse instanciado, a criação deveria ocorrer sem problemas")
    void testCriacaoProcessador() {
        Processador processador = new Processador();

        assertInstanceOf(Processador.class, processador);
    }
}