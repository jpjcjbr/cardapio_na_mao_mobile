package br.com.jplr.utils.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.jplr.model.Categoria;
import br.com.jplr.model.Configuracao;
import br.com.jplr.model.Item;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "banco_cardapio";
	private static final int DATABASE_VERSION = 1;

	private Dao<Item, Integer> itemRepository;
	
	private Dao<Categoria, Integer> categoriaRepository;
	
	private Dao<Configuracao, Integer> configutacaoRepository;

	public DatabaseHelper(Context context) throws SQLException {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
		try {
			createTable(connectionSource);
			TableUtils.createTable(connectionSource, Configuracao.class);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Erro ao criar Banco", e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
		try {
			TableUtils.dropTable(connectionSource, Configuracao.class, true);
			
			dropTable(connectionSource);
			onCreate(sqliteDatabase, connectionSource);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Erro ao atualizar banco", e);
		}
	}
	
	public void createTable(ConnectionSource connectionSource) throws SQLException
	{
		TableUtils.createTable(connectionSource, Item.class);
		TableUtils.createTable(connectionSource, Categoria.class);
	}
	
	public void dropTable(ConnectionSource connectionSource) throws SQLException
	{
		TableUtils.dropTable(connectionSource, Item.class, true);
		TableUtils.dropTable(connectionSource, Categoria.class, true);
	}

	public Dao<Item, Integer> getItemRepository() throws SQLException {
		if(itemRepository == null)
		{
			itemRepository = BaseDaoImpl.createDao(getConnectionSource(), Item.class);
		}
		
		return itemRepository;
	}

	public Dao<Categoria, Integer> getCategoriaRepository() throws SQLException {
		if(categoriaRepository == null)
		{
			categoriaRepository = BaseDaoImpl.createDao(getConnectionSource(), Categoria.class);
		}
		
		return categoriaRepository;
	}

	public Dao<Configuracao, Integer> getConfigutacaoRepository() throws SQLException {
		if(configutacaoRepository == null)
		{
			configutacaoRepository = BaseDaoImpl.createDao(getConnectionSource(), Configuracao.class);
		}
		
		return configutacaoRepository;
	}
}
