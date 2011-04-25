package br.com.jplr.view;

import java.sql.SQLException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import br.com.jplr.application.Atualizador;
import br.com.jplr.exception.UsuarioIncorretoException;
import br.com.jplr.model.Configuracao;
import br.com.jplr.utils.db.DatabaseHelper;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

public class Atualizacao extends OrmLiteBaseActivity<DatabaseHelper>{
	
	private ProgressDialog dialog;
	
	private Handler handler = new Handler();
	
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		setContentView(R.layout.progress_dialog);
		
		dialog = ProgressDialog.show(this, "Atualização", "Atualizando itens", false, true);
		
		try {
			Configuracao configuracao = getHelper().getConfigutacaoRepository().queryForId(1);
			
			if(configuracao == null || configuracao.getEmailUsuario() == null || "".equals(configuracao.getEmailUsuario()))
			{			
				handler.post(getTratadorErroAtualizacao());
			}
			else
			{
				new Thread(){
					@Override
					public void run() {
						try
						{
							new Atualizador().atualizar(getHelper());
							
							handler.post(new Runnable(){
								public void run() {
									dialog.dismiss();
									Atualizacao.this.setResult(Activity.RESULT_OK, null);
									Atualizacao.this.finish();
								}}
							);
						}
						catch(UsuarioIncorretoException uie)
						{
							handler.post(getTratadorErroAtualizacao());
						}
					}
				}.start();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			handler.post(getTratadorErroAtualizacao());
		}
	}

	private Runnable getTratadorErroAtualizacao() {
		return new TratadorErroAtualizacao(dialog, this);
	}
}

class TratadorErroAtualizacao implements Runnable{

	private ProgressDialog janelaProgresso;
	
	private Atualizacao atualizacao;
	
	public TratadorErroAtualizacao(ProgressDialog janelaProgresso, Atualizacao atualizacao)
	{
		this.janelaProgresso = janelaProgresso;
		this.atualizacao = atualizacao;
	}
	
	@Override
	public void run() {
		janelaProgresso.dismiss();
		atualizacao.setResult(Activity.RESULT_CANCELED, null);
		atualizacao.finish();
	}
	
}
