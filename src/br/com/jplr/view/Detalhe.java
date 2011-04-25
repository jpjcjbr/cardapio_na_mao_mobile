package br.com.jplr.view;

import java.sql.SQLException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.jplr.model.Item;
import br.com.jplr.utils.db.DatabaseHelper;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

public class Detalhe extends OrmLiteBaseActivity<DatabaseHelper>
{
	@Override
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		setContentView(R.layout.detalhe);
		
		Bundle extras = getIntent().getExtras();
		if(extras != null){

			Integer idItemSelecionado = extras.getInt(Item.ID_ITEM);

			TextView nome = (TextView) findViewById(R.id.txtNome);
			ImageView image = (ImageView) findViewById(R.id.imgItem);
			TextView descricao = (TextView) findViewById(R.id.txtDescricao);
			TextView preco = (TextView) findViewById(R.id.txtPreco);
			
			try {
				Item item = getHelper().getItemRepository().queryForId(idItemSelecionado);
				
				nome.setText(item.getNome());
				
				Bitmap arrayBytes = BitmapFactory.decodeByteArray(item.getImagem(), 0, item.getImagem().length);
				image.setImageBitmap(arrayBytes);
				
				descricao.setText(item.getDescricao());
				
				preco.setText("R$ "+ item.getPreco());
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception ex){
				image.setBackgroundResource(R.drawable.noimage);
				descricao.setText(R.string.erro_carregar_item);
			}
			

		}
		
	}
}