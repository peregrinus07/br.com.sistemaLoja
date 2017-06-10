package br.com.sistemaloja.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mysql.cj.jdbc.interceptors.SessionAssociationInterceptor;

import br.com.sistemaLoja.domain.Cidade;
import br.com.sistemaloja.util.HibernetUtil;


public class CidadeDao extends GenericDao<Cidade> {

	@SuppressWarnings("unchecked")
	public List<Cidade> buscarPorEstado(Long estadoCodigo) throws Exception {
		
		HibernetUtil conexao = new HibernetUtil();
		Session sessao = conexao.getFabrica().openSession();
		 
		try {
 
			Criteria consulta = sessao.createCriteria(Cidade.class);
			consulta.add(Restrictions.eq("estado.codigo", estadoCodigo));
			
			
			consulta.addOrder(Order.asc("nome"));
			List<Cidade> ressultado = consulta.list();
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
