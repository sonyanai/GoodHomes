package jp.techacademy.son.goodhomes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by taiso on 2018/04/03.
 */

public class CustomerAccountFragment extends Fragment {
    public static final String TAG = "CustomerAccountFragment";


    TextView nameTextView;
    TextView postalCodeTextView;
    TextView ageBuildTextView;
    TextView formTextView;
    TextView otherFormTextView;
    TextView propertyTextView;
    TextView otherPropertyTextView;
    TextView placeTextView;
    TextView otherPlaceTextView;
    TextView budgetTextView;
    TextView ageTextView;
    TextView sexTextView;
    TextView estimateTextView;
    TextView requestEstimateTextView;
    TextView requestTextView;
    TextView flagTextView;
    String Uid;
    Button customerChangeButton;
    Button acceptButton;
    DatabaseReference databaseReference;
    DatabaseReference businessAcceptPathRef;
    DatabaseReference customerAcceptPathRef;
    DatabaseReference businessRequestPathRef;
    DatabaseReference customerRequestPathRef;
    DatabaseReference customerUserPathRef;
    DatabaseReference businessUserPathRef;
    String removeKey1;
    String removeKey2;
    private FirebaseUser user;
    ArrayList<BusinessData> businessDataArrayList;
    String TotalEstimate;
    String UnwatchEstimate;
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
    String myName;
    String myFlag;



