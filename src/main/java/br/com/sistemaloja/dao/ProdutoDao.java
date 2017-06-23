package br.com.sistemaloja.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.sistemaLoja.domain.Produto;
import br.com.sistemaloja.util.HibernetUtil;

public class ProdutoDao extends GenericDao<Produto> {

	public List<Produto> listarPorQuantidadeMinima() throws Exception {

		HibernetUtil hibernetUtil = new HibernetUtil();

		Session sessao = hibernetUtil.getFabrica().openSession();

		List<Produto> resultado = null;

		try {

			Criteria consulta = sessao.createCriteria(Produto.class);

			consulta.add(Restrictions.gt("quantidade", new Short("0")));

			resultado = (List<Produto>) consulta.list();

			return resultado;

		} catch (RuntimeException e) {

			e.printStackTrace();

		} finally {

			sessao.close();
		}

		return resultado;

	}
	

}
