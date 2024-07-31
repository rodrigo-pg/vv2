package contas;

import java.util.List;
import java.util.stream.Collectors;

public class Processador {
    private List<Conta> contas;
    private Fatura fatura;

    public Processador(
            List<Conta> contas,
            Fatura fatura
    ) {
        this.contas = contas;
        this.fatura = fatura;
    }

    public Fatura getFatura() {
        return fatura;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void processar() {
        double soma = contas.stream().mapToDouble(conta -> {
            PagamentoStrategy pagamentoStrategy = PagamentoStrategyFactory.create(conta.getMeioDePagamento());
            Pagamento pagamento = Pagamento.build(
                    conta.getValorPago(),
                    conta.getData(),
                    pagamentoStrategy,
                    conta.getId(),
                    1L
            );
            pagamento.efetuarPagamento(conta, fatura);
            return pagamento.getValor();
        }).sum();

        if (soma >= fatura.getTotal()) {
            fatura.pagar();
        }
    }

}