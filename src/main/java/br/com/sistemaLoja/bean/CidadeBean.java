package br.com.sistemaLoja.bean;

import javax.faces.event.ActionEvent;
import javax.swing.plaf.synth.SynthSeparatorUI;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.omnifaces.util.Messages;

import br.com.sistemaLoja.domain.Cidade;
import br.com.sistemaLoja.domain.Estado;
import br.com.sistemaloja.dao.CidadeDao;
import br.com.sistemaloja.dao.EstadoDao;

@SuppressWarnings("serial")
@ManagedBean
public class CidadeBean implements Serializable {

	private Cidade cidade;
	private List<Cidade> cidades;
	private List<Estado> estados;

	@PostConstruct
	public void listar() {

		cidade = new Cidade();

		try {

			CidadeDao cidadeDao = new CidadeDao();
			cidades = cidadeDao.listar();

			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listar();
		} catch (RuntimeException erro) {

			Messages.addFlashError("Ocorreu um erro ao tentar listar as cidades", null, null);
			erro.printStackTrace();
		}

	}

	public void novo() {

		try {

			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listar();
		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar uma nova cidade");
			erro.printStackTrace();
		}
	}

	public void salvar() throws Exception {

		teste();

		System.out.println("Metodo salvar");
		try {

			System.out.println("Cidade: " + cidade.getNome() + " Id da Cidade:" + cidade.getCodigo());
			System.out.println("Estado: " + cidade.getEstado().getNome());

			CidadeDao cidadeDao = new CidadeDao();
			cidadeDao.salvar(cidade);

			System.out.println("Cidade: " + cidade.getNome() + " Id da Cidade:" + cidade.getCodigo());
			System.out.println("Estado: " + cidade.getEstado().getNome());

			// limpando os objetos
			cidade = new Cidade();

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

			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listar();

			Messages.addGlobalInfo("Cidade excluida com sucesso");

		} catch (RuntimeException e) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir");
		}

	}

	public void editar(ActionEvent evento) {

		try {

			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionado");

			System.out.println("Metodo editar");
			System.out.println("Cidade: " + cidade.getNome() + " Id da Cidade:" + cidade.getCodigo());
			System.out.println("Estado: " + cidade.getEstado().getNome());

			
			 
			
			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listar();
		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma cidade");
			erro.printStackTrace();
		}

	}
 
	public void teste() {

		System.out.println("Metodo teste id da cidade");
		System.out.println("Cidade: " + cidade.getNome() + " Id da Cidade: " + cidade.getCodigo());
		System.out.println("Estado: " + cidade.getEstado().getNome());

	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

}
