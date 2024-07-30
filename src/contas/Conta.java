package contas;

public class Conta {
    private Long id;
    private Double saldo;

    private Conta(
            Long id,
            Double saldo
    ) {
        this.id = id;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public Double getSaldo() {
        return saldo;
    }

    public static Conta build(
            Long id,
            Double saldo
    ) {
        return new Conta(
                id,
                saldo
        );
    }
}
