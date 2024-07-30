package contas;

import java.util.List;

public class Processador {
    private List<Conta> contas;

    public Processador(
            List<Conta> contas
    ) {
        this.contas = contas;
    }

    public List<Conta> getContas() {
        return contas;
    }

}
