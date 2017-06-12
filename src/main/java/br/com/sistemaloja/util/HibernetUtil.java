package br.com.sistemaloja.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.jdbc.ReturningWork;

public class HibernetUtil {

	private SessionFactory fabrica;

	public HibernetUtil() throws Exception {

		criarConexao();

	}

	public SessionFactory getFabrica() {
		return fabrica;
	}

	public void setFabrica(SessionFactory fabrica) {
		this.fabrica = fabrica;
	}

	public static Connection getConexao() {

		try {

			HibernetUtil conexaoHibernate = new HibernetUtil();

			Session sessao = conexaoHibernate.fabrica.openSession();

			Connection conexao = sessao.doReturningWork(new ReturningWork<Connection>() {

				@Override
				public Connection execute(Connection conn) throws SQLException {
					// TODO Auto-generated method stub
					return conn;
				}

			});

			return conexao;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

	}

	public void criarConexao() throws Exception {
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure() // configures
				// from
				// hibernate.cfg.xml
				.build();
		try {

			fabrica = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			setFabrica(fabrica);

		} catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had
			// trouble building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

}