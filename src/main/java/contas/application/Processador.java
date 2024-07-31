package contas.application;

import contas.domain.entities.Conta;
import contas.domain.entities.Fatura;
import contas.domain.entities.Pagamento;
import contas.domain.strategies.pagamento.PagamentoStrategy;
import contas.domain.factories.PagamentoStrategyFactory;

import java.util.List;

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
                    fatura.getId()
            );
            pagamento.efetuarPagamento(conta, fatura);
            return pagamento.getValor();
        }).sum();

        if (soma >= fatura.getTotal()) {
            fatura.pagar();
        }
    }

}
