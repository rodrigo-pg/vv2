package main.java.model;

import main.java.enums.StatusIngresso;
import main.java.enums.TipoIngresso;
import main.java.utils.Validator;

public class Ingresso {

	private int id;
    private TipoIngresso tipo;
    private StatusIngresso status;
	private double precoBase;
	private double precoDesconto;
	private Validator validator;

	public Ingresso(int id, TipoIngresso tipo, double precoBase) {
		this(id, tipo, StatusIngresso.NAO_VENDIDO, precoBase);
	}

	public Ingresso(int id, TipoIngresso tipo, StatusIngresso vendido, double precoBase) {
		this.id = id;
        this.tipo = tipo;
        this.status = vendido;
        this.precoBase = precoBase;
        this.precoDesconto = this.getPreco();
        this.validator = new Validator();
	}

	public StatusIngresso getStatus() {
		return this.status;
	}

	public void marcarComoVendido() {
		this.status = StatusIngresso.VENDIDO;
	}
	
	public void marcarComoNaoVendido() {
        this.status = StatusIngresso.NAO_VENDIDO;
    }

	public double getPreco() {
		switch (this.tipo) {
			case VIP:
				return precoBase * 2;
	        case MEIA_ENTRADA:
	            return precoBase / 2;
	        case NORMAL:
	        default:
	            return precoBase;
		
		}
	}

	public TipoIngresso getTipo() {
		return this.tipo;
	}

	public int getId() {
		return this.id;
	}

	public double getPrecoComDesconto() {
		return this.precoDesconto;
	}

	public void aplicarDesconto(double desconto) {
		this.validator.validarDesconto(desconto);
		if(this.tipo != TipoIngresso.MEIA_ENTRADA) {
			double precoAtual = this.getPreco();
	
			this.precoDesconto = precoAtual - (precoAtual*desconto/100);
		}
		
	}


}
