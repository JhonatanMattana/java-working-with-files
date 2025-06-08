package br.com.java.excecao;

public class ErroDeConversaoDeAnoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String mensagem;
	
	public ErroDeConversaoDeAnoException(String mesagem) {
		this.mensagem = mesagem;
	}
	
	@Override
	public String getMessage() {
		return this.mensagem;
	}
	
}