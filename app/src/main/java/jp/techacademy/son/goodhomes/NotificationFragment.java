package jp.techacademy.son.goodhomes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * Created by taiso on 2018/04/03.
 */

public class NotificationFragment extends Fragment {
    public static final String TAG = "NotificationFragment";

    RadioGroup listRadioGroup;
    RadioButton okListButton;
    RadioButton yetListButton;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_notification,container,false);

        listRadioGroup = (RadioGroup)v.findViewById(R.id.listRadioGroup);
        okListButton = (RadioButton)v.findViewById(R.id.okListButton);
        yetListButton = (RadioButton)v.findViewById(R.id.yetListButton);

        return v;
    }

    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String flag = sp.getString(Const.FlagKEY, "");

        if (flag!=null){

        }

        if (flag.equals("customer")){
            okListButton.setText("相談中");
            yetListButton.setText("申請中");
        }else if (flag.equals("business")){
            okListButton.setText("商談中");
            yetListButton.setText("リクエスト");
        }




    }

}
