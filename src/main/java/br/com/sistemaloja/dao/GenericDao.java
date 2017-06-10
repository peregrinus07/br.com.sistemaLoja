package br.com.sistemaloja.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.sistemaloja.util.HibernetUtil;

public class GenericDao<Entidade> {

	private Class<Entidade> classe;

	@SuppressWarnings("unchecked")
	public GenericDao() {

		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

	}

	public void salvar(Entidade entidade) throws Exception {

		HibernetUtil conexao = new HibernetUtil();

		Session sessao = conexao.getFabrica().openSession();

		Transaction transacao = null;

		try {

			transacao = sessao.beginTransaction();
			sessao.merge(entidade);

			transacao.commit();

		} catch (RuntimeException erro) {

			if (transacao != null) {

				transacao.rollback();
			}

			throw erro;

		} finally {

			sessao.close();
			conexao.getFabrica().close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Entidade> listar() {

		HibernetUtil conexao;
		try {

			conexao = new HibernetUtil();
			Session sessao = conexao.getFabrica().openSession();

			Criteria consulta = sessao.createCriteria(classe);
			List<Entidade> resultado = consulta.list();
			return resultado;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Entidade> listar(String campoOrdenacao) {

		HibernetUtil conexao;
		try {

			conexao = new HibernetUtil();
			Session sessao = conexao.getFabrica().openSession();

			Criteria consulta = sessao.createCriteria(classe);
			consulta.addOrder(Order.asc(campoOrdenacao));
			List<Entidade> resultado = consulta.list();
			return resultado;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public Entidade buscar(Long codigo) {

		HibernetUtil conexao;
		try {

			conexao = new HibernetUtil();
			Session sessao = conexao.getFabrica().openSession();

			Criteria consulta = sessao.createCriteria(classe);
			consulta.add(Restrictions.idEq(codigo));
			Entidade ressultado = (Entidade) consulta.uniqueResult();
			return ressultado;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}

	}

	public void excluir(Entidade entidade) throws Exception {

		HibernetUtil conexao = new HibernetUtil();

		Session sessao = conexao.getFabrica().openSession();

		Transaction transacao = null;

		try {

			transacao = sessao.beginTransaction();
			sessao.delete(entidade);

			transacao.commit();

		} catch (RuntimeException erro) {

			if (transacao != null) {

				transacao.rollback();
			}

			throw erro;

		} finally {

			sessao.close();
			conexao.getFabrica().close();
		}

	}

	public void editar(Entidade entidade) throws Exception {

		HibernetUtil conexao = new HibernetUtil();

		Session sessao = conexao.getFabrica().openSession();

		Transaction transacao = null;

		try {

			transacao = sessao.beginTransaction();
			sessao.update(entidade);

			transacao.commit();

		} catch (RuntimeException erro) {

			if (transacao != null) {

				transacao.rollback();
			}

			throw erro;

		} finally {

			sessao.close();
			conexao.getFabrica().close();
		}

	}

	public void merge(Entidade entidade) throws Exception {

		HibernetUtil conexao = new HibernetUtil();

		Session sessao = conexao.getFabrica().openSession();

		Transaction transacao = null;

		try {

			transacao = sessao.beginTransaction();
			sessao.merge(entidade);

			transacao.commit();

		} catch (RuntimeException erro) {

			if (transacao != null) {

				transacao.rollback();
			}

			throw erro;

		} finally {

			sessao.close();
			conexao.getFabrica().close();
		}

	}

}
