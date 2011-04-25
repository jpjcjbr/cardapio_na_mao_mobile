package br.com.jplr.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Configuracao {
	
	@DatabaseField(id = true)
	private Integer id;
	
	@DatabaseField
	private String urlServidor;
	
	@DatabaseField
	private String emailUsuario;
	
	@DatabaseField
	private long dataAtualizacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrlServidor() {
		return urlServidor;
	}

	public void setUrlServidor(String urlServidor) {
		this.urlServidor = urlServidor;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public long getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(long dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public boolean isValida() {
		boolean isUrlServidorValida = urlServidor != null && !"".equals(urlServidor);
		boolean isEmailUsuario = emailUsuario != null && !"".equals(emailUsuario);
		
		return isUrlServidorValida && isEmailUsuario;
	}

}
