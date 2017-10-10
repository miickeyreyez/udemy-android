package com.udemy.cardrecyclerview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udemy.models.Fruta;

import java.util.List;

/**
 * Created by angel on 8/10/17.
 */

public class FrutaAdapter extends RecyclerView.Adapter<FrutaAdapter.ViewHolder>
{
    private List<Fruta> frutas;
    private int layout;
    private OnItemClickListener listener;
    private Context context;
    private Activity activity;

    public FrutaAdapter(List<Fruta> frutas, int layout, Activity activity, OnItemClickListener listener)
    {
        this.frutas = frutas;
        this.layout = layout;
        this.listener = listener;
        this.activity = activity;
    }

    //Creamos el viewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //Cambiar los datos del textView
    @Override
    public void onBindViewHolder(FrutaAdapter.ViewHolder holder, int position)
    {
        holder.bind(frutas.get(position),listener);
    }

    //Obtener el tamaño de la lista
    @Override
    public int getItemCount()
    {
        return frutas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener
    {
        public TextView name;
        public TextView description;
        public TextView cantidad;
        public ImageView avatar;

        public ViewHolder(View v)
        {
            super(v);
            name = (TextView) itemView.findViewById(R.id.txtFrutaCardView);
            description = (TextView) itemView.findViewById(R.id.txtFrutaDescriptionCardView);
            cantidad = (TextView) itemView.findViewById(R.id.txtFrutaCantidadCardView);
            avatar = (ImageView) itemView.findViewById(R.id.imageViewFruta);
            //Para actualizar el contexto
            itemView.setOnCreateContextMenuListener(this);
        }

        public void bind(final Fruta fruta, final OnItemClickListener listener)
        {
            name.setText(fruta.getName());
            description.setText(fruta.getDescription());
            cantidad.setText("" + fruta.getQuantity());
            if (fruta.getQuantity() == Fruta.LIMIT_QUANTITY)
            {
                cantidad.setTextColor(ContextCompat.getColor(activity, R.color.colorAccent));
                cantidad.setTypeface(null, Typeface.BOLD);
            }
            else
            {
                cantidad.setTextColor(ContextCompat.getColor(activity, R.color.colorPrimary));
                cantidad.setTypeface(null, Typeface.NORMAL);
            }
            Picasso.with(context).load(fruta.getImgBackground()).fit().into(avatar);
            //Seteamos solamente el onClickListener en el avatar
            this.avatar.setOnClickListener(new View.OnClickListener()
                                           {
                                               @Override
                                               public void onClick(View view)
                                               {
                                                   //Método para obtener la posición
                                                   listener.onItemClick(fruta, getAdapterPosition());
                                               }
                                           }
            );
        }

        //Sobre escribimos el onCreateContexMenu
        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo)
        {
            // Recogemos la posición con el método getAdapterPosition
            Fruta fruta = frutas.get(this.getAdapterPosition());
            // Establecemos título e icono para cada elemento, mirando en sus propiedades
            contextMenu.setHeaderTitle(fruta.getName());
            contextMenu.setHeaderIcon(R.mipmap.ic_launcher);
            // Inflamos el menú
            MenuInflater inflater = activity.getMenuInflater();
            inflater.inflate(R.menu.context_menu_fruit, contextMenu);
            // Por último, añadimos uno por uno, el listener onMenuItemClick para
            // controlar las acciones en el contextMenu, anteriormente lo manejábamos
            // con el método onContextItemSelected en el activity
            for (int i = 0; i < contextMenu.size(); i++)
                contextMenu.getItem(i).setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem)
        {
            // No obtenemos nuestro objeto info
            // porque la posición la podemos rescatar desde getAdapterPosition
            switch (menuItem.getItemId())
            {
                case R.id.deleteFruit:
                    // Observa que como estamos dentro del adaptador, podemos acceder
                    // a los métodos propios de él como notifyItemRemoved o notifyItemChanged
                    frutas.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                case R.id.reset:
                    frutas.get(getAdapterPosition()).resetQuantity();
                    notifyItemChanged(getAdapterPosition());
                    return true;
                default:
                    return false;
            }
        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(Fruta fruta, int position);
    }

}
