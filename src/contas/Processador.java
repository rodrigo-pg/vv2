package contas;

import java.util.List;

public class Processador {
    private List<Conta> contas;
    private Fatura fatura;

    public Processador(
            List<Conta> contas,
            Fatura fatura
    ) {
        this.contas = contas;
        this.fatura = fatura;
    }

    public Fatura getFatura() {
        return fatura;
    }

    public List<Conta> getContas() {
        return contas;
    }

}
