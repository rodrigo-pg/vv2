package contas;

import java.time.LocalDate;

public class Pagamento {
    private Double valor;
    private LocalDate data;
    private PagamentoStrategy strategy;

    private Pagamento(
            Double valor,
            LocalDate data,
            PagamentoStrategy strategy
    ) {
        this.valor = valor;
        this.data = data;
        this.strategy = strategy;
    }

    public Double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public static Pagamento build(
            Double valor,
            LocalDate data,
            PagamentoStrategy strategy
    ) {
        return new Pagamento(
                valor,
                data,
                strategy
        );
    }
}
