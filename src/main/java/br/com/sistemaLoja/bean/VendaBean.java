package br.com.sistemaLoja.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

import br.com.sistemaLoja.domain.Cidade;
import br.com.sistemaLoja.domain.Cliente;
import br.com.sistemaLoja.domain.Estado;
import br.com.sistemaLoja.domain.Funcionario;
import br.com.sistemaLoja.domain.ItemVenda;
import br.com.sistemaLoja.domain.Produto;
import br.com.sistemaLoja.domain.Venda;
import br.com.sistemaloja.dao.CidadeDao;
import br.com.sistemaloja.dao.ClienteDao;
import br.com.sistemaloja.dao.EstadoDao;
import br.com.sistemaloja.dao.FabricanteDao;
import br.com.sistemaloja.dao.FuncionarioDao;
import br.com.sistemaloja.dao.ProdutoDao;
import br.com.sistemaloja.dao.VendaDao;

@SuppressWarnings("serial")
@ManagedBean(name = "vendaBean")
@ViewScoped
public class VendaBean implements Serializable {

	private Venda venda;

	private List<Produto> produtos;
	private List<ItemVenda> itensVenda;
	private List<Cliente> clientes;
	private List<Funcionario> funcionarios;

	@PostConstruct
	public void listar() {

		try {

			venda = new Venda();
			venda.setPrecoTotal(new BigDecimal("0.00"));

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

		if (achou < 0) {

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

		calcularPrecoTotal();

	}

	public void removerProduto(ActionEvent evento) {

		ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("itemSelecionado");

		int achou = -1;

		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {

			if (itensVenda.get(posicao).getProduto().equals(itemVenda.getProduto())) {

				achou = posicao;
			}
		}

		if (achou > -1) {

			itensVenda.remove(achou);
		}

		calcularPrecoTotal();
	}

	public void calcularPrecoTotal() {

		venda.setPrecoTotal(new BigDecimal("0.00"));

		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {

			ItemVenda itemVenda = itensVenda.get(posicao);

			venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getValorParcial()));

		}

	}

	public void finalizarVenda() {

		try {

			venda.setHorario(new Date());

			FuncionarioDao funcionarioDao = new FuncionarioDao();
			funcionarios = funcionarioDao.listarOrdenado();

			ClienteDao clienteDao = new ClienteDao();
			clientes = clienteDao.listarOrdenado();

		} catch (RuntimeException e) {

			Messages.addFlashGlobalError("Ocorreu um erro ao finalizar venda!");
			e.printStackTrace();
		}

	}

	public void salvar() throws Exception {

		try {

			if (venda.getPrecoTotal().signum() == 0) {

				Messages.addGlobalInfo("informe um produto para venda");

				return;
			}

			 
			VendaDao vendaDao = new VendaDao();
			vendaDao.salvar(venda, itensVenda);
			
			Messages.addGlobalInfo("Venda realizada com Sucesso");
			

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

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
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

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
