package android.examples.com.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by angel on 1/10/17.
 */

public class FruitAdapter extends BaseAdapter
{

    private Context context;
    private int layout;
    private List<Fruit> fruits;

    //Constructor
    public FruitAdapter(Context context, int layout, List<Fruit> fruits)
    {
        this.context = context;
        this.layout = layout;
        this.fruits = fruits;
    }

    @Override
    public int getCount() {
        return this.fruits.size();
    }

    @Override
    public Object getItem(int i) {
        return this.fruits.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;//this.getItemId(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {

        //Necesitamos un view holder para mantener la vista siempre
        ViewHolder holder;

        if(view == null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            view = layoutInflater.inflate(this.layout, null);
            holder = new ViewHolder();
            holder.nameTextView = (TextView) view.findViewById(R.id.textViewDescription);
            //Se pueden poner propiedades
            holder.originTextView = (TextView) view.findViewById(R.id.textViewOrigin);
            holder.icon = (ImageView) view.findViewById(R.id.imageView);
            view.setTag(holder);
        }

        else
        {
            holder = (ViewHolder)view.getTag();
        }

        String currentName = fruits.get(i).getName();
        holder.nameTextView.setText(currentName);
        holder.originTextView.setText(fruits.get(i).getOrigin());
        holder.icon.setImageResource(fruits.get(i).getIcon());

        return view;
    }

    static class ViewHolder
    {
        private TextView nameTextView;
        private TextView originTextView;
        private ImageView icon;
    }
}
