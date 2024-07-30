package contas;

import java.time.LocalDate;

public class Conta {
    private Long id;
    private Double valorPago;
    private LocalDate data;

    private Conta(
            Long id,
            Double valorPago,
            LocalDate data
    ) {
        this.id = id;
        this.valorPago = valorPago;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public Double getSaldo() {
        return valorPago;
    }

    public LocalDate getData() {
        return data;
    }

    public static Conta build(
            Long id,
            Double valorPago,
            LocalDate data
    ) {
        return new Conta(
                id,
                valorPago,
                data
        );
    }
}
