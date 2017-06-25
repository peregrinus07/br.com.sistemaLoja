package br.com.sistemaLoja.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.omnifaces.util.Messages;

import br.com.sistemaLoja.domain.Caixa;
import br.com.sistemaLoja.domain.Cidade;
import br.com.sistemaloja.dao.CaixaDao;

@ManagedBean(name = "fluxoDeCaixaBean")
@ViewScoped
public class FluxoDeCaixaBean {

	private Caixa caixa;
	private List<Caixa> listaDeCaixas;

	@PostConstruct
	public void listar() {

		CaixaDao caixaDao = new CaixaDao();
		listaDeCaixas = caixaDao.listar();
		
		
	}
	
	public void inicarCaixa(){}
	
	public void encerrarCaixa(ActionEvent evento) throws ParseException {
		
		Caixa caixa = new Caixa();
		
		caixa = (Caixa) evento.getComponent().getAttributes().get("caixaSelecionado");
		
		CaixaDao caixaDao = new CaixaDao();
		
		DateFormat dataformatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date data = new Date();				
		String diaAtual = dataformatada.format(data); 
		
//		System.out.println("Hora Atual: "+diaAtual );
		
		caixa.setDataDeFeichamento(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(diaAtual));
		
		try {
			
			caixaDao.salvar(caixa);
			Messages.addGlobalInfo("Caixa Encerrado Com Sucesso");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Messages.addGlobalError("Erro ao Encerrar Caixa");
		}
		
	}
	
	

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public List<Caixa> getListaDeCaixas() {
		return listaDeCaixas;
	}

	public void setListaDeCaixas(List<Caixa> listaDeCaixas) {
		this.listaDeCaixas = listaDeCaixas;
	}

}
