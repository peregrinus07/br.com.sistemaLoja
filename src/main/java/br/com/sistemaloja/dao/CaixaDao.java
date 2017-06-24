package br.com.sistemaloja.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.sistemaLoja.domain.Caixa;
import br.com.sistemaLoja.domain.Cidade;
import br.com.sistemaloja.util.HibernetUtil;

public class CaixaDao extends GenericDao<Caixa> {

	@SuppressWarnings("unchecked")
	public Caixa buscarPorDataDeAbertura (Date dataAbertura) throws Exception {

		HibernetUtil conexao = new HibernetUtil();
		Session sessao = conexao.getFabrica().openSession();

		try {

			Criteria consulta = sessao.createCriteria(Caixa.class);
			consulta.add(Restrictions.eq("dataDeAbertura", dataAbertura));

			//consulta.addOrder(Order.asc("nome"));
			Caixa ressultado = (Caixa) consulta.uniqueResult();
			return ressultado;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		} finally {

			sessao.close();

		}

	}

}
