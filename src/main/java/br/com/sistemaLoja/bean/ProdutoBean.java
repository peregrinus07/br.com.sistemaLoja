package br.com.sistemaLoja.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;

import br.com.sistemaLoja.domain.Fabricante;
import br.com.sistemaLoja.domain.Produto;
import br.com.sistemaloja.dao.FabricanteDao;
import br.com.sistemaloja.dao.ProdutoDao;

@SuppressWarnings("serial")
@ManagedBean(name = "ProdutoBean")
@ViewScoped
public class ProdutoBean implements Serializable {

	private Produto produto;
	private List<Produto> produtos;
	private List<Fabricante> fabricantes;

	@PostConstruct
	public void listar() {

		produto = new Produto();

		try {

			ProdutoDao ProdutoDao = new ProdutoDao();
			produtos = ProdutoDao.listar();

			FabricanteDao FabricanteDao = new FabricanteDao();
			fabricantes = FabricanteDao.listar();
		} catch (RuntimeException erro) {

			Messages.addFlashError("Ocorreu um erro ao tentar listar os produtos", null, null);
			erro.printStackTrace();
		}

	}

	public void novo() {

		try {

			FabricanteDao FabricanteDao = new FabricanteDao();
			fabricantes = FabricanteDao.listar();
		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar um novo Produto");
			erro.printStackTrace();
		}
	}

	public void salvar() throws Exception {

		try {

			ProdutoDao ProdutoDao = new ProdutoDao();
			ProdutoDao.merge(produto);

			// limpando os objetos
			produto = new Produto();

			produtos = ProdutoDao.listar();

			FabricanteDao FabricanteDao = new FabricanteDao();
			fabricantes = FabricanteDao.listar();

			Messages.addGlobalInfo("Produto salvo com sucesso");

		} catch (RuntimeException e) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar um novo produtos");
		}

	}

	public void excluir(ActionEvent evento) throws Exception {

		System.out.println("Persevere!!!");

		try {

			produto = (Produto) evento.getComponent().getAttributes().get("produtoselecionado");

			ProdutoDao ProdutoDao = new ProdutoDao();
			ProdutoDao.excluir(produto);

			// limpando os objetos
			produto = new Produto();
			produtos = ProdutoDao.listar();

			FabricanteDao FabricanteDao = new FabricanteDao();
			fabricantes = FabricanteDao.listar();

			Messages.addGlobalInfo("Produto excluido com sucesso");

		} catch (RuntimeException e) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir");
		}

	}

	public void editar(ActionEvent evento) {

		try {

			produto = new Produto();

			produto = (Produto) evento.getComponent().getAttributes().get("produtoselecionado");

			FabricanteDao FabricanteDao = new FabricanteDao();
			fabricantes = FabricanteDao.listar();

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um Produto");
			erro.printStackTrace();
		}

	}
	
	public void upLoad (FileUploadEvent evento) {
		
		System.out.println("TESTe upload");
		
		
		String nome = evento.getFile().getFileName();
		
		System.out.println("Nome: "+nome);
		
	}
	

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

}
