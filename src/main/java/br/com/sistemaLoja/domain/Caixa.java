package br.com.sistemaLoja.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table (name ="tb_caixa")
public class Caixa extends GenericDomain {
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDeAbertura;
	
	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDeFeichamento;
	
	@Column (nullable = false, precision = 7 , scale = 2)
	private BigDecimal valor;
	
	

	public Date getDataDeAbertura() {
		return dataDeAbertura;
	}

	public void setDataDeAbertura(Date dataDeAbertura) {
		this.dataDeAbertura = dataDeAbertura;
	}

	public Date getDataDeFeichamento() {
		return dataDeFeichamento;
	}

	public void setDataDeFeichamento(Date dataDeFeichamento) {
		this.dataDeFeichamento = dataDeFeichamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
	

}
