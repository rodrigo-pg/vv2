package contas;

import utils.AppError;

import java.util.Optional;

public interface PagamentoStrategy {
    Optional<AppError> efetuarPagamento(Pagamento pagamento, Conta conta);
}
