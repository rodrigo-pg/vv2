package contas;

import java.time.LocalDate;

public class Pagamento {
    private Double valor;
    private LocalDate data;

    private Pagamento(
            Double valor,
            LocalDate data
    ) {
        this.valor = valor;
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public static Pagamento build(
            Double valor,
            LocalDate data
    ) {
        return new Pagamento(
                valor,
                data
        );
    }
}
