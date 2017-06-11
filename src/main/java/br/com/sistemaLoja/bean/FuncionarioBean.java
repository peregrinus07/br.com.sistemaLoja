package br.com.sistemaLoja.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.sistemaLoja.domain.Funcionario;
import br.com.sistemaLoja.domain.Pessoa;
import br.com.sistemaloja.dao.FuncionarioDao;
import br.com.sistemaloja.dao.PessoaDao;

@SuppressWarnings("serial")
@ManagedBean(name = "funcionarioBean")
@ViewScoped
public class FuncionarioBean implements Serializable {

	private Funcionario funcionario;
	private List<Pessoa> pessoas;

	private List<Funcionario> funcionarios;

	@PostConstruct
	public void listar() {

		funcionario = new Funcionario();

		try {

			FuncionarioDao funcionarioDao = new FuncionarioDao();
			funcionarios = funcionarioDao.listar("dataCadastro");

		} catch (RuntimeException erro) {

			Messages.addFlashError("Ocorreu um erro ao tentar listar as funcionarios", null, null);
			erro.printStackTrace();
		}

	}

	public void novo() {

		try {

			funcionario = new Funcionario();
			PessoaDao pessoaDao = new PessoaDao();

			pessoas = pessoaDao.listar("nome");

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar uma nova funcionario");
			erro.printStackTrace();
		}
	}

	public void salvar() throws Exception {

		try {

			FuncionarioDao funcionarioDao = new FuncionarioDao();
			funcionarioDao.merge(funcionario);

			// limpando os objetos
			funcionario = new Funcionario();

			funcionarios = funcionarioDao.listar();

			novo();

			Messages.addGlobalInfo("funcionario salva com sucesso");

		} catch (RuntimeException e) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova funcionarios");
		}

	}

	public void excluir(ActionEvent evento) throws Exception {

		try {

			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

			FuncionarioDao funcionarioDao = new FuncionarioDao();
			funcionarioDao.excluir(funcionario);

			// limpando os objetos
			novo();
			funcionarios = funcionarioDao.listar();
			Messages.addGlobalInfo("funcionario excluido com sucesso");

		} catch (RuntimeException e) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir");
		}

	}

	public void editar(ActionEvent evento) {

		try {

			funcionario = new Funcionario();

			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

			PessoaDao pessoaDao = new PessoaDao();
			pessoas = pessoaDao.listar("nome");

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma funcionario");
			erro.printStackTrace();
		}

	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
