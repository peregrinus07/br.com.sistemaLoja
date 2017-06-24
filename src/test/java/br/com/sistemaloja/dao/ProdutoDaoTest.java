package br.com.sistemaloja.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sistemaLoja.domain.Produto;

public class ProdutoDaoTest {

	@Test
	@Ignore
	public void produtoPorquantidadeMinima() throws Exception {

		ProdutoDao produtoDao = new ProdutoDao();

		List<Produto> listaDeProdutos;

		listaDeProdutos = produtoDao.listarPorQuantidadeMinima();

		System.out.println("Testando lista de Produtos por quantidade minima: " + listaDeProdutos.size());

		for (Produto prod : listaDeProdutos) {

			System.out.println("Nome: " + prod.getNome());
			System.out.println("Quantidade: " + prod.getQuantidade());

		}

	}
	


}
