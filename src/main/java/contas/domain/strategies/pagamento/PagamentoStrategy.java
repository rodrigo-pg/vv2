package contas.domain.strategies.pagamento;

import contas.domain.entities.Conta;
import contas.domain.entities.Fatura;
import contas.domain.entities.Pagamento;
import utils.AppError;

import java.util.Optional;

public interface PagamentoStrategy {
    Optional<AppError> efetuarPagamento(Pagamento pagamento, Conta conta, Fatura fatura);
}
