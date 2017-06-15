package br.com.sistemaloja.dao;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.sistemaLoja.domain.Usuario;
import br.com.sistemaloja.util.HibernetUtil;

public class UsuarioDao extends GenericDao<Usuario> {

	public Usuario autenticar(String cpf, String senha) throws Exception {

		HibernetUtil hibernetUtil = new HibernetUtil();

		Session sessao = hibernetUtil.getFabrica().openSession();

		Usuario resultado = new Usuario();
		
		try {
			
				Criteria consulta = sessao.createCriteria(Usuario.class);
     		    consulta.createAlias("pessoa", "p");
				
				SimpleHash hash = new SimpleHash("md5",senha);
				  
				consulta.add(Restrictions.eq("p.cpf", cpf));
				consulta.add(Restrictions.eq("senha", hash.toHex()));	
				 
				resultado = (Usuario) consulta.uniqueResult();
		 
				 
				
				
				return resultado;

		} catch (RuntimeException e) {

			e.printStackTrace();

		} finally {

		}
		
			 return resultado;

	}

}
