package br.com.sistemaLoja.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.sistemaLoja.domain.Cidade;
import br.com.sistemaLoja.domain.Estado;
import br.com.sistemaLoja.domain.Pessoa;
import br.com.sistemaLoja.domain.Usuario;
import br.com.sistemaloja.dao.CidadeDao;
import br.com.sistemaloja.dao.EstadoDao;
import br.com.sistemaloja.dao.UsuarioDao;

@SuppressWarnings("serial")
@ManagedBean(name = "autenticacaoBean")
@SessionScoped
public class AutenticacaoBean implements Serializable {

	private Usuario usuario;
	private Pessoa pessoa;
	private Usuario usuarioLogado;
	private Boolean permissaoMenuCadastro;

	@PostConstruct
	public void iniciar() throws IOException {

		usuario = new Usuario();
		pessoa = new Pessoa();

		usuario.setPessoa(pessoa);
		
		permissaoMenuCadastro = false; 

	}

	public void autenticar() throws IOException {

		try {

			UsuarioDao usuarioDao = new UsuarioDao();

			usuarioLogado = usuarioDao.autenticar(pessoa.getCpf(), usuario.getSenha());

			if (usuarioLogado == null) {

				Messages.addGlobalError("Cpf e/ou Senha invalida");

				return;
			}

			Faces.redirect("./pages/principal.xhtml");
			temPermissaoMenuCadastro();

		} catch (Exception e) {

			Messages.addGlobalError("Erro ao autenticar");
			e.printStackTrace();
		}

	}

	public boolean temPermissoes(List<String> permissoes) {

		for (String permissao : permissoes) {

			System.out.println("Permissao: " + permissao.charAt(0));

			if (usuarioLogado.getTipo() == permissao.charAt(0)) {

				System.out.println("tipo: " + usuarioLogado.getTipoFormatado());

				return true;

			}
		}

		return false;
	}

	public boolean temPermissaoMenuCadastro() {

		System.out.println("Tipo de Permissao do usuario: " + usuarioLogado.getTipo());

		if (usuarioLogado.getTipo() == 'B') {

			System.out.println("usuario: " + usuarioLogado.getTipo());

			permissaoMenuCadastro = false;
			
			return false;
		} else {

			 
			permissaoMenuCadastro = true;
			return true;

		}

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Boolean getPermissaoMenuCadastro() {
		return permissaoMenuCadastro;
	}

	public void setPermissaoMenuCadastro(Boolean permissaoMenuCadastro) {
		this.permissaoMenuCadastro = permissaoMenuCadastro;
	}

}
