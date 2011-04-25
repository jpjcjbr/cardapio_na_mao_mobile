package br.com.jplr.view.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.jplr.model.Item;
import br.com.jplr.view.R;

public class ItemAdapter extends BaseAdapter {
	
	private List<Item> itens;
	private LayoutInflater mInflater;

	public ItemAdapter(Context context, List<Item> itens){
		this.mInflater = LayoutInflater.from(context);
		this.itens = itens;
	}
	
	public int getCount() {
		return itens.size();
	}

	public Object getItem(int position) {
		return itens.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

        Duplo duplo;

        if (convertView == null) {

              duplo = new Duplo();

              convertView = mInflater.inflate(R.layout.row, null);
              
              duplo.txt0 = (TextView)convertView.findViewById(R.id.txtId);
              duplo.txt1 = (TextView)convertView.findViewById(R.id.txtNome);
              duplo.txt2 = (TextView)convertView.findViewById(R.id.txtDescricao);
              duplo.txt3 = (TextView)convertView.findViewById(R.id.txtPreco);

              convertView.setTag(duplo);

        } else {
              duplo = (Duplo)convertView.getTag();
        }
        
			duplo.txt0.setText(itens.get(position).getId() + " - ");
			duplo.txt1.setText(itens.get(position).getNome());
			duplo.txt2.setText(itens.get(position).getDescricao());
			duplo.txt3.setText(String.valueOf(itens.get(position).getPreco()));

		return convertView;
	}
	
    class Duplo {
    	TextView txt0;
        TextView txt1;
        TextView txt2;
        TextView txt3;
    }
}
