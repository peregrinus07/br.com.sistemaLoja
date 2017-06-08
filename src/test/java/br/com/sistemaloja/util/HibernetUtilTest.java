package br.com.sistemaloja.util;

import org.hibernate.Session;

import org.junit.Test;

public class HibernetUtilTest {

	@Test
	public void conectar() throws Exception {

		HibernetUtil fabricaDeConexao = new HibernetUtil();

		Session sessao = (Session) fabricaDeConexao.getFabrica().openSession();

		sessao.close();
		fabricaDeConexao.getFabrica().close();
	}

}
