package main.java.utils;

public class Validator {
	
	public void validarDesconto(double desconto) {
		if(desconto < 0 || desconto > 25) {
			throw new IllegalArgumentException("Desconto não pode ser maior que 25% e menor que 0%");
		}
    }

}
