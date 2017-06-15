package br.com.sistemaloja.dao;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import br.com.sistemaLoja.domain.Cidade;
import br.com.sistemaLoja.domain.Pessoa;
import br.com.sistemaLoja.domain.Usuario;

public class UsuarioDaoTest {

	@Test
	@Ignore
	public void salvar() throws Exception {

		 UsuarioDao usuarioDao = new UsuarioDao();
		 PessoaDao pessoaDao = new PessoaDao();
		 Pessoa pessoa = pessoaDao.buscar(28l);
		  
		 Usuario usuario = new Usuario();
		 
		 usuario.setPessoa(pessoa);
		 usuario.setSenhaSemCriptografia("persevere");
		 
		 SimpleHash hash = new SimpleHash("md5",usuario.getSenhaSemCriptografia());
		  
		 usuario.setSenha(hash.toHex());
		 usuario.setAtivo(true);
		 usuario.setSenhaSemCriptografia("teste");
		 usuario.setTipo('A');
		 
		 usuarioDao.salvar(usuario);

	}

	@Test
	@Ignore
	public void listar() {

		CidadeDao cidadeDao = new CidadeDao();

		List<Cidade> resultado = cidadeDao.listar();

		for (Cidade cidade : resultado) {

			System.out.println("Codigo: " + cidade.getCodigo() + " " + "Cidade: " + cidade.getNome() + " " + "Estado: "
					+ cidade.getEstado().getNome());

		}
	}

	@Test
	@Ignore
	public void buscar() {

		CidadeDao cidadeDao = new CidadeDao();

		Cidade cidade = cidadeDao.buscar(2l);

		System.out.println("Cidade: " + cidade.getNome());

	}

	@Test
	@Ignore
	public void excluir() throws Exception {

		CidadeDao cidadeDao = new CidadeDao();
		Cidade cidade = new Cidade();

		cidadeDao.excluir(cidade);

	}
	
	@Test
	@Ignore
	public void buscarPorEstado() throws Exception {

		Long estadoCodigo =8l;
		
		CidadeDao cidadeDao = new CidadeDao();

		List<Cidade> resultado = cidadeDao.buscarPorEstado(estadoCodigo);

		for (Cidade cidade : resultado) {

			System.out.println("Codigo: " + cidade.getCodigo() + " " + "Cidade: " + cidade.getNome() + " " + "Estado: "
					+ cidade.getEstado().getNome());

		}
	}
	
	@Test
	public void autenticar() throws Exception{
		String cpf = "333.333-33";
		String senha = "tibe17";
		
		UsuarioDao usuarioDAO = new UsuarioDao();
		Usuario usuario = usuarioDAO.autenticar(cpf, senha);
		
		 
	 
		 
	}

}
