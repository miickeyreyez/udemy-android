package android.examples.com.views;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<String> nombres;

    public MyAdapter(Context context, int layout, List<String> nombres){
        this.context = context;
        this.layout = layout;
        this.nombres = nombres;
    }

    @Override
    public int getCount() {
        return this.nombres.size();
    }

    @Override
    public Object getItem(int i) {
        return this.nombres.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;//this.getItemId(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            view = layoutInflater.inflate(this.layout, null);
            holder = new ViewHolder();
            holder.nameTextView = (TextView) view.findViewById(R.id.textView);
            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)view.getTag();
        }
        
        String currentName = nombres.get(i);
        holder.nameTextView.setText(currentName);

        return view;
    }

    static class ViewHolder
    {
        private TextView nameTextView;
    }
}
