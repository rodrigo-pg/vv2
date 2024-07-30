package contas;

import java.util.List;

public class Processador {
    private List<Conta> contas;

    private Processador(
            List<Conta> contas
    ) {
        this.contas = contas;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public static Processador build(List<Conta> contas) {
        return new Processador(contas);
    }

}
