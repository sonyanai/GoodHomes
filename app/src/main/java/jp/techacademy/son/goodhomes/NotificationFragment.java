package jp.techacademy.son.goodhomes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by taiso on 2018/04/03.
 */

public class NotificationFragment extends Fragment {
    public static final String TAG = "NotificationFragment";

    RadioGroup listRadioGroup;
    RadioButton okListButton;
    RadioButton yetListButton;
    String flag;
    DatabaseReference databaseReference;
    DatabaseReference businessPathRef;
    DatabaseReference customerPathRef;
    BusinessListAdapter bAdapter;
    ListView mListView;
    public ArrayList<BusinessListData> businessDataArrayList;





    ChildEventListener bEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(final DataSnapshot dataSnapshot, String s) {
            HashMap map = (HashMap) dataSnapshot.getValue();
            final String mUid = (String) map.get("mUid");
            final String companyName = (String) map.get("CompanyName");
            final String bitmapString = (String) map.get("BitmapString");

            BusinessListData post = new BusinessListData(mUid, companyName,bitmapString);
            businessDataArrayList.add(post);
            bAdapter.setOkBusinessDataArrayList(businessDataArrayList);
            mListView.setAdapter(bAdapter);
            bAdapter.notifyDataSetChanged();


        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        }
        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
        }
        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    };


/*
    ChildEventListener cEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(final DataSnapshot dataSnapshot, String s) {
            HashMap map = (HashMap) dataSnapshot.getValue();
            final String mUid = (String) map.get("mUid");
            final String companyName = (String) map.get("CompanyName");
            final String bitmapString = (String) map.get("BitmapString");

            BusinessListData post = new BusinessListData(mUid, companyName,bitmapString);
            okBusinessDataArrayList.add(post);
            bAdapter.setOkBusinessDataArrayList(okBusinessDataArrayList);
            mListView.setAdapter(bAdapter);
            bAdapter.notifyDataSetChanged();


        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        }
        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
        }
        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    };


*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_notification,container,false);

        listRadioGroup = (RadioGroup)v.findViewById(R.id.listRadioGroup);
        okListButton = (RadioButton)v.findViewById(R.id.okListButton);
        yetListButton = (RadioButton)v.findViewById(R.id.yetListButton);
        bAdapter = new BusinessListAdapter(this.getActivity(), R.layout.ok_list);
        businessDataArrayList = new ArrayList<BusinessListData>();
        mListView = (ListView)v.findViewById(R.id.listView);

        return v;
    }

    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        businessPathRef = databaseReference.child(Const.RequestEstimatePath).child(Const.BusinessPath);
        customerPathRef = databaseReference.child(Const.RequestEstimatePath).child(Const.CustomerPath);


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        flag = sp.getString(Const.FlagKEY, "");

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

    public void onRadioButtonClicked(View view){
        RadioButton radioButton = (RadioButton)view;
        boolean checked = radioButton.isChecked();
        switch (radioButton.getId()){
            case  R.id.okListButton:
                if (checked){
                    if (flag.equals("customer")){
                        //userPathRef.addChildEventListener(bEventListener);
                        //customerPathRef.child(Const.CustomerAcceptPath).addChildEventListener(cEventListener);
                    }else if (flag.equals("business")){
                        businessPathRef.child(Const.BusinessAcceptPath).addChildEventListener(bEventListener);
                    }
                }
                break;
            case R.id.yetListButton:
                if (checked){
                    if (flag.equals("customer")){
                        //customerPathRef.child(Const.CustomerRequestPath).addChildEventListener(cEventListener);
                    }else if (flag.equals("business")){
                        businessPathRef.child(Const.BusinessRequestPath).addChildEventListener(bEventListener);
                    }
                }
        }
    }

}
