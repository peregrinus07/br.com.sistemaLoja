package br.com.sistemaLoja.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.sistemaLoja.domain.Cliente;
import br.com.sistemaloja.dao.ClienteDao;
import br.com.sistemaloja.dao.EstadoDao;

@SuppressWarnings("serial")
@ManagedBean(name = "clienteBean")
@ViewScoped
public class ClienteBean implements Serializable {

	private Cliente cliente;

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

			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listar("nome");
		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar uma nova cliente");
			erro.printStackTrace();
		}
	}

	public void salvar() throws Exception {

		try {

			clienteDao clienteDao = new clienteDao();
			clienteDao.merge(cliente);

			// limpando os objetos
			cliente = new cliente();

			clientes = clienteDao.listar();

			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listar();

			Messages.addGlobalInfo("cliente salva com sucesso");

		} catch (RuntimeException e) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova clientes");
		}

	}

	public void excluir(ActionEvent evento) throws Exception {

		try {

			cliente = (cliente) evento.getComponent().getAttributes().get("clienteSelecionada");

			clienteDao clienteDao = new clienteDao();
			clienteDao.excluir(cliente);

			// limpando os objetos
			cliente = new cliente();
			clientes = clienteDao.listar();

			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listar();

			Messages.addGlobalInfo("cliente excluida com sucesso");

		} catch (RuntimeException e) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir");
		}

	}

	public void editar(ActionEvent evento) {

		try {

			cliente = new cliente();

			cliente = (cliente) evento.getComponent().getAttributes().get("clienteSelecionada");

			clienteEditar = cliente;

			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listar();

			cliente.setCodigo(clienteEditar.getCodigo());

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

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}
