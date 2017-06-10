package br.com.sistemaLoja.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.sistemaLoja.domain.Cliente;
import br.com.sistemaLoja.domain.Pessoa;
import br.com.sistemaloja.dao.ClienteDao;
import br.com.sistemaloja.dao.EstadoDao;
import br.com.sistemaloja.dao.PessoaDao;

@SuppressWarnings("serial")
@ManagedBean(name = "clienteBean")
@ViewScoped
public class ClienteBean implements Serializable {

	private Cliente cliente;
	private List<Pessoa> pessoas;

	private List<Cliente> clientes;

	@PostConstruct
	public void listar() {

		cliente = new Cliente();

		try {

			ClienteDao clienteDao = new ClienteDao();
			clientes = clienteDao.listar("dataCadastro");

		} catch (RuntimeException erro) {

			Messages.addFlashError("Ocorreu um erro ao tentar listar as clientes", null, null);
			erro.printStackTrace();
		}

	}

	public void novo() {

		try {

				cliente = new Cliente();
				PessoaDao pessoaDao = new PessoaDao();
				
				pessoas = pessoaDao.listar("nome");
						
			
		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar uma nova cliente");
			erro.printStackTrace();
		}
	}

	public void salvar() throws Exception {

		try {

			ClienteDao clienteDao = new ClienteDao();
			clienteDao.merge(cliente);

			// limpando os objetos
			cliente = new Cliente();

			clientes = clienteDao.listar();

			novo();

			Messages.addGlobalInfo("cliente salva com sucesso");

		} catch (RuntimeException e) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova clientes");
		}

	}

	public void excluir(ActionEvent evento) throws Exception {

		try {

			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

			ClienteDao clienteDao = new ClienteDao();
			clienteDao.excluir(cliente);

			// limpando os objetos
			novo();
			clientes = clienteDao.listar();
			Messages.addGlobalInfo("cliente excluido com sucesso");

		} catch (RuntimeException e) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir");
		}

	}

	public void editar(ActionEvent evento) {

		try {

			cliente = new Cliente();

			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
  
			PessoaDao pessoaDao = new PessoaDao();
			pessoas = pessoaDao.listar("nome");
			
		 

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma cliente");
			erro.printStackTrace();
		}

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}
