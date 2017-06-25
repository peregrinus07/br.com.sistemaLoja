package br.com.sistemaloja.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.sistemaLoja.domain.Caixa;

public class CaixaDaoTest {

	@Test
	@Ignore
	public void salvar() throws Exception {
		
		Caixa caixa = new Caixa();
		caixa.setDataDeAbertura(new SimpleDateFormat("dd/MM/yyyy").parse("15/12/2015"));
		caixa.setValor(new BigDecimal("100.00"));
		
		CaixaDao caixaDao = new  CaixaDao();
		caixaDao.salvar(caixa);
		 
	}

	
	@Test
	@Ignore
	public void buscar() throws ParseException, Exception {
		
		CaixaDao caixaDao = new CaixaDao();
		Caixa caixa = caixaDao.buscarPorDataDeAbertura(new SimpleDateFormat("dd/MM/yyyy").parse("13/12/2015"));
		System.out.println(caixa);
		Assert.assertNull(caixa);
		
		
		
		
	}

}
