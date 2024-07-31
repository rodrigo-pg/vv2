package contas.domain.factories;

import contas.domain.enums.TipoPagamento;
import contas.domain.strategies.pagamento.PagamentoBoletoStrategy;
import contas.domain.strategies.pagamento.PagamentoCCStrategy;
import contas.domain.strategies.pagamento.PagamentoStrategy;
import contas.domain.strategies.pagamento.PagamentoTransferenciaStrategy;

public class PagamentoStrategyFactory {

    public static PagamentoStrategy create(TipoPagamento tipo) {
        return switch (tipo) {
            case CARTAO_CREDITO -> new PagamentoCCStrategy();
            case TRANSFERENCIA -> new PagamentoTransferenciaStrategy();
            default -> new PagamentoBoletoStrategy();
        };
    }
}
