package contas;

import java.time.LocalDate;

public class Pagamento {
    private Double valor;
    private LocalDate data;
    private PagamentoStrategy strategy;
    private Long contaId;
    private Long faturaId;

    private Pagamento(
            Double valor,
            LocalDate data,
            PagamentoStrategy strategy,
            Long contaId,
            Long faturaId
    ) {
        this.valor = valor;
        this.data = data;
        this.strategy = strategy;
        this.faturaId = faturaId;
        this.contaId = contaId;
    }

    public Double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public PagamentoStrategy getStrategy() {
        return strategy;
    }

    public Long getContaId() {
        return contaId;
    }

    public Long getFaturaId() {
        return faturaId;
    }

    public void adicionarJuros(float porcentagem) {
        this.valor += this.valor * porcentagem;
    }

    public void efetuarPagamento(Conta conta, Fatura fatura) {
        this.strategy.efetuarPagamento(this, conta, fatura);
    }

    public void zerar() {
        this.valor = 0.0;
    }

    public static Pagamento build(
            Double valor,
            LocalDate data,
            PagamentoStrategy strategy,
            Long contaId,
            Long faturaId
    ) {
        return new Pagamento(
                valor,
                data,
                strategy,
                contaId,
                faturaId
        );
    }
}
