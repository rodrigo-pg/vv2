package main;

public class Ingresso {

	private int id;
    private TipoIngresso tipo;
    private StatusIngresso status;

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

	public Object getPreco() {
		// TODO Auto-generated method stub
		return null;
	}

}
