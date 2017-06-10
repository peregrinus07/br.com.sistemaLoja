package br.com.sistemaLoja.bean;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@RequestScoped
public class ImagemBean {

	@ManagedProperty("#{param.caminho}")
	private String caminho;
	private StreamedContent foto;

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public StreamedContent getFoto() {

		if (caminho == null || caminho.isEmpty()) {

			try {

				Path path = Paths.get("/home/tibe/upload/tux.png");
				InputStream fluxo = Files.newInputStream(path);
				foto = new DefaultStreamedContent(fluxo);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else {

			try {

				Path path = Paths.get("/home/tibe/upload/tux.png");
				InputStream fluxo;
				fluxo = Files.newInputStream(path);
				foto = new DefaultStreamedContent(fluxo);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return foto;

	}

	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}

}
