package contas;

public class PagamentoBoletoStrategy implements PagamentoStrategy{
    private String tipo = "BOLETO";

    public String getTipo() {
        return tipo;
    }

    @Override
    public void efetuarPagamento() {

    }
}
