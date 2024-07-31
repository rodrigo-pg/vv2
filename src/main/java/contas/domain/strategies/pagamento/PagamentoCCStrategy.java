package contas.domain.strategies.pagamento;

import contas.domain.entities.Conta;
import contas.domain.entities.Fatura;
import contas.domain.entities.Pagamento;
import utils.AppError;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class PagamentoCCStrategy implements PagamentoStrategy {
    private String tipo = "CARTAO_CREDITO";

    public String getTipo() {
        return tipo;
    }

    @Override
    public Optional<AppError> efetuarPagamento(Pagamento pagamento, Conta conta, Fatura fatura) {
        if (ChronoUnit.DAYS.between(conta.getData(), fatura.getData()) < 15 || conta.getData().isAfter(fatura.getData())) {
            pagamento.zerar();
        }
        return Optional.empty();
    }
}
