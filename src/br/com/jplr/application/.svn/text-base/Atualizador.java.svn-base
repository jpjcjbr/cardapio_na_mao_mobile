package br.com.jplr.application;

import br.com.jplr.exception.UsuarioIncorretoException;
import br.com.jplr.model.Categoria;
import br.com.jplr.model.Configuracao;
import br.com.jplr.model.Item;
import br.com.jplr.model.json.Categorias;
import br.com.jplr.model.json.Itens;
import br.com.jplr.utils.db.DatabaseHelper;
import br.com.jplr.utils.http.HttpClient;
import br.com.jplr.utils.json.JSONParser;

public class Atualizador {
	
	private static final String IDENTIFICADOR_ERRO_USUARIO_INVALIDO = "{\"erro\":";

	public void atualizar(DatabaseHelper databaseHelper){
				
		try {
			Configuracao configuracao = databaseHelper.getConfigutacaoRepository().queryForId(1);
			
			databaseHelper.dropTable(databaseHelper.getConnectionSource());
			databaseHelper.createTable(databaseHelper.getConnectionSource());
			
			String categorias = getCategorias(configuracao.getUrlServidor(), configuracao.getEmailUsuario());
			String itens = getItens(configuracao.getUrlServidor(), configuracao.getEmailUsuario());
			
			Categorias listaCategorias = JSONParser.parse(categorias, Categorias.class);
			Itens listaItens = JSONParser.parse(itens, Itens.class);
						
			for(Categoria categoria: listaCategorias.getItens())
			{
				databaseHelper.getCategoriaRepository().create(categoria);
			}
			
			for(Item item: listaItens.getItens())
			{
				item.setCategoria(databaseHelper.getCategoriaRepository().queryForId(item.getCategoria_id()));
				
				if(item.getUrl_imagem() != null && !"".equals(item.getUrl_imagem()))
				{
					item.setImagem(HttpClient.getImagem(item.getUrl_imagem()));
				}
				
				databaseHelper.getItemRepository().create(item);
			}
			
		}catch (UsuarioIncorretoException uie) {
			throw new UsuarioIncorretoException(uie);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private String getItens(String urlServidor, String emailUsuario) {
		String retorno = HttpClient.getJSON(urlServidor + "/itens_json.json?email=" + emailUsuario);
		
		if(retorno.startsWith(IDENTIFICADOR_ERRO_USUARIO_INVALIDO))
		{
			throw new UsuarioIncorretoException();
		}
		
		return retorno;
	}

	private String getCategorias(String urlServidor,String emailUsuario) {
		return HttpClient.getJSON(urlServidor + "/categorias_json.json?email=" + emailUsuario);
	}
}