    ChildEventListener bEventListener = new ChildEventListener() {
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
            final String industry = (String)map.get("Industry");
            final String pr = (String) map.get("Pr");
            final String flag = (String) map.get("Flag");

            BusinessData post = new BusinessData(mUid, companyName,address,companyNumber,name,bitmapString,totalEstimate,unwatchEstimate,thisPayment,nextPayment,totalEvaluation,moneyEvaluation,industry,pr,flag);
            businessDataArrayList.add(post);

            if (post.getUid().equals(user.getUid())){
                TotalEstimate = totalEstimate;
                UnwatchEstimate = unwatchEstimate;
            }


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




    ChildEventListener mEventListener = new ChildEventListener() {
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

            if (post.getUid().equals(Uid)){
                nameTextView.setText(name);
                postalCodeTextView.setText(postalCode);
                ageBuildTextView.setText(ageBuild);
                formTextView.setText(form);
                otherFormTextView.setText(otherForm);
                propertyTextView.setText(pro);
                otherPropertyTextView.setText(otherPro);
                placeTextView.setText(place);
                otherPlaceTextView.setText(otherPlace);
                budgetTextView.setText(budget);
                ageTextView.setText(age);
                sexTextView.setText(sex);
                estimateTextView.setText(estimate);
                requestEstimateTextView.setText(requestEstimate);
                requestTextView.setText(request);
                flagTextView.setText(flag);
                if (post.getUid().equals(user.getUid())){
                    acceptButton.setVisibility(View.GONE);
                }
            }


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
        View v = inflater.inflate(R.layout.fragment_customeraccount,container,false);

        nameTextView = (TextView)v.findViewById(R.id.nameTextView);
        postalCodeTextView = (TextView)v.findViewById(R.id.postalCodeTextView);
        ageBuildTextView = (TextView)v.findViewById(R.id.ageBuildTextView);
        formTextView = (TextView)v.findViewById(R.id.formTextView);
        otherFormTextView = (TextView)v.findViewById(R.id.otherFormTextView);
        propertyTextView = (TextView)v.findViewById(R.id.propertyTextView);
        otherPropertyTextView = (TextView)v.findViewById(R.id.otherPropertyTextView);
        placeTextView = (TextView)v.findViewById(R.id.placeTextView);
        otherPlaceTextView = (TextView)v.findViewById(R.id.otherPlaceTextView);
        budgetTextView = (TextView)v.findViewById(R.id.budgetTextView);
        ageTextView = (TextView)v.findViewById(R.id.ageTextView);
        sexTextView = (TextView)v.findViewById(R.id.sexTextView);
        estimateTextView = (TextView)v.findViewById(R.id.estimateTextView);
        requestEstimateTextView = (TextView)v.findViewById(R.id.requestEstimateTextView);
        requestTextView = (TextView)v.findViewById(R.id.requestTextView);
        flagTextView = (TextView)v.findViewById(R.id.flagTextView);
        customerChangeButton = (Button)v.findViewById(R.id.customerChangeButton);
        acceptButton = (Button)v.findViewById(R.id.acceptButton);
        businessDataArrayList = new ArrayList<BusinessData>();
        return v;
    }

    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //notificationfragmentから開いたとき
        Bundle bundle = getArguments();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (bundle!=null){
            Uid = bundle.getString("Uid");
            String arFlag = bundle.getString("arFlag");
            myCompanyName = bundle.getString("myCompanyName");
            myAddress = bundle.getString("myAddress");
            myCompanyNumber = bundle.getString("myCompanyNumber");
            myName = bundle.getString("myName");
            myBitmapString = bundle.getString("myBitmapString");
            myTotalEstimate = bundle.getString("myTotalEstimate");
            myUnwatchEstimate = bundle.getString("myUnwatchEstimate");
            myThisPayment = bundle.getString("myThisPayment");
            myNextPayment = bundle.getString("myNextPayment");
            myTotalEvaluation = bundle.getString("myTotalEvaluation");
            myMoneyEvaluation = bundle.getString("myMoneyEvaluation");
            myIndustry = bundle.getString("myIndustry");
            myPr = bundle.getString("myPr");
            myFlag = bundle.getString("myFlag");


            if (arFlag!=null){
                if (!(arFlag.equals("request"))){
                    acceptButton.setVisibility(View.GONE);
                }
            }
            customerChangeButton.setVisibility(View.GONE);
        }else {
            Uid = user.getUid();
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();
        businessAcceptPathRef = databaseReference.child(Const.RequestEstimatePath).child(Const.BusinessPath).child(Const.BusinessAcceptPath);
        customerAcceptPathRef = databaseReference.child(Const.RequestEstimatePath).child(Const.CustomerPath).child(Const.CustomerAcceptPath);
        businessRequestPathRef = databaseReference.child(Const.RequestEstimatePath).child(Const.BusinessPath).child(Const.BusinessRequestPath);
        customerRequestPathRef = databaseReference.child(Const.RequestEstimatePath).child(Const.CustomerPath).child(Const.CustomerRequestPath);
        customerUserPathRef = databaseReference.child(Const.CustomerPath);
        customerUserPathRef.addChildEventListener(mEventListener);
        businessUserPathRef = databaseReference.child(Const.BusinessPath);
        businessUserPathRef.addChildEventListener(bEventListener);



        view.findViewById(R.id.customerChangeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CustomerLoginFragment fragmentCustomerLogin = new CustomerLoginFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.container,fragmentCustomerLogin,CustomerLoginFragment.TAG)
                        .commit();

            }
        });
        view.findViewById(R.id.acceptButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                acceptButton.setVisibility(View.GONE);


                String uid = user.getUid();

                //remove
                businessRequestPathRef.child(uid).child(Uid).setValue(null);
                customerRequestPathRef.child(Uid).child(uid).setValue(null);

                //カウントの処理
                int newUnwatchEstimate = Integer.parseInt(UnwatchEstimate);
                newUnwatchEstimate -= 1;
                String newUnwatchEstimates = String.valueOf(newUnwatchEstimate);
                int newTotalEstimate = Integer.parseInt(TotalEstimate);
                newTotalEstimate += 1;
                String newTotalEstimates = String.valueOf(newTotalEstimate);
                int newRequestEstimate = Integer.parseInt(requestEstimateTextView.getText().toString());
                newRequestEstimate -= 1;
                String newRequestEstimates = String.valueOf(newRequestEstimate);
                int newEstimate = Integer.parseInt(estimateTextView.getText().toString());
                newEstimate += 1;
                String newEstimates = String.valueOf(newEstimate);

                businessUserPathRef.child(uid).child("UnwatchEstimate").setValue(newUnwatchEstimates);
                businessUserPathRef.child(uid).child("TotalEstimate").setValue(newTotalEstimates);
                customerUserPathRef.child(Uid).child("requestEstimate").setValue(newRequestEstimates);
                customerUserPathRef.child(Uid).child("estimate").setValue(newEstimates);


                //add
                Map<String, String> data1 = new HashMap<String, String>();

                String mUid = user.getUid();

                data1.put("mUid", mUid);
                data1.put("CompanyName", myCompanyName);
                data1.put("Address",myAddress);
                data1.put("CompanyNumber",myCompanyNumber);
                data1.put("name",myName);
                data1.put("BitmapString",myBitmapString);
                data1.put("TotalEstimate",myTotalEstimate);
                data1.put("UnwatchEstimate",myUnwatchEstimate);
                data1.put("ThisPayment",myThisPayment);
                data1.put("NextPayment",myNextPayment);
                data1.put("TotalEvaluation",myTotalEvaluation);
                data1.put("MoneyEvaluation",myMoneyEvaluation);
                data1.put("Industry",myIndustry);
                data1.put("Pr",myPr);
                data1.put("flag",myFlag);
                //Uidは開いてるアカウントの会社のやつ
                //mUidは開いてる人のやつ

                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put(Uid, data1);

                customerAcceptPathRef.child(Uid).updateChildren(childUpdates);




                Map<String, String> data2 = new HashMap<String, String>();

                data2.put("mUid", Uid);
                data2.put("UserName", nameTextView.getText().toString());
                data2.put("postalCode", postalCodeTextView.getText().toString());
                data2.put("ageBuild", ageBuildTextView.getText().toString());
                data2.put("form", formTextView.getText().toString());
                data2.put("otherForm", otherFormTextView.getText().toString());
                data2.put("pro", propertyTextView.getText().toString());
                data2.put("otherPro", otherPropertyTextView.getText().toString());
                data2.put("place", placeTextView.getText().toString());
                data2.put("otherPlace", otherPlaceTextView.getText().toString());
                data2.put("budget", budgetTextView.getText().toString());
                data2.put("age", ageTextView.getText().toString());
                data2.put("sex", sexTextView.getText().toString());
                data2.put("estimate", estimateTextView.getText().toString());
                data2.put("requestEstimate", requestEstimateTextView.getText().toString());
                data2.put("request", requestTextView.getText().toString());
                data2.put("flag", flagTextView.getText().toString());

                Map<String, Object> childUpdate = new HashMap<>();
                childUpdate.put(mUid, data2);

                businessAcceptPathRef.child(mUid).updateChildren(childUpdate);


            }
        });


    }

}
