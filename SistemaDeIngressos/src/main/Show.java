package main;

import java.util.HashMap;
import java.util.Map;

import main.enums.StatusFinanceiro;
import main.enums.StatusIngresso;
import main.enums.TipoIngresso;

public class Show {

	private String data;
	private String artista;
	private double cache;
	private double despesasInfraestrutura;
	private LoteIngressos loteIngressos;
	private boolean isDataEspecial;
	
	public Show(String data, String artista, double cache, double despesasInfraestrutura, LoteIngressos loteIngressos, boolean isDataEspecial) {
		this.data = data;
		this.artista = artista;
		this.cache = cache;
		this.despesasInfraestrutura = despesasInfraestrutura;
		this.loteIngressos = loteIngressos;
		this.isDataEspecial = isDataEspecial;
	}


	public void setData(String data) {
		this.data = data;
	}

	public void setCache(double cache) {
		this.cache = cache;
		
	}

	public void setDespesasInfraestrutura(double despesas) {
		this.despesasInfraestrutura = despesas;
		
	}

	public void setDataEspecial(boolean isDataEspecial) {
		this.isDataEspecial = isDataEspecial;
		
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getData() {
		return this.data;
	}

	public String getArtista() {
		return this.artista;
	}

	public Object getCache() {
		return this.cache;
	}

	public Object getDespesasInfraestrutura() {
		return this.despesasInfraestrutura;
	}

	public Boolean isDataEspecial() {
		return this.isDataEspecial;
	}


	public Object getLoteIngressos() {
		// TODO Auto-generated method stub
		return null;
	}


	public Relatorio gerarRelatorio() {
		Map<TipoIngresso, Integer> numIngressosVendidos = calculaNumeroIngressosVendidos();
		Double receitaLiquida = calculaReceitaLiquida();
		StatusFinanceiro status = calculaStatus(receitaLiquida);
		Relatorio relatorio = new Relatorio(numIngressosVendidos, receitaLiquida, status);
		return relatorio;
	}


	private StatusFinanceiro calculaStatus(Double receitaLiquida) {
		if(receitaLiquida > 0) {
			return StatusFinanceiro.LUCRO;
		} else if(receitaLiquida == 0) {
			return StatusFinanceiro.ESTÁVEL;
		} else {
			return StatusFinanceiro.PREJUÍZO;
		}
	}


	private double calculaReceitaLiquida() {
		double somaPreco = 0.0;
		for(Ingresso ingresso: this.loteIngressos.getIngressos()) {
			if(ingresso.getStatus() == StatusIngresso.VENDIDO) {
				somaPreco += ingresso.getPrecoComDesconto();
			}
		}
		
		double receitaBruta = somaPreco - this.cache;
		
		if(this.isDataEspecial) {
			receitaBruta -= (this.despesasInfraestrutura + (this.despesasInfraestrutura*0.15));
		} else {
			receitaBruta -= this.despesasInfraestrutura;
		}
		
		System.out.println(receitaBruta);
		
		return receitaBruta;
		
	}


	private Map<TipoIngresso, Integer> calculaNumeroIngressosVendidos() {
		int ingressosVendidosVIP = 0;
        int ingressosVendidosMeiaEntrada = 0;
        int ingressosVendidosNormal = 0;
        Map<TipoIngresso, Integer> numIngressosVendidos = new HashMap<>();
        
		for(Ingresso ingresso: this.loteIngressos.getIngressos()) {
			if(ingresso.getStatus() == StatusIngresso.VENDIDO) {
				switch(ingresso.getTipo()) {
					case VIP:
	                    ingressosVendidosVIP++;
	                    break;
	                case MEIA_ENTRADA:
	                    ingressosVendidosMeiaEntrada++;
	                    break;
	                case NORMAL:
	                    ingressosVendidosNormal++;
	                    break;
				}
			}
		}
		
		numIngressosVendidos.put(TipoIngresso.VIP, ingressosVendidosVIP);
		numIngressosVendidos.put(TipoIngresso.MEIA_ENTRADA, ingressosVendidosMeiaEntrada);
		numIngressosVendidos.put(TipoIngresso.NORMAL, ingressosVendidosNormal);
		
		return numIngressosVendidos;
		
	}


	public void adicionarLote(LoteIngressos lote) {
		this.loteIngressos = lote;
		
	}

}
