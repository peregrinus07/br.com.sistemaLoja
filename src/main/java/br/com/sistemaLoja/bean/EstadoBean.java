package br.com.sistemaLoja.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.sistemaLoja.domain.Estado;
import br.com.sistemaloja.dao.EstadoDao;

@SuppressWarnings("serial")
@ManagedBean(name = "estadoBean")
@ViewScoped
public class EstadoBean implements Serializable {

	private Estado estado;

	private List<Estado> estados;

	public void salvar() throws Exception {

		EstadoDao estadoDao = new EstadoDao();
		estadoDao.merge(estado);

		novo();
		Messages.addGlobalInfo("Estado Salvo com Sucesso");

	}

	@PostConstruct
	public void listar() {
		try {

			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listar();

		} catch (RuntimeException erro) { 

			Messages.addGlobal(null, "Ocorreu um erro ao tentar listar os estados");

			erro.printStackTrace();
		}

	}

	public void novo() {

		estado = new Estado();
		EstadoDao estadoDao = new EstadoDao();
		estados = estadoDao.listar();

	}

	public void excluir(ActionEvent evento) {

		try {
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");

			EstadoDao estadoDao = new EstadoDao();

			estadoDao.excluir(estado);

			novo();

			Messages.addGlobalInfo("Estado removido com sucesso");
		} catch (Exception erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Estado");
			erro.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {

		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
		
		Messages.addGlobalInfo("Nome: "+ estado.getNome());
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

}
