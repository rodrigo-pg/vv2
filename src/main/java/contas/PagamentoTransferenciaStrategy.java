package contas;

import utils.AppError;

import java.time.temporal.ChronoUnit;
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