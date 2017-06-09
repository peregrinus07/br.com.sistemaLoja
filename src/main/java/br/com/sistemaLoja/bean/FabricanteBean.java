package br.com.sistemaLoja.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.sistemaLoja.domain.Fabricante;
import br.com.sistemaloja.dao.FabricanteDao;

@SuppressWarnings("serial")
@ManagedBean(name = "FabricanteBean")
@ViewScoped
public class FabricanteBean implements Serializable {

	private Fabricante fabricante;

	private List<Fabricante> fabricantes;

	public void salvar() throws Exception {

		FabricanteDao fabricanteDao = new FabricanteDao();
		fabricanteDao.merge(fabricante);

		novo();
		Messages.addGlobalInfo("Fabricante Salvo com Sucesso");

	}

	@PostConstruct
	public void listar() {
		try {

			FabricanteDao fabricanteDao = new FabricanteDao();
			fabricantes = fabricanteDao.listar();

		} catch (RuntimeException erro) {

			Messages.addGlobal(null, "Ocorreu um erro ao tentar listar os estados", null);

			erro.printStackTrace();
		}

	}

	public void novo() {

		fabricante = new Fabricante();
		FabricanteDao fabricanteDao = new FabricanteDao();
		fabricantes = fabricanteDao.listar();

	}

	public void excluir(ActionEvent evento) {

		try {
			fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");

			FabricanteDao fabricanteDao = new FabricanteDao();

			fabricanteDao.excluir(fabricante);

			novo();

			Messages.addGlobalInfo("Fabricante removido com sucesso");
		} catch (Exception erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Fabricante");
			erro.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {

		fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");

		Messages.addGlobalInfo("Nome: " + fabricante.getNome());
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

}
