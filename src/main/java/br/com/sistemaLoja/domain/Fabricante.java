package br.com.sistemaLoja.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity(name = "tb_fabricante")
public class Fabricante extends GenericDomain {

	@Column(length = 200, nullable = false)
	private String descricao;

	@Column(length = 50, nullable = false)
	private String nome;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
