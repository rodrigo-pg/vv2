package main;

public class Ingresso {

	private int id;
    private TipoIngresso tipo;
    private StatusIngresso status;
    private static final double PRECO_NORMAL = 10.0;

	public Ingresso(int id, TipoIngresso tipo) {
		this.id = id;
        this.tipo = tipo;
        this.status = StatusIngresso.NAO_VENDIDO;
	}

	public Object getStatus() {
		return this.status;
	}

	public void marcarComoVendido() {
		this.status = StatusIngresso.VENDIDO;
		
	}

	public double getPreco() {
		switch (this.tipo) {
			case VIP:
				return PRECO_NORMAL * 2;
	        case MEIA_ENTRADA:
	            return PRECO_NORMAL / 2;
	        case NORMAL:
	        default:
	            return PRECO_NORMAL;
		
		}
	}

	public TipoIngresso getTipo() {
		return this.tipo;
	}

	public Object getId() {
		return this.id;
	}

	public void aplicarDesconto(int desconto) {
		if(this.tipo != TipoIngresso.MEIA_ENTRADA) {
			// falta implementar
			
		}
		
	}

}
