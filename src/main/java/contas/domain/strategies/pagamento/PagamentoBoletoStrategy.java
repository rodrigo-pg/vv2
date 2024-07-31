package contas.domain.strategies.pagamento;

import contas.domain.entities.Conta;
import contas.domain.entities.Fatura;
import contas.domain.entities.Pagamento;
import utils.AppError;

import java.util.Optional;

public class PagamentoBoletoStrategy implements PagamentoStrategy {
    private String tipo = "BOLETO";

    public String getTipo() {
        return tipo;
    }

    @Override
    public Optional<AppError> efetuarPagamento(Pagamento pagamento, Conta conta, Fatura fatura) {
        Double valorPagamento = pagamento.getValor();
        if (pagamento.getValor() < 0.01) {
            return Optional.of(new AppError("Pagamento por boleto não pode ser menor que R$ 0,01"));
        }

        if (pagamento.getValor() > 5000.0) {
            return Optional.of(new AppError("Pagamento por boleto não pode ser maior que R$ 5.000,00"));
        }

        if (pagamento.getData().isAfter(conta.getData())) {
            pagamento.adicionarJuros(0.1f);
        }

        return Optional.empty();
    }
}
