package main;

import java.util.List;

public class LoteIngressos {

	private int id;
	private List<Ingresso> ingressos;
	private double desconto;

	public LoteIngressos(int id, List<Ingresso> ingressos, double desconto) {
		verificaDistribuicaoIngressos(ingressos);
		this.id = id;
		this.ingressos = ingressos;
		this.desconto = desconto;
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

	public void aplicarDesconto(int desconto) {
		for(Ingresso ingresso: this.ingressos) {
			ingresso.aplicarDesconto(desconto);
		}
		
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

}