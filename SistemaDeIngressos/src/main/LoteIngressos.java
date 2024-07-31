package main;

import java.util.List;

import main.utils.Validator;

public class LoteIngressos {

	private int id;
	private List<Ingresso> ingressos;
	private double desconto;
	private Validator validator;

	public LoteIngressos(int id, List<Ingresso> ingressos, double desconto) {
		this(id, ingressos);
		this.desconto = desconto;
	}

	public LoteIngressos(int id, List<Ingresso> ingressos) {
		verificaDistribuicaoIngressos(ingressos);
		this.id = id;
		this.ingressos = ingressos;
		this.validator = new Validator();
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
		this.validator.validarDesconto(desconto);
		for(Ingresso ingresso: this.ingressos) {
			ingresso.aplicarDesconto(desconto);
		}
		this.setDesconto(desconto);
		
	}

	private void setDesconto(double desconto) {
		this.desconto = desconto;
		
	}

	public int getId() {
		return this.id;
	}

	public double getDesconto() {
		return this.desconto;
	}

	public void aplicarDesconto() {
		if (this.desconto == 0.0) {
	        throw new IllegalArgumentException("O desconto não foi passado, informe um valor de desconto válido.");
	    }
		
		this.aplicarDesconto(this.desconto);
		
	}

}
