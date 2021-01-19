package br.com.poc.micronaut.model;

public class TransacaoModel {

	private String descricao;
	private long data;
	private int valor;
	private boolean duplicated;
	
	public TransacaoModel(String descricao, long data, int valor) {
		this.descricao = descricao;
		this.data = data;
		this.valor = valor;
	}
	
	public TransacaoModel() {
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public long getData() {
		return data;
	}
	public void setData(long data) {
		this.data = data;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public boolean isDuplicated() {
		return duplicated;
	}
	public void setDuplicated(boolean duplicated) {
		this.duplicated = duplicated;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return descricao + " " + valor + " " + data + " " + duplicated;
	}
	
}
