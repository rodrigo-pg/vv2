package contas;

import utils.AppError;

import java.util.Optional;

public class PagamentoCCStrategy implements PagamentoStrategy {
    private String tipo = "CARTAO_CREDITO";

    public String getTipo() {
        return tipo;
    }

    @Override
    public Optional<AppError> efetuarPagamento(Pagamento pagamento, Conta conta) {
        return Optional.empty();
    }
}
