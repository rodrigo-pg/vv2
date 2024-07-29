package main;

import java.util.List;

public class Show {

	private String data;
	private String artista;
	private double cache;
	private double despesasInfraestrutura;
	private List loteIngressos;
	private boolean isDataEspecial;
	
	public Show(String data, String artista, double cache, double despesasInfraestrutura, List loteIngressos, boolean isDataEspecial) {
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

}
