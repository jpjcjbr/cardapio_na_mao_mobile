package br.com.jplr.view;

import java.sql.SQLException;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import br.com.jplr.model.Configuracao;
import br.com.jplr.utils.db.DatabaseHelper;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

public class Configuracoes extends OrmLiteBaseActivity<DatabaseHelper>{
	
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.configuracoes);
		
		try
		{
			final Configuracao configuracao = getConfiguracao();
			
			final TextView txtEmailUsuario = (TextView) findViewById(R.id.txtEmailUsuario);
			final TextView txtServidor = (TextView) findViewById(R.id.txtServidor);
			
			txtEmailUsuario.setText(configuracao.getEmailUsuario());
			txtServidor.setText(configuracao.getUrlServidor());
			
			Button btnSalvarConfiguracoes = (Button) findViewById(R.id.btnSalvarConfiguracoes);
			
			btnSalvarConfiguracoes.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					String urlServidor = txtServidor.getText().toString();
					String emailUsuario = txtEmailUsuario.getText().toString().toLowerCase();
	
					configuracao.setEmailUsuario(emailUsuario);
					configuracao.setUrlServidor(urlServidor);
					
					try {
						getHelper().getConfigutacaoRepository().update(configuracao);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					finish();
				}
			});
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private Configuracao getConfiguracao() throws SQLException {
		Dao<Configuracao, Integer> configutacaoRepository = getHelper().getConfigutacaoRepository();
		
		Configuracao configuracao = configutacaoRepository.queryForId(1);
		
		if(configuracao == null || !configuracao.isValida())
		{
			configuracao = new Configuracao();
			configuracao.setId(1);
//			configuracao.setUrlServidor("http://10.0.2.2:3000");
			configuracao.setUrlServidor("http://www.cardapionamao.com.br");
			configuracao.setEmailUsuario("usuario@exemplo.com.br");
			configutacaoRepository.create(configuracao);
		}
		
		return configuracao;
	}
}
