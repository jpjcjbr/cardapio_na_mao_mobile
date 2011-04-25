package br.com.jplr.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Item {

	public static final String ID_ITEM = "idItem";

	@DatabaseField(id = true)
	private Integer id;
	
	@DatabaseField
	private String nome;
	
	@DatabaseField
	private String descricao;
	
	@DatabaseField
	private float preco; 
	
	@DatabaseField
	private byte[] imagem;
	
	public Item(){
		
	}
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Categoria categoria;
	
	private Integer categoria_id;
	
	private String url_imagem;
	
	public Item(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Integer getCategoria_id() {
		return categoria_id;
	}

	public void setCategoria_id(Integer categoria_id) {
		this.categoria_id = categoria_id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public String getUrl_imagem() {
		return url_imagem;
	}

	public void setUrl_imagem(String url_imagem) {
		this.url_imagem = url_imagem;
	}
}
