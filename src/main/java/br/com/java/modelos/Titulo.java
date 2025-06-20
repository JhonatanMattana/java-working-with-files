package br.com.java.modelos;

import com.google.gson.annotations.SerializedName;

import br.com.java.excecao.ErroDeConversaoDeAnoException;

public class Titulo implements Comparable<Titulo> {
	@SerializedName(value = "Title")
    private String nome;
	
	@SerializedName(value = "Year")
    private int anoDeLancamento;
    
	private boolean incluidoNoPlano;
    
	private double somaDasAvaliacoes;
    
	private int totalDeAvaliacoes;
    
	private int duracaoEmMinutos;

    public Titulo(String nome, int anoDeLancamento) {
        this.nome = nome;
        this.anoDeLancamento = anoDeLancamento;
    }

    public Titulo(TituloOmdb tituloOmdb) {
    	this.nome = tituloOmdb.title();
    	if (tituloOmdb.year().length() > 4) {
			throw new ErroDeConversaoDeAnoException("Não consegui converter o ano, poreque tem mais de 04 caracteres.");
		}
    	this.anoDeLancamento = Integer.valueOf(tituloOmdb.year());
    	this.duracaoEmMinutos = Integer.valueOf(tituloOmdb.runtime().substring(0, 2));
    }

	public String getNome() {
        return nome;
    }

    public int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public boolean isIncluidoNoPlano() {
        return incluidoNoPlano;
    }

    public int getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    public int getTotalDeAvaliacoes() {
        return totalDeAvaliacoes;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAnoDeLancamento(int anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public void setIncluidoNoPlano(boolean incluidoNoPlano) {
        this.incluidoNoPlano = incluidoNoPlano;
    }

    public void setDuracaoEmMinutos(int duracaoEmMinutos) {
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    public void exibeFichaTecnica(){
        System.out.println("Nome do filme: " + nome);
        System.out.println("Ano de lançamento: " + anoDeLancamento);
    }

    public void avalia(double nota){
        somaDasAvaliacoes += nota;
        totalDeAvaliacoes++;
    }

    public double pegaMedia(){
        return somaDasAvaliacoes / totalDeAvaliacoes;
    }

    @Override
    public int compareTo(Titulo outroTitulo) {
        return this.getNome().compareTo(outroTitulo.getNome());
    }
}