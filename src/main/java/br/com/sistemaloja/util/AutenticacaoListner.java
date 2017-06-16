package br.com.sistemaloja.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.sistemaLoja.bean.AutenticacaoBean;
import br.com.sistemaLoja.domain.Usuario;

@SuppressWarnings("serial")
public class AutenticacaoListner implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent arg0) {

		String paginaAtual = Faces.getViewId();

		boolean ehPaginaDeAutenticação = paginaAtual.contains("autenticacao.xhtml");

		if (!ehPaginaDeAutenticação) {

			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");

			if (autenticacaoBean == null) {

				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}

			Usuario usuario = autenticacaoBean.getUsuarioLogado();

			if (usuario == null) {

				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}

		}

	}

	@Override
	public void beforePhase(PhaseEvent arg0) {

		//System.out.println("Inicio Antes da Fase: " + arg0.getPhaseId());

	}

	@Override
	public PhaseId getPhaseId() {

		return PhaseId.ANY_PHASE;
	}

}
