package br.com.sistemaloja.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sistemaLoja.domain.Estado;

public class EstadoDaoTest {

	@Test
	@Ignore
	public void salvar() throws Exception {

		Estado estado = new Estado();
		EstadoDao estadoDao = new EstadoDao();

		estado.setNome("Ceara");
		estado.setSigla("Ce");

		estadoDao.salvar(estado);

	}

	@Test
	@Ignore
	public void listar() {

		EstadoDao estado = new EstadoDao();

		List<Estado> resultado = estado.listar();

		for (Estado uf : resultado) {

			System.out.println(uf.getNome() + "\n");
		}
	}

	@Test
	@Ignore
	public void buscar() {

		Long codigo = 200L;
		EstadoDao estado = new EstadoDao();

		Estado uf = (Estado) estado.buscar(codigo);

		System.out.println("Estado: " + uf.getNome() + "\n");
	}

	@Test
	@Ignore
	public void excluir() throws Exception {

		Long codigo = 1L;
		EstadoDao estado = new EstadoDao();

		Estado uf = (Estado) estado.buscar(codigo);

		estado.excluir(uf);

	}

	@Test
	@Ignore
	public void editar() {

		Long codigo = 2L;
		EstadoDao estado = new EstadoDao();

		Estado uf = (Estado) estado.buscar(codigo);

		uf.setNome("teste");
		try {
			estado.editar(uf);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
