package br.com.jplr.view;

import java.sql.SQLException;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import br.com.jplr.model.Categoria;
import br.com.jplr.model.Item;
import br.com.jplr.utils.db.DatabaseHelper;
import br.com.jplr.view.adapter.ItemAdapter;

import com.j256.ormlite.android.apptools.OrmLiteBaseTabActivity;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

public class CardapioDigital extends OrmLiteBaseTabActivity<DatabaseHelper> implements TabContentFactory {
	
	private static final int CONFIGURACOES = 0;
	private static final int ATUALIZAR = 1;
	
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		TabHost tabHost = getTabHost();

		List<Categoria> categorias = getCategorias();
		
		if(categorias == null || categorias.size() == 0)
		{
			atualizar();
		}

		if(categorias != null && categorias.size() > 0)
		{
			for(Categoria categoria: categorias)
			{
				adicionarAba(tabHost, categoria);
			}
		}
		else
		{
			adicionarAba(tabHost, new Categoria("Instruções"));
		}
	}
	
	private void adicionarAba(TabHost tabHost, Categoria categoria) {
		TabSpec aba = tabHost.newTabSpec("" + categoria.getId());
		aba.setContent(this);
		aba.setIndicator(categoria.getNome());
		tabHost.addTab(aba);
	}

	public List<Categoria> getCategorias()
	{
		try {
			return getHelper().getCategoriaRepository().queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public View createTabContent(String categoria) {
		int backgroundColor = getResources().getColor(R.color.background);
		
		final ListView listaItens = new ListView(this);
		listaItens.setBackgroundColor(backgroundColor);
		listaItens.setCacheColorHint(backgroundColor);
		List<Item> itens = getItens(categoria);
		
		listaItens.setAdapter(new ItemAdapter(this, itens));
		
		final CardapioDigital cardapio = this;
		
		listaItens.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int posicao, long arg3) {
				Item itemSelecionado = (Item) listaItens.getItemAtPosition(posicao);
				
				Intent intent = new Intent(cardapio, Detalhe.class);
				intent.putExtra(Item.ID_ITEM, itemSelecionado.getId());
				startActivity(intent);
				
			}
		});
				
		return listaItens;
	}

	private List<Item> getItens(String idCategoria) {
		try {
			QueryBuilder<Item, Integer> queryBuilder = getHelper().getItemRepository().queryBuilder();
			queryBuilder.where().eq("categoria_id", idCategoria);
			queryBuilder.orderBy("nome", true);
			PreparedQuery<Item> query = queryBuilder.prepare();
			List<Item> itens = getHelper().getItemRepository().query(query);
			
			return itens;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		MenuItem item = menu.add(0, CONFIGURACOES, 0, "Configurações");
		item.setIcon(android.R.drawable.ic_menu_preferences);
		
		item = menu.add(0, ATUALIZAR, 0, "Atualizar");
		item.setIcon(android.R.drawable.ic_menu_rotate);
		
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case CONFIGURACOES:
			configurar();
			return true;
		case ATUALIZAR:
			atualizar();
			return true;
		}
		return false;
	}

	private void configurar() {
		startActivityForResult(new Intent(this, Configuracoes.class), CONFIGURACOES);
	}

	private void atualizar() {
		startActivityForResult(new Intent(this, Atualizacao.class), ATUALIZAR);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if(resultCode == RESULT_OK)
		{
			switch (requestCode) {
			case ATUALIZAR:
				
				AlertDialog.Builder alerta = new AlertDialog.Builder(this);
				alerta.setTitle("Atualização");
				alerta.setMessage("Itens atualizados com sucesso.");
				
				alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						CardapioDigital.this.finish();
						CardapioDigital.this.startActivity(getIntent());
					}
				});
				
				alerta.show();
				
				break;
			}
		}
		else if(resultCode == RESULT_CANCELED)
		{
			switch (requestCode) {
			case ATUALIZAR:
				AlertDialog.Builder alerta = new AlertDialog.Builder(this);
				alerta.setTitle("Usuário não configurado");
				alerta.setMessage("Favor configurar o email do usuário na tela de configurações.");
				
				alerta.setPositiveButton("Configurar", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						configurar();
					}
				});
				
				alerta.show();
				
				break;
			}
		}
	}
}