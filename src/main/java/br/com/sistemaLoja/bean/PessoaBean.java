package br.com.sistemaLoja.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.sistemaLoja.domain.Cidade;
import br.com.sistemaLoja.domain.Estado;
import br.com.sistemaLoja.domain.Pessoa;
import br.com.sistemaloja.dao.CidadeDao;
import br.com.sistemaloja.dao.EstadoDao;
import br.com.sistemaloja.dao.PessoaDao;

@SuppressWarnings("serial")
@ManagedBean(name = "pessoaBean")
@ViewScoped
public class PessoaBean implements Serializable {

	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	private Estado estado;
	private List<Estado> estados;
	private List<Cidade> cidades;

	@PostConstruct
	public void listar() {

		try {

			pessoa = new Pessoa();

			PessoaDao pessoaDao = new PessoaDao();
			pessoas = pessoaDao.listar();

		} catch (RuntimeException erro) {

			Messages.addFlashError("Ocorreu um erro ao tentar listar Pessoas", null, null);
			erro.printStackTrace();
		}

	}

	public void novo() {

		try {

			pessoa = new Pessoa();

			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listar();

			cidades = new ArrayList<Cidade>();

		} catch (RuntimeException erro) {
			System.out.println("#############");
			System.out.println("#############");

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar uma nova Pessoa");
			erro.printStackTrace();
		}
	}

	public void salvar() throws Exception {

		 

		try {

			PessoaDao pessoaDao = new PessoaDao();
			pessoaDao.merge(pessoa);

			// limpando os objetos
			pessoa = new Pessoa();
			 
			pessoas = pessoaDao.listar();

			novo();

			Messages.addGlobalInfo("Pessoa salva com sucesso");

		} catch (RuntimeException e) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova Pessoas");
		}

	}

	public void excluir(ActionEvent evento) throws Exception {

		try {

			pessoa = (Pessoa) evento.getComponent().getAttributes().get("PessoaSelecionada");

			PessoaDao PessoaDao = new PessoaDao();
			PessoaDao.excluir(pessoa);

			// limpando os objetos
			pessoa = new Pessoa();
			pessoas = PessoaDao.listar();

			Messages.addGlobalInfo("Pessoa excluida com sucesso");

		} catch (RuntimeException e) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir");
		}

	}

	public void editar(ActionEvent evento) {

		try {

			pessoa = new Pessoa();

			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma Pessoa");
			erro.printStackTrace();
		}

	}

	public void popularCidades() throws Exception {

		try { 
			
			if(estado != null) {

				CidadeDao cidadeDao = new CidadeDao();
				cidades = cidadeDao.buscarPorEstado(estado.getCodigo());
				System.out.println(cidades.size());
			} else {
				
				cidades = new ArrayList<>();
				
			}

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar filtrar as Cidades");
			erro.printStackTrace();
		}

	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
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

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

}
