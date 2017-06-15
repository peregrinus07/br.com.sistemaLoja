package br.com.sistemaLoja.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;

import br.com.sistemaLoja.domain.Pessoa;
import br.com.sistemaLoja.domain.Usuario;
import br.com.sistemaloja.dao.PessoaDao;
import br.com.sistemaloja.dao.UsuarioDao;

@SuppressWarnings("serial")
@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable {

	private Usuario usuario;
	private List<Pessoa> pessoas;
	private List<Usuario> usuarios;

	@PostConstruct
	public void listar() {

		usuario = new Usuario();

		try {

			UsuarioDao usuarioDao = new UsuarioDao();
			usuarios = usuarioDao.listar();

		} catch (RuntimeException erro) {

			Messages.addFlashError("Ocorreu um erro ao tentar listar as usuarios", null, null);
			erro.printStackTrace();
		}

	}

	public void novo() {

		try {

			usuario = new Usuario();
			PessoaDao pessoaDao = new PessoaDao();

			pessoas = pessoaDao.listar("nome");

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar uma nova usuario");
			erro.printStackTrace();
		}
	}

	public void salvar() throws Exception {

		System.out.println("TESTE");

		try {

			UsuarioDao usuarioDao = new UsuarioDao();

			SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());

			usuario.setSenha(hash.toHex());
			
			usuarioDao.merge(usuario);

			// limpando os objetos
			usuario = new Usuario();

			usuarios = usuarioDao.listar();

			novo();

			Messages.addGlobalInfo("usuario salva com sucesso");

		} catch (RuntimeException e) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova usuarios");
		}

	}

	public void excluir(ActionEvent evento) throws Exception {

		try {

			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			UsuarioDao usuarioDao = new UsuarioDao();
			usuarioDao.excluir(usuario);

			// limpando os objetos
			novo();
			usuarios = usuarioDao.listar();
			Messages.addGlobalInfo("usuario excluido com sucesso");

		} catch (RuntimeException e) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir");
		}

	}

	public void editar(ActionEvent evento) throws Exception {

		try {

			usuario = new Usuario();

			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			System.out.println("Usuario senha: " + usuario.getSenha());

			PessoaDao pessoaDao = new PessoaDao();
			pessoas = pessoaDao.listar("nome");

		} catch (RuntimeException erro) {

			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um usuario");
			erro.printStackTrace();
		}

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
