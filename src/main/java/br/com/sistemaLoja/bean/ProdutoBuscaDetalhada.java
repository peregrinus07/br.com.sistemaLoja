package br.com.sistemaLoja.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.sistemaLoja.domain.Produto;
import br.com.sistemaloja.dao.ProdutoDao;

@ManagedBean(name ="produtoBuscaDetalhada")
@ViewScoped
public class ProdutoBuscaDetalhada {
	
	private Produto produto;
	private Boolean exibirPainelDeDados;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	public Boolean getExibirPainelDeDados() {
		return exibirPainelDeDados;
	}

	public void setExibirPainelDeDados(Boolean exibirPainelDeDados) {
		this.exibirPainelDeDados = exibirPainelDeDados;
	}

	@PostConstruct
	public void novo () {
		
		produto = new Produto();
		exibirPainelDeDados = false;
		
		
	}
	
	public void buscar() {
		
		try {
			
				ProdutoDao produtoDao = new ProdutoDao();
			    Produto resultado = produtoDao.buscar(produto.getCodigo());
			
			    if (resultado == null) {
					
			    	Messages.addGlobalWarn("Não exite produto cadastrado, para o codigo informado");
				} else {
					
					produto = resultado;
					exibirPainelDeDados = true;
					
				}
			
		} catch (RuntimeException e) {
 
			e.printStackTrace();
			Messages.addGlobalError("Erro ao Pesquisar o Produto");
			
		}
	}

}
