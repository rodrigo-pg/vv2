package contas;

public class PagamentoCCStrategy implements PagamentoStrategy {
    private String tipo = "CARTAO_CREDITO";

    public String getTipo() {
        return tipo;
    }

    @Override
    public void efetuarPagamento() {

    }
}
