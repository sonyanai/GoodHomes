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
    String flags;
    DatabaseReference databaseReference;
    DatabaseReference businessPathRef;
    DatabaseReference customerPathRef;
    DatabaseReference businessUserPathRef;
    DatabaseReference customerUserPathRef;
    BusinessDataArrayListAdapter bAdapter;
    CustomerDataArrayListAdapter cAdapter;
    ListView mListView;
    FirebaseUser user;
    public ArrayList<BusinessData> businessDataArrayList;
    public ArrayList<BusinessData> nothingBusinessDataArrayList;
    public ArrayList<CustomerData> customerDataArrayList;
    //acceptかrequestかrequestのときはcustomeraccountに許可ボタン表示
    String arFlag;
    String myUid;
    String myName;
    String myPostalCode;
    String myAgeBuild;
    String myForm;
    String myOtherForm;
    String myPro;
    String myOtherPro;
    String myPlace;
    String myOtherPlace;
    String myBudget;
    String myAge;
    String mySex;
    String myEstimate;
    String myRequestEstimate;
    String myRequest;
    String myFlag;
    String myCompanyName;
    String myAddress;
    String myCompanyNumber;
    String myBitmapString;
    String myTotalEstimate;
    String myUnwatchEstimate;
    String myThisPayment;
    String myNextPayment;
    String myTotalEvaluation;
    String myMoneyEvaluation;
    String myIndustry;
    String myPr;
    String Uid;




    ChildEventListener cEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(final DataSnapshot dataSnapshot, String s) {
            HashMap map = (HashMap) dataSnapshot.getValue();
            final String mUid = (String) map.get("mUid");
            final String companyName = (String) map.get("CompanyName");
            final String address = (String) map.get("Address");
            final String companyNumber = (String) map.get("CompanyNumber");
            final String name = (String) map.get("name");
            final String bitmapString = (String) map.get("BitmapString");
            final String totalEstimate = (String) map.get("TotalEstimate");
            final String unwatchEstimate = (String) map.get("UnwatchEstimate");
            final String thisPayment = (String) map.get("ThisPayment");
            final String nextPayment = (String) map.get("NextPayment");
            final String totalEvaluation = (String) map.get("TotalEvaluation");
            final String moneyEvaluation = (String) map.get("MoneyEvaluation");
            final String industry = (String) map.get("Industry");
            final String pr = (String) map.get("Pr");
            final String flag = (String)map.get("flag");

            BusinessData post = new BusinessData(mUid, companyName,address,companyNumber,name,bitmapString,totalEstimate,unwatchEstimate,thisPayment,nextPayment,totalEvaluation,moneyEvaluation,industry,pr,flag);


            if (businessDataArrayList.size()==0){
                businessDataArrayList.add(post);
            }else {
                for (BusinessData aaa : businessDataArrayList){
                    if (!(aaa.getUid().equals(post.getUid()))){
                        businessDataArrayList.add(post);
                    }
                }
            }
            Uid = user.getUid();

            if (post.getUid().equals(Uid)){
                myUid = post.getUid();
                myCompanyName = post.getCompanyName();
                myAddress = post.getAddress();
                myCompanyNumber = post.getCompanyNumber();
                myName = post.getName();
                myBitmapString = post.getBitmapString();
                myTotalEstimate = post.getTotalEstimate();
                myUnwatchEstimate = post.getUnwatchEstimate();
                myThisPayment = post.getThisPayment();
                myNextPayment = post.getNextPayment();
                myTotalEvaluation = post.getTotalEvaluation();
                myMoneyEvaluation = post.getMoneyEvaluation();
                myIndustry = post.getIndustry();
                myPr = post.getPr();
                myFlag = post.getFlag();
            }



            if (flags.equals("business")){
                businessDataArrayList.clear();
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
            final String name = (String) map.get("UserName");
            final String postalCode = (String) map.get("postalCode");
            final String ageBuild = (String) map.get("ageBuild");
            final String form = (String) map.get("form");
            final String otherForm = (String) map.get("otherForm");
            final String pro = (String) map.get("pro");
            final String otherPro = (String) map.get("otherPro");
            final String place = (String) map.get("place");
            final String otherPlace = (String) map.get("otherPlace");
            final String budget = (String) map.get("budget");
            final String age = (String) map.get("age");
            final String sex = (String) map.get("sex");
            final String estimate = (String) map.get("estimate");
            final String requestEstimate = (String) map.get("requestEstimate");
            final String request = (String) map.get("request");
            final String flag = (String) map.get("flag");

            CustomerData post = new CustomerData(mUid, name, postalCode,ageBuild,form,otherForm,pro,otherPro,place,otherPlace,budget,age,sex,estimate,requestEstimate,request,flag);

            if (customerDataArrayList.size()==0){
                customerDataArrayList.add(post);
            }else {
                for (CustomerData aaa : customerDataArrayList){
                    if (!(aaa.getUid().equals(post.getUid()))){
                        customerDataArrayList.add(post);
                    }
                }
            }

            if (post.getUid().equals(Uid)){
                myUid = post.getUid();
                myName = post.getName();
                myPostalCode = post.getPostalCode();
                myAgeBuild = post.getAgeBuild();
                myForm = post.getForm();
                myOtherForm = post.getOtherForm();
                myPro = post.getPro();
                myOtherPro = post.getOtherPro();
                myPlace = post.getPlace();
                myOtherPlace = post.getOtherPlace();
                myBudget = post.getBudget();
                myAge = post.getAge();
                mySex = post.getSex();
                myEstimate = post.getEstimate();
                myRequestEstimate = post.getRequestEstimate();
                myRequest = post.getRequest();
                myFlag = post.getFlag();

            }

            if (flags.equals("customer")){
                customerDataArrayList.clear();
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
        bAdapter = new BusinessDataArrayListAdapter(this.getActivity(), R.layout.new_list);
        cAdapter = new CustomerDataArrayListAdapter(this.getActivity(), R.layout.customer_list);
        businessDataArrayList = new ArrayList<BusinessData>();
        nothingBusinessDataArrayList = new ArrayList<BusinessData>();
        customerDataArrayList = new ArrayList<CustomerData>();
        mListView = (ListView)v.findViewById(R.id.listView);

        return v;
    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        businessPathRef = databaseReference.child(Const.RequestEstimatePath).child(Const.BusinessPath);
        customerPathRef = databaseReference.child(Const.RequestEstimatePath).child(Const.CustomerPath);
        businessUserPathRef = databaseReference.child(Const.BusinessPath);
        customerUserPathRef = databaseReference.child(Const.CustomerPath);
        user = FirebaseAuth.getInstance().getCurrentUser();


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        flags = sp.getString(Const.FlagKEY, "");

        okListButton.setChecked(true);


        Uid = user.getUid();
        if (flags.equals("customer")){
            okListButton.setText("相談");
            yetListButton.setText("見積り申請中");

            businessDataArrayList.clear();
            customerPathRef.child(Const.CustomerAcceptPath).child(Uid).addChildEventListener(cEventListener);
            customerUserPathRef.addChildEventListener(bEventListener);
        }else if (flags.equals("business")){
            okListButton.setText("商談");
            yetListButton.setText("見積り申請");

            customerDataArrayList.clear();
            businessPathRef.child(Const.BusinessAcceptPath).child(Uid).addChildEventListener(bEventListener);
            businessUserPathRef.addChildEventListener(cEventListener);
        }





        listRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton radioButton = (RadioButton) view.findViewById(checkedId);
                String uid = user.getUid();
                bAdapter.setBusinessDataArrayList(nothingBusinessDataArrayList);
                mListView.setAdapter(bAdapter);
                bAdapter.notifyDataSetChanged();
                if (okListButton.isChecked() == true){
                    if (flags.equals("customer")){
                        businessDataArrayList.clear();
                        customerPathRef.child(Const.CustomerAcceptPath).child(uid).addChildEventListener(cEventListener);

                    }else if (flags.equals("business")){
                        businessDataArrayList.clear();

                        businessPathRef.child(Const.BusinessAcceptPath).child(uid).addChildEventListener(bEventListener);


                    }
                }else{
                    if (flags.equals("customer")){
                        customerDataArrayList.clear();
                        customerPathRef.child(Const.CustomerRequestPath).child(uid).addChildEventListener(cEventListener);

                        arFlag ="request";
                    }else if (flags.equals("business")){


                        businessDataArrayList.clear();
                        businessPathRef.child(Const.BusinessRequestPath).child(uid).addChildEventListener(bEventListener);

                        arFlag ="request";

                    }
                }
            }
        });


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (flags.equals("customer")){
                    if(businessDataArrayList.size() != 0){
                        Bundle bundle = new Bundle();
                        bundle.putString("Uid", businessDataArrayList.get(position).getUid());
                        if (arFlag!=null){
                            bundle.putString("arFlag",arFlag);
                        }
                        bundle.putString("myUid",myUid);
                        bundle.putString("myName",myName);
                        bundle.putString("myPostalCode",myPostalCode);
                        bundle.putString("myAgeBuild",myAgeBuild);
                        bundle.putString("myForm",myForm);
                        bundle.putString("myOtherForm",myOtherForm);
                        bundle.putString("myPro",myPro);
                        bundle.putString("myPlace",myPlace);
                        bundle.putString("myOtherPlace",myOtherPlace);
                        bundle.putString("myBudget",myBudget);
                        bundle.putString("myAge",myAge);
                        bundle.putString("mySex",mySex);
                        bundle.putString("myEstimate",myEstimate);
                        bundle.putString("myRequestEstimate",myRequestEstimate);
                        bundle.putString("myRequest",myRequest);
                        bundle.putString("myFlag",myFlag);


                        BusinessAccountFragment fragmentBusinessAccount = new BusinessAccountFragment();
                        fragmentBusinessAccount.setArguments(bundle);

                        getFragmentManager().beginTransaction()
                                .replace(R.id.container,fragmentBusinessAccount,BusinessAccountFragment.TAG)
                                .commit();
                    }


                }else{
                    if (customerDataArrayList.size() != 0){
                        Bundle bundle = new Bundle();
                        bundle.putString("Uid", customerDataArrayList.get(position).getUid());
                        if (arFlag!=null){
                            bundle.putString("arFlag",arFlag);
                        }
                        bundle.putString("myUid",myUid);
                        bundle.putString("myCompanyName",myCompanyName);
                        bundle.putString("myAddress",myAddress);
                        bundle.putString("myCompanyNumber",myCompanyNumber);
                        bundle.putString("myName",myName);
                        bundle.putString("myBitmapString",myBitmapString);
                        bundle.putString("myTotalEstimate",myTotalEstimate);
                        bundle.putString("myUnwatchEstimate",myUnwatchEstimate);
                        bundle.putString("myThisPayment",myThisPayment);
                        bundle.putString("myNextPayment",myNextPayment);
                        bundle.putString("myTotalEvaluation",myTotalEvaluation);
                        bundle.putString("myMoneyEvaluation",myMoneyEvaluation);
                        bundle.putString("myIndustry",myIndustry);
                        bundle.putString("myPr",myPr);
                        bundle.putString("myFlag",myFlag);

                        CustomerAccountFragment fragmentCustomerAccount = new CustomerAccountFragment();
                        fragmentCustomerAccount.setArguments(bundle);

                        getFragmentManager().beginTransaction()
                                .replace(R.id.container,fragmentCustomerAccount,BusinessAccountFragment.TAG)
                                .commit();
                    }
                }
            }
        });
    }
}
