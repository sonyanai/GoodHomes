package jp.techacademy.son.goodhomes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    CustomerListAdapter cAdapter;
    ListView mListView;
    FirebaseUser user;
    public ArrayList<BusinessListData> businessDataArrayList;
    public ArrayList<CustomerListData> customerDataArrayList;
    //acceptかrequestかrequestのときはcustomeraccountに許可ボタン表示
    String arFlag;




    ChildEventListener cEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(final DataSnapshot dataSnapshot, String s) {
            HashMap map = (HashMap) dataSnapshot.getValue();
            final String mUid = (String) map.get("mUid");
            final String companyName = (String) map.get("companyName");
            final String bitmapString = (String) map.get("bitmapString");
            final String industry = (String)map.get("industry");
            final String key = (String)map.get("key");

            BusinessListData post = new BusinessListData(mUid, companyName,bitmapString,industry,key);

            if (businessDataArrayList.size()==0){
                businessDataArrayList.add(post);
            }
            for (BusinessListData aaa : businessDataArrayList){
                if (!(aaa.getUid().equals(post.getUid()))){
                    businessDataArrayList.add(post);
                }
            }
            bAdapter.setBusinessDataArrayList(businessDataArrayList);
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



    ChildEventListener bEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(final DataSnapshot dataSnapshot, String s) {
            HashMap map = (HashMap) dataSnapshot.getValue();
            final String mUid = (String) map.get("mUid");
            final String name = (String) map.get("name");
            final String place = (String) map.get("place");
            final String key1 = (String)map.get("key1");
            final String key2 = (String)map.get("key2");

            CustomerListData post = new CustomerListData(mUid,name,place,key1,key2);

            if (customerDataArrayList.size()==0){
                customerDataArrayList.add(post);
            }
            for (CustomerListData aaa : customerDataArrayList){
                if (!(aaa.getUid().equals(post.getUid()))){
                    customerDataArrayList.add(post);
                }
            }
            cAdapter.setCustomerDataArrayList(customerDataArrayList);
            mListView.setAdapter(cAdapter);
            cAdapter.notifyDataSetChanged();


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





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_notification,container,false);

        listRadioGroup = (RadioGroup)v.findViewById(R.id.listRadioGroup);
        okListButton = (RadioButton)v.findViewById(R.id.okListButton);
        yetListButton = (RadioButton)v.findViewById(R.id.yetListButton);
        bAdapter = new BusinessListAdapter(this.getActivity(), R.layout.business_list);
        cAdapter = new CustomerListAdapter(this.getActivity(), R.layout.customer_list);
        businessDataArrayList = new ArrayList<BusinessListData>();
        customerDataArrayList = new ArrayList<CustomerListData>();
        mListView = (ListView)v.findViewById(R.id.listView);

        return v;
    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        businessPathRef = databaseReference.child(Const.RequestEstimatePath).child(Const.BusinessPath);
        customerPathRef = databaseReference.child(Const.RequestEstimatePath).child(Const.CustomerPath);
        user = FirebaseAuth.getInstance().getCurrentUser();


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        flag = sp.getString(Const.FlagKEY, "");

        okListButton.setChecked(true);


        String uid = user.getUid();
        if (flag.equals("customer")){
            okListButton.setText("相談");
            yetListButton.setText("見積り申請中");

            businessDataArrayList.clear();
            businessPathRef.child(Const.BusinessAcceptPath).child(uid).addChildEventListener(bEventListener);

              }else if (flag.equals("business")){
            okListButton.setText("商談");
            yetListButton.setText("見積り申請");

            customerDataArrayList.clear();
            customerPathRef.child(Const.CustomerAcceptPath).child(uid).addChildEventListener(cEventListener);
        }





        listRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton radioButton = (RadioButton) view.findViewById(checkedId);
                String uid = user.getUid();
                if (okListButton.isChecked() == true){
                    if (flag.equals("customer")){
                        businessDataArrayList.clear();
                        customerPathRef.child(Const.CustomerAcceptPath).child(uid).addChildEventListener(cEventListener);

                        cAdapter.setCustomerDataArrayList(customerDataArrayList);
                        mListView.setAdapter(cAdapter);
                        cAdapter.notifyDataSetChanged();
                    }else if (flag.equals("business")){
                        customerDataArrayList.clear();
                        businessPathRef.child(Const.BusinessAcceptPath).child(uid).addChildEventListener(bEventListener);


                        bAdapter.setBusinessDataArrayList(businessDataArrayList);
                        mListView.setAdapter(bAdapter);
                        bAdapter.notifyDataSetChanged();


                    }
                }else{
                    if (flag.equals("customer")){
                        businessDataArrayList.clear();
                        customerPathRef.child(Const.CustomerRequestPath).child(uid).addChildEventListener(cEventListener);

                        cAdapter.setCustomerDataArrayList(customerDataArrayList);
                        mListView.setAdapter(cAdapter);
                        cAdapter.notifyDataSetChanged();
                        arFlag ="request";
                        //customerPathRef.child(Const.CustomerRequestPath).addChildEventListener(cEventListener);
                    }else if (flag.equals("business")){


                        customerDataArrayList.clear();
                        businessPathRef.child(Const.BusinessRequestPath).child(uid).addChildEventListener(bEventListener);

                        bAdapter.setBusinessDataArrayList(businessDataArrayList);
                        mListView.setAdapter(bAdapter);
                        bAdapter.notifyDataSetChanged();

                        arFlag ="request";

                    }
                }
            }
        });


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (flag.equals("customer")){
                    Bundle bundle = new Bundle();
                    bundle.putString("Uid", businessDataArrayList.get(position).getUid());
                    if (arFlag!=null){
                        bundle.putString("arFlag",arFlag);
                    }
                    bundle.putString("key", businessDataArrayList.get(position).getKey());

                    BusinessAccountFragment fragmentBusinessAccount = new BusinessAccountFragment();
                    fragmentBusinessAccount.setArguments(bundle);

                    getFragmentManager().beginTransaction()
                            .replace(R.id.container,fragmentBusinessAccount,BusinessAccountFragment.TAG)
                            .commit();

                }else{
                    Bundle bundle = new Bundle();
                    bundle.putString("Uid", customerDataArrayList.get(position).getUid());
                    if (arFlag!=null){
                        bundle.putString("arFlag",arFlag);
                    }
                    bundle.putString("key1", customerDataArrayList.get(position).getKey1());
                    bundle.putString("key2", customerDataArrayList.get(position).getKey2());


                    CustomerAccountFragment fragmentCustomerAccount = new CustomerAccountFragment();
                    fragmentCustomerAccount.setArguments(bundle);

                    getFragmentManager().beginTransaction()
                            .replace(R.id.container,fragmentCustomerAccount,BusinessAccountFragment.TAG)
                            .commit();

                }





            }
        });


    }
}
