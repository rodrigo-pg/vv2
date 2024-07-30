package contas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContaTest {

    @Test
    @DisplayName("Dados atributos válidos de uma conta, ela deveria ser instanciada sem problemas")
    void testCriacaoConta() {
        Conta conta = Conta.build(
                1L,
                500.0
        );

        assertInstanceOf(Conta.class, conta);
    }
}