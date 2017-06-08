package br.com.sistemaLoja.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity(name = "tb_funcionario")
public class Funcionario extends GenericDomain {
	
	@Column(nullable = false)
	private String carteiraDeTrabalho;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAdmissao;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pessoa pessoa;

}
