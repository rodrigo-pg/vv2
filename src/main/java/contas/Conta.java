package contas;

import java.time.LocalDate;

public class Conta {
    private Long id;
    private Double valorPago;
    private LocalDate data;
    private TipoPagamento meioDePagamento;

    private Conta(
            Long id,
            Double valorPago,
            LocalDate data,
            TipoPagamento meioDePagamento
    ) {
        this.id = id;
        this.valorPago = valorPago;
        this.data = data;
        this.meioDePagamento = meioDePagamento;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public TipoPagamento getMeioDePagamento() {
        return meioDePagamento;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public static Conta build(
            Long id,
            Double valorPago,
            LocalDate data,
            TipoPagamento meioDePagamento
    ) {
        return new Conta(
                id,
                valorPago,
                data,
                meioDePagamento
        );
    }
}
