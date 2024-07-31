package contas.domain.strategies.pagamento;

import contas.domain.entities.Conta;
import contas.domain.entities.Fatura;
import contas.domain.entities.Pagamento;
import utils.AppError;

import java.util.Optional;

public class PagamentoTransferenciaStrategy implements PagamentoStrategy {
    private String tipo = "TRANSFERENCIA_BANCARIA";

    public String getTipo() {
        return tipo;
    }

    @Override
    public Optional<AppError> efetuarPagamento(Pagamento pagamento, Conta conta, Fatura fatura) {
        if (conta.getData().isAfter(fatura.getData())) {
            pagamento.zerar();
        }
        return Optional.empty();
    }
}