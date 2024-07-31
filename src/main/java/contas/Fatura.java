package contas;

import java.time.LocalDate;
import java.util.Date;

public class Fatura {
    private String nome;
    private Double total;
    private LocalDate data;
    private FaturaStatus status = FaturaStatus.PENDENTE;

    private Fatura(
            String nome,
            Double total,
            LocalDate data,
            FaturaStatus status
    ) {
        this.nome = nome;
        this.total = total;
        this.data = data;
        this.status = status;
    }

    public FaturaStatus getStatus() {
        return status;
    }

    public LocalDate getData() {
        return data;
    }

    public Double getTotal() {
        return total;
    }

    public String getNome() {
        return nome;
    }

    public void pagar() {
        this.status = FaturaStatus.PAGA;
    }

    static Fatura build(
            String nome,
            Double total,
            LocalDate data,
            FaturaStatus status
    ) {
        return new Fatura(
                nome,
                total,
                data,
                status
        );
    }
}
