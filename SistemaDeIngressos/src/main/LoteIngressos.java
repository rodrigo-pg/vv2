package main;

import java.util.List;

public class LoteIngressos {

	private int id;
	private List<Ingresso> ingressos;
	private double desconto;

	public LoteIngressos(int id, List<Ingresso> ingressos, double desconto) {
		this(id, ingressos);
		this.desconto = desconto;
	}

	public LoteIngressos(int id, List<Ingresso> ingressos) {
		verificaDistribuicaoIngressos(ingressos);
		this.id = id;
		this.ingressos = ingressos;
	}

	private void verificaDistribuicaoIngressos(List<Ingresso> ingressos) {
		int totalIngressos = ingressos.size();
        int quantidadeVip = 0;
        int quantidadeMeiaEntrada = 0;
        int quantidadeNormal = 0;

        for (Ingresso ingresso : ingressos) {
            switch (ingresso.getTipo()) {
                case VIP:
                    quantidadeVip++;
                    break;
                case MEIA_ENTRADA:
                    quantidadeMeiaEntrada++;
                    break;
                case NORMAL:
                    quantidadeNormal++;
                    break;
            }
        }

        if (quantidadeVip < totalIngressos * 0.20 || quantidadeVip > totalIngressos * 0.30 ||
            quantidadeMeiaEntrada != totalIngressos * 0.10 ||
            quantidadeNormal != totalIngressos - quantidadeVip - quantidadeMeiaEntrada) {
            throw new IllegalArgumentException("Distribuição de ingressos está incorreta");
        }
		
	}

	public List<Ingresso> getIngressos() {
		return this.ingressos;
	}

	public void aplicarDesconto(double desconto) {
		verificaDesconto(desconto);
		for(Ingresso ingresso: this.ingressos) {
			ingresso.aplicarDesconto(desconto);
		}
		this.setDesconto(desconto);
		
	}

	private void verificaDesconto(double desconto) {
		if (desconto == 0.0) {
	        throw new IllegalArgumentException("O desconto não foi passado, informe um valor de desconto válido.");
	    }
		if(desconto < 0 || desconto > 25) {
			throw new IllegalArgumentException("Desconto não pode ser maior que 25% e menor que 0%");
		}
	}

	private void setDesconto(double desconto) {
		this.desconto = desconto;
		
	}

	public void adicionarIngresso(Ingresso ingressoNormal) {
		// TODO Auto-generated method stub
		
	}

	public Object getId() {
		return this.id;
	}

	public Object getDesconto() {
		return this.desconto;
	}

	public void aplicarDesconto() {
		if (this.desconto == 0.0) {
	        throw new IllegalArgumentException("O desconto não foi passado, informe um valor de desconto válido.");
	    }
		
		this.aplicarDesconto(this.desconto);
		
	}

}
