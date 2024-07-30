package contas;

import utils.AppError;

import java.util.Optional;

public class PagamentoBoletoStrategy implements PagamentoStrategy{
    private String tipo = "BOLETO";

    public String getTipo() {
        return tipo;
    }

    @Override
    public Optional<AppError> efetuarPagamento(Pagamento pagamento, Conta conta) {
        Double valorPagamento = pagamento.getValor();
        if (pagamento.getValor() < 0.01) {
            return Optional.of(new AppError("Pagamento por boleto não pode ser menor que R$ 0,01"));
        }

        return Optional.empty();
    }
}
