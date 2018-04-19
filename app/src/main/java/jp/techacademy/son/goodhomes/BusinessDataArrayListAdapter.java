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
 * Created by taiso on 2018/04/17.
 */


class ViewHolder {
    ImageView companyImageView;
    TextView companyNameTextView;
    TextView totalEvaluationTextView;
    TextView moneyEvaluationTextView;
}

public class BusinessDataArrayListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private int layoutId;
    private ArrayList<BusinessData> businessDataArrayList = new ArrayList<BusinessData>();

    public BusinessDataArrayListAdapter(Context context, int layoutId) {
        super();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layoutId = layoutId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String mBitmapString = businessDataArrayList.get(position).getBitmapString();
        String mCompanyName = businessDataArrayList.get(position).getCompanyName();
        String mTotalEvaluation = businessDataArrayList.get(position).getTotalEvaluation();
        String mMoneyEvaluation = businessDataArrayList.get(position).getMoneyEvaluation();


        ViewHolder holder;
        if (convertView == null) {
            // main.xml の <GridView .../> に grid_items.xml を inflate して convertView とする
            convertView = inflater.inflate(layoutId, parent, false);
            // FolderViewHolder を生成
            holder = new ViewHolder();
            holder.companyImageView = (ImageView) convertView.findViewById(R.id.companyImageView);
            holder.companyNameTextView = (TextView) convertView.findViewById(R.id.companyNameTextView);
            holder.totalEvaluationTextView = (TextView) convertView.findViewById(R.id.totalEvaluationTextView);
            holder.moneyEvaluationTextView = (TextView) convertView.findViewById(R.id.moneyEvaluationTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        if (mCompanyName != null) {
            holder.companyNameTextView.setText(mCompanyName);
            holder.companyNameTextView.setTextSize(16.0f);

        }
        if (mTotalEvaluation != null) {
            holder.totalEvaluationTextView.setText("総合評価　" + mTotalEvaluation);
        }
        if (mMoneyEvaluation != null) {
            holder.moneyEvaluationTextView.setText("平均見積り誤差　" + mMoneyEvaluation);
        }
        if (mBitmapString != null){
            if (mBitmapString.length()>10) {
                byte[] bytes = Base64.decode(mBitmapString,Base64.DEFAULT);
                if(bytes.length != 0){
                    Bitmap image = BitmapFactory.decodeByteArray(bytes,0,bytes.length).copy(Bitmap.Config.ARGB_8888,true);
                    holder.companyImageView.setImageBitmap(image);
                }
            }
        }



        return convertView;
    }

    @Override
    public int getCount() {
        // List<String> imgList の全要素数を返す
        return businessDataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        //return null;
        return businessDataArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
        //return businessDataArrayList.get(position).getId();
    }

    public void setBusinessDataArrayList(ArrayList<BusinessData> businessDataArrayList) {
        this.businessDataArrayList = businessDataArrayList;
    }
}



