package ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.curesults.R;

import java.util.List;

/**
 * Created by winhtaikaung on 11/14/15.
 */
public class MySinneradapter extends ArrayAdapter<String> {
    private Context mContext;
    private List<String> mGeoTownshipsList;


    public MySinneradapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.mContext=context;
        this.mGeoTownshipsList=objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position,View convertview,ViewGroup parent){

        ViewHolder holder;
        if(convertview!=null){
            holder=(ViewHolder) convertview.getTag();
        }else{

            LayoutInflater inflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertview =inflater.inflate(R.layout.geo_item_row,null);
            holder=new ViewHolder();
            convertview.setTag(holder);
            holder.txt_geo_name=(TextView)convertview.findViewById(R.id.txt_geo_name);
        }
        String township=mGeoTownshipsList.get(position);
        holder.txt_geo_name.setText(township);

        return convertview;
    }

    static class ViewHolder{
        TextView txt_geo_name;
    }
}
