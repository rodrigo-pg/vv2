package contas;

public class Pagamento {
    private Double valor;

    private Pagamento(
            Double valor
    ) {
        this.valor = valor;
    }

    public Double getValor() {
        return valor;
    }

    public static Pagamento build(
            Double valor
    ) {
        return new Pagamento(
                valor
        );
    }
}
