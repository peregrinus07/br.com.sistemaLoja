package br.com.sistemaloja.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateContexto implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {

		try {
			HibernetUtil conexao = new HibernetUtil();

			conexao.getFabrica().close();
			;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {

		try {
			HibernetUtil conexao = new HibernetUtil();

			conexao.getFabrica();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
