package br.com.sistemaLoja.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

import br.com.sistemaLoja.domain.Cidade;
import br.com.sistemaLoja.domain.Estado;
import br.com.sistemaLoja.domain.ItemVenda;
import br.com.sistemaLoja.domain.Produto;
import br.com.sistemaloja.dao.CidadeDao;
import br.com.sistemaloja.dao.EstadoDao;
import br.com.sistemaloja.dao.FabricanteDao;
import br.com.sistemaloja.dao.ProdutoDao;

@SuppressWarnings("serial")
@ManagedBean(name = "vendaBean")
@ViewScoped
public class VendaBean implements Serializable {

	private List<Produto> produtos;
	private List<ItemVenda> itensVenda;

	@PostConstruct
	public void listar() {

		try {

			ProdutoDao ProdutoDao = new ProdutoDao();
			produtos = ProdutoDao.listar("descricao");
			itensVenda = new ArrayList<>();

		} catch (RuntimeException erro) {

			Messages.addFlashError("Ocorreu um erro ao tentar listar os produtos", null, null);
			erro.printStackTrace();
		}

	}

	public void adcionarProduto(ActionEvent evento) {

		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoselecionado");

		int achou = -1;

		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {

			if (itensVenda.get(posicao).getProduto().equals(produto)) {

				achou = posicao;
			}
		}

		if(achou < 0) {
			
			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setValorParcial(produto.getPreco());
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(new Short("1"));
			itensVenda.add(itemVenda);
			
		} else {
			
			ItemVenda itemVenda = itensVenda.get(achou);
			itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1 + ""));
			itemVenda.setValorParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
			
		}
		
		

	}

	public void novo() {

		try {

			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listar("nome");
		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar uma nova cidade");
			erro.printStackTrace();
		}
	}

	public void salvar() throws Exception {

		try {

			CidadeDao cidadeDao = new CidadeDao();
			cidadeDao.merge(cidade);

			// limpando os objetos
			cidade = new Cidade();

			cidades = cidadeDao.listar();

			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listar();

			Messages.addGlobalInfo("Cidade salva com sucesso");

		} catch (RuntimeException e) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova cidades");
		}

	}

	public void excluir(ActionEvent evento) throws Exception {

		try {

			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");

			CidadeDao cidadeDao = new CidadeDao();
			cidadeDao.excluir(cidade);

			// limpando os objetos
			cidade = new Cidade();
			cidades = cidadeDao.listar();

			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listar();

			Messages.addGlobalInfo("Cidade excluida com sucesso");

		} catch (RuntimeException e) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir");
		}

	}

	public void editar(ActionEvent evento) {

		try {

			cidade = new Cidade();

			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");

			cidadeEditar = cidade;

			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listar();

			cidade.setCodigo(cidadeEditar.getCodigo());

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma cidade");
			erro.printStackTrace();
		}

	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

}
