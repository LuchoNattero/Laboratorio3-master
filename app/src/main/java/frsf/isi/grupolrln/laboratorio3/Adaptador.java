package frsf.isi.grupolrln.laboratorio3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by st on 19/09/2017.
 */
public class Adaptador extends BaseAdapter{

    private Context contexto;
    private List<Trabajo> lista_trabajadores;
    private LayoutInflater layout_inflater;


    public Adaptador(Context c, List<Trabajo>lista) {

        this.contexto = c;
        this.lista_trabajadores = lista;


    }

    class ViewHolder {

        private TextView profesion;
        private TextView proyecto;
        private TextView horaMax;
        private TextView fecha;
        private TextView hora_precio;
        private ImageView imagen;
        private CheckBox select;

        public ViewHolder(View v) {

            this.profesion = (TextView) v.findViewById(R.id.tv_prof);
            this.proyecto = (TextView) v.findViewById(R.id.tv_act);
            this.horaMax = (TextView)v.findViewById(R.id.tx_max);
            this.hora_precio = (TextView) v.findViewById(R.id.tx_hora);
            this.imagen = (ImageView) v.findViewById(R.id.img_lab);
            this.fecha = (TextView) v.findViewById(R.id.tx_fecha);
            this.select = (CheckBox) v.findViewById(R.id.id_chek);
            select.setEnabled(false);
        }
    }

    @Override
    public int getCount() {
        return lista_trabajadores.size();
    }

    @Override
    public Trabajo getItem(int i) {
        return lista_trabajadores.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(contexto).
                    inflate(R.layout.content_ofertalaboral, viewGroup, false);
        }

        ViewHolder holder = (ViewHolder) view.getTag();

        if (holder == null) {
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        Trabajo trabajo = getItem(i);

        holder.profesion.setText(trabajo.getCategoria().getDescripcion());

        holder.select.setChecked(trabajo.getRequiereIngles());

        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yy");

        holder.fecha.setText("Fecha Fin: "+ fecha.format(trabajo.getFechaEntrega()));
        holder.hora_precio.setText("Hora: "+trabajo.getHorasPresupuestadas()+ "Max $/Hora: "+trabajo.getPrecioMaximoHora());


        switch (trabajo.getMonedaPago()) {    //1 US$ 2Euro 3 AR$- 4 Libra 5 R$

            case 1: holder.imagen.setImageResource(R.drawable.us);
                break;
            case 2: holder.imagen.setImageResource(R.drawable.eu);
                break;
            case 3: holder.imagen.setImageResource(R.drawable.ar);
                break;
            case 4: holder.imagen.setImageResource(R.drawable.uk);
                break;
            case 5: holder.imagen.setImageResource(R.drawable.br);
                break;
        }
        return view;
    }
}