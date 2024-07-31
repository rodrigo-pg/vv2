package contas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class FaturaTest {

    @Test
    @DisplayName("Dados os atributos válidos de uma fatura, ela deveria ser criada sem problemas ")
    void testCriacaoFatura() {
        LocalDate data = LocalDate.parse("2024-05-25");
        Fatura fatura = Fatura.build(
                "Rodrigo",
                205.90,
                data,
                FaturaStatus.PENDENTE,
                1L
        );

        assertInstanceOf(Fatura.class, fatura);
    }
}