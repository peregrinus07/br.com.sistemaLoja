package br.com.sistemaloja.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sistemaLoja.domain.Cidade;
import br.com.sistemaLoja.domain.Estado;

public class CidadeDaoTest {

	@Test
	@Ignore
	public void salvar() throws Exception {

		EstadoDao estado = new EstadoDao();
		Estado buscaEstado = estado.buscar(1l);

		Cidade cidade = new Cidade();

		cidade.setNome("Fortaleza");
		cidade.setEstado(buscaEstado);

		CidadeDao cid = new CidadeDao();
		cid.salvar(cidade);

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
	public void buscarPorEstado() throws Exception {

		Long estadoCodigo =8l;
		
		CidadeDao cidadeDao = new CidadeDao();

		List<Cidade> resultado = cidadeDao.buscarPorEstado(estadoCodigo);

		for (Cidade cidade : resultado) {

			System.out.println("Codigo: " + cidade.getCodigo() + " " + "Cidade: " + cidade.getNome() + " " + "Estado: "
					+ cidade.getEstado().getNome());

		}
	}

}
