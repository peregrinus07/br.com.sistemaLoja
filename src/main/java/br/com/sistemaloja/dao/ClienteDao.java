package br.com.sistemaloja.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.sistemaLoja.domain.Cliente;
import br.com.sistemaLoja.domain.Funcionario;
import br.com.sistemaloja.util.HibernetUtil;

public class ClienteDao extends GenericDao<Cliente> {

	@SuppressWarnings("unchecked")
	public List<Cliente> listarOrdenado() {

		HibernetUtil conexao;
		try {

			conexao = new HibernetUtil();
			Session sessao = conexao.getFabrica().openSession();

			Criteria consulta = sessao.createCriteria(Cliente.class);
			consulta.createAlias("pessoa", "p");
			consulta.addOrder(Order.asc("p.nome"));
			List<Cliente> resultado = consulta.list();
			return resultado;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}

	}

}
