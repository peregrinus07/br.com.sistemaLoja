package br.com.sistemaloja.dao;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.sistemaLoja.domain.ItemVenda;
import br.com.sistemaLoja.domain.Produto;
import br.com.sistemaLoja.domain.Usuario;
import br.com.sistemaLoja.domain.Venda;
import br.com.sistemaloja.util.HibernetUtil;

public class VendaDao extends GenericDao<Venda> {

	public void salvar(Venda venda, List<ItemVenda> itensVenda) throws Exception {

		HibernetUtil conexao = new HibernetUtil();

		Session sessao = conexao.getFabrica().openSession();

		Transaction transacao = null;

		try {

			transacao = sessao.beginTransaction();
			sessao.save(venda);

			for (int posicao = 0; posicao < itensVenda.size(); posicao++) {

				ItemVenda itemVenda = itensVenda.get(posicao);

				itemVenda.setVenda(venda);

				sessao.save(itemVenda);

				Produto produto = itemVenda.getProduto();

				int quantidadeAtual = produto.getQuantidade() - itemVenda.getQuantidade();

				if (quantidadeAtual >= 0) {

					produto.setQuantidade(new Short((quantidadeAtual) + ""));

					sessao.update(produto);
				} else {
					throw new RuntimeException("Quantidade insuficiente em estoque");
				}

			}

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
