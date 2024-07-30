package contas;

public class PagamentoTransferenciaStrategy implements PagamentoStrategy {
    private String tipo = "TRANSFERENCIA_BANCARIA";

    public String getTipo() {
        return tipo;
    }

    @Override
    public void efetuarPagamento() {

    }
}