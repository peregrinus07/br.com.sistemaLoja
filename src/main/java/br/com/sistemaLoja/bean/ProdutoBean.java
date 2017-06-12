package br.com.sistemaLoja.bean;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

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

			if (produto.getCaminho() == null) {

				Messages.addGlobalInfo("O campo foto é obrigatório");

				return;
			}

			ProdutoDao ProdutoDao = new ProdutoDao();
			Produto produtoRetorno = ProdutoDao.merge(produto);

			Path origem = Paths.get(produto.getCaminho());
			Path destino = Paths.get("/home/tibe/upload/" + produtoRetorno.getCodigo() + ".png");

			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

			// limpando os objetos
			produto = new Produto();

			produtos = ProdutoDao.listar();

			FabricanteDao FabricanteDao = new FabricanteDao();
			fabricantes = FabricanteDao.listar();

			Messages.addGlobalInfo("Produto salvo com sucesso");

		} catch (RuntimeException | IOException e) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar um novo produtos");
			e.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) throws Exception {

		System.out.println("Persevere!!!");

		try {

			produto = (Produto) evento.getComponent().getAttributes().get("produtoselecionado");

			ProdutoDao ProdutoDao = new ProdutoDao();
			ProdutoDao.excluir(produto);

			Path delete = Paths.get("/home/tibe/upload/" + produto.getCodigo() + ".png");

			Files.deleteIfExists(delete);

			// limpando os objetos
			produto = new Produto();
			produtos = ProdutoDao.listar();

			FabricanteDao FabricanteDao = new FabricanteDao();
			fabricantes = FabricanteDao.listar();

			Messages.addGlobalInfo("Produto excluido com sucesso");

		} catch (RuntimeException | IOException e) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir");
			e.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {

		try {

			produto = new Produto();

			produto = (Produto) evento.getComponent().getAttributes().get("produtoselecionado");
			produto.setCaminho("/home/tibe/upload/" + produto.getCodigo() + ".png");

			FabricanteDao FabricanteDao = new FabricanteDao();
			fabricantes = FabricanteDao.listar();

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um Produto");
			erro.printStackTrace();
		}

	}

	public void upLoad(FileUploadEvent evento) {

		try {

			UploadedFile arquivoUload = evento.getFile();

			Path caminho = Paths.get("/home/tibe/teste");

			Path arquivoTemp = Files.createTempFile(caminho, null, null);

			Files.copy(arquivoUload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);

			produto.setCaminho(arquivoTemp.toString());

			Messages.addGlobalInfo("Upload realizado com sucesso!");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			Messages.addGlobalInfo("Ocorreu um erro ao tentar realizar o upload de um arquivo");
			e.printStackTrace();
		}

	}
	
	public void gerarRelatorio() {
		
		
		
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
