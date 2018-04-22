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
    String Uid;
    Button customerChangeButton;
    Button acceptButton;
    DatabaseReference databaseReference;
    DatabaseReference businessAcceptPathRef;
    DatabaseReference customerAcceptPathRef;
    DatabaseReference businessRequestPathRef;
    DatabaseReference customerRequestPathRef;
    DatabaseReference userPathRef;
    String removeKey1;
    String removeKey2;
    private FirebaseUser user;




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

            CustomerData post = new CustomerData(mUid, name, postalCode,ageBuild,form,otherForm,pro,otherPro,place,otherPlace,budget,age,sex,estimate,requestEstimate,request);

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
        customerChangeButton = (Button)v.findViewById(R.id.customerChangeButton);
        acceptButton = (Button)v.findViewById(R.id.acceptButton);
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
            removeKey1 = bundle.getString("key1");
            removeKey2 = bundle.getString("key2");
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
        userPathRef = databaseReference.child(Const.CustomerPath);
        userPathRef.addChildEventListener(mEventListener);







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
                businessRequestPathRef.child(uid).child(removeKey2).setValue(null);
                customerRequestPathRef.child(Uid).child(removeKey1).setValue(null);

                //カウントの処理
/*
                int newUnwatchEstimate = Integer.parseInt(unwatchEstimateTextView.getText().toString());
                newUnwatchEstimate += 1;
                String newUnwatchEstimates = String.valueOf(newUnwatchEstimate);
                int newRequestEstimate = Integer.parseInt(requestEstimate);
                newRequestEstimate += 1;
                String newRequestEstimates = String.valueOf(newRequestEstimate);
*/

                //add
                Map<String, String> data1 = new HashMap<String, String>();

                String mUid = user.getUid();
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
                String openedBitmapString = sp.getString(Const.BitmapStringKEY, "");
                String openedCompanyName = sp.getString(Const.CompanyNameKEY, "");
                String openedIndustry = sp.getString(Const.IndustryKEY,"");

                String key1 = businessAcceptPathRef.child(Uid).push().getKey();

                data1.put("mUid", mUid);
                data1.put("companyName", openedCompanyName);
                data1.put("bitmapString",openedBitmapString);
                data1.put("industry",openedIndustry);
                data1.put("key",key1);
                //Uidは開いてるアカウントの会社のやつ
                //mUidは開いてる人のやつ

                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put(key1, data1);

                customerAcceptPathRef.child(Uid).updateChildren(childUpdates);




                Map<String, String> data2 = new HashMap<String, String>();
                String key2 = customerAcceptPathRef.child(mUid).push().getKey();

                data2.put("mUid", Uid);
                data2.put("name", nameTextView.getText().toString());
                data2.put("place",placeTextView.getText().toString());
                data2.put("key",key2);

                Map<String, Object> childUpdate = new HashMap<>();
                childUpdate.put(key2, data2);

                businessAcceptPathRef.child(mUid).updateChildren(childUpdate);


            }
        });


    }

}
