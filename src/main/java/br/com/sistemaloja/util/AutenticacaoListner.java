package br.com.sistemaloja.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.sistemaLoja.bean.AutenticacaoBean;

@SuppressWarnings("serial")
public class AutenticacaoListner  implements PhaseListener{

	@Override
	public void afterPhase(PhaseEvent arg0) {

		System.out.println("Inicio depois da Fase: "+arg0.getPhaseId());
		
		AutenticacaoBean autenticacaoBean	= Faces.getSessionAttribute("autenticacaoBean");
		
		String paginaAtual = Faces.getViewId();
		
		System.out.println("Autenticação: "+ autenticacaoBean);
		
		System.out.println("Pagina Atual: "+paginaAtual );
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		 
		System.out.println("Inicio Antes da Fase: "+ arg0.getPhaseId());
		
	}

	@Override
	public PhaseId getPhaseId() {
		 
		return PhaseId.ANY_PHASE;
	}
	
	

}
