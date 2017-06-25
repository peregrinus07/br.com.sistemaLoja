package br.com.sistemaLoja.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sistemaLoja.domain.Caixa;
import br.com.sistemaloja.dao.CaixaDao;

@ManagedBean(name = "fluxoDeCaixaBean")
@ViewScoped
public class FluxoDeCaixaBean {

	private Caixa caixa;
	private List<Caixa> listaDeCaixas;

	@PostConstruct
	public void listar() {

		CaixaDao caixaDao = new CaixaDao();
		listaDeCaixas = caixaDao.listar();
		
		
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public List<Caixa> getListaDeCaixas() {
		return listaDeCaixas;
	}

	public void setListaDeCaixas(List<Caixa> listaDeCaixas) {
		this.listaDeCaixas = listaDeCaixas;
	}

}
