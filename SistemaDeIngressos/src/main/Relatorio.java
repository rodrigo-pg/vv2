package main;

import java.util.Map;

import main.enums.StatusFinanceiro;
import main.enums.TipoIngresso;


public class Relatorio {

	private Map<TipoIngresso, Integer> numIngressosVendidos;
	private Double receitaLiquida;
	private StatusFinanceiro status;

	public Relatorio(Map<TipoIngresso, Integer> numIngressosVendidos, Double receitaLiquida, StatusFinanceiro status) {
		this.numIngressosVendidos = numIngressosVendidos;
		this.receitaLiquida = receitaLiquida;
		this.status = status;
	}

	public double getReceitaLiquida() {
		return this.receitaLiquida;
	}

	public StatusFinanceiro getStatusFinanceiro() {
		return this.status;
	}

	public int getNumeroIngressosVIP() {
		return this.numIngressosVendidos.get(TipoIngresso.VIP);
	}

	public int getNumeroIngressosMeiaEntrada() {
		return this.numIngressosVendidos.get(TipoIngresso.MEIA_ENTRADA);
	}

	public int getNumeroIngressosNormal() {
		return this.numIngressosVendidos.get(TipoIngresso.NORMAL);
	}

}
