package br.com.jplr.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Categoria {

	@DatabaseField(id = true)
	private Integer id;
	
	@DatabaseField
	private String nome;

	public Categoria(String nome) {
		super();
		this.nome = nome;
	}
	
	public Categoria() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
