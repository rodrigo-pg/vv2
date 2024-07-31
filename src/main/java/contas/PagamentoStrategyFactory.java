package contas;

public class PagamentoStrategyFactory {

    public static PagamentoStrategy create(TipoPagamento tipo) {
        return switch (tipo) {
            case CARTAO_CREDITO -> new PagamentoCCStrategy();
            case TRANSFERENCIA -> new PagamentoTransferenciaStrategy();
            default -> new PagamentoBoletoStrategy();
        };
    }
}
