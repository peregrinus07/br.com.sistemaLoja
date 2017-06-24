package br.com.sistemaloja.dao;

import java.util.Date;

import org.bouncycastle.asn1.isismtt.x509.Restriction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.sistemaLoja.domain.Caixa;
import br.com.sistemaLoja.domain.Movimentacao;
import br.com.sistemaloja.util.HibernetUtil;

public class MovimentacaoDao extends GenericDao<Movimentacao> {

	public Caixa buscar(Date dataDeAbertura) throws Exception {

		HibernetUtil hibernateUtil = new HibernetUtil();

		Session sessao = hibernateUtil.getFabrica().openSession();
		
		Caixa resultado;

		try {

				Criteria consulta = sessao.createCriteria(Caixa.class);
				
				consulta.add(Restrictions.eq("dataDeAbertura", dataDeAbertura));
			
			    resultado = (Caixa) consulta.uniqueResult();
				
				return resultado;
				
		} catch (RuntimeException e) {

			throw e;
		}

		finally {

			sessao.close();

		}

		 
	}
}
