package contas;

import java.time.LocalDate;
import java.util.Date;

public class Fatura {
    private String nome;
    private Double total;
    private LocalDate data;

    private Fatura(
            String nome,
            Double total,
            LocalDate data
    ) {
        this.nome = nome;
        this.total = total;
        this.data = data;
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

    static Fatura build(
            String nome,
            Double total,
            LocalDate data
    ) {
        return new Fatura(
                nome,
                total,
                data
        );
    }
}
