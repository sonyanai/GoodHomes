package jp.techacademy.son.goodhomes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by taiso on 2018/04/20.
 */


class ViewHoldersc{
    TextView nameTextView;
    TextView placeTextView;
}

public class CustomerDataArrayListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private int layoutId;
    private ArrayList<CustomerData> customerDataArrayList = new ArrayList<CustomerData>();

    public CustomerDataArrayListAdapter(Context context, int layoutId) {
        super();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layoutId = layoutId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String mName = customerDataArrayList.get(position).getName();
        String mPlace = customerDataArrayList.get(position).getPlace();


        ViewHoldersc holder;
        if (convertView == null) {
            // main.xml の <GridView .../> に grid_items.xml を inflate して convertView とする
            convertView = inflater.inflate(layoutId, parent, false);
            // FolderViewHolder を生成
            holder = new ViewHoldersc();
            holder.placeTextView = (TextView) convertView.findViewById(R.id.placeTextView);
            holder.nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHoldersc) convertView.getTag();
        }


        if (mName != null) {
            holder.nameTextView.setText(mName);
            holder.nameTextView.setTextSize(16.0f);
        }
        if (mPlace != null){
            holder.placeTextView.setText(mPlace);
        }



        return convertView;
    }

    @Override
    public int getCount() {
        // List<String> imgList の全要素数を返す
        return customerDataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        //return null;
        return customerDataArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
        //return businessDataArrayList.get(position).getId();
    }

    public void setCustomerDataArrayList(ArrayList<CustomerData> customerDataArrayList) {
        this.customerDataArrayList = customerDataArrayList;
    }
}



