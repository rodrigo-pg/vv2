package contas;

import contas.domain.entities.Conta;
import contas.domain.enums.TipoPagamento;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ContaTest {

    @Test
    @DisplayName("Dados atributos válidos de uma conta, ela deveria ser instanciada sem problemas")
    void testCriacaoConta() {
        LocalDate data = LocalDate.parse("2024-05-25");
        Conta conta = Conta.build(
                1L,
                500.0,
                data,
                TipoPagamento.BOLETO
        );

        assertInstanceOf(Conta.class, conta);
    }
}