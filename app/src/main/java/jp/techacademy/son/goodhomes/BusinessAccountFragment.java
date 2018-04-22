package jp.techacademy.son.goodhomes;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by taiso on 2018/04/03.
 */

public class BusinessAccountFragment extends Fragment {
    public static final String TAG = "BusinessAccountFragment";

    TextView companyNameTextView;
    TextView addressTextView;
    TextView companyNumberTextView;
    TextView nameTextView;
    TextView totalEstimateTextView;
    TextView unwatchEstimateTextView;
    TextView thisPaymentTextView;
    TextView nextPaymentTextView;
    TextView unwatchEstimateText;
    TextView thisPaymentText;
    TextView nextPaymentText;
    TextView prTextView;
    TextView totalEvaluationTextView;
    TextView moneyEvaluationTextView;
    TextView industryTextView;
    Button businessChangeButton;
    Button estimateButton;
    ImageView companyImageView;
    String Uid;
    String openName;
    String openedCompanyName;
    String openedIndustry;
    String requestEstimate;
    DatabaseReference databaseReference;
    DatabaseReference businessUserPathRef;
    DatabaseReference customerUserPathRef;
    DatabaseReference businessRequestPathRef;
    DatabaseReference customerRequestPathRef;
    private FirebaseUser user;
    String place;
    TextView flagTextView;
    String openedBitmapString = "0";
    public ArrayList<BusinessListData> businessDataArrayList;


    ChildEventListener mEventListener = new ChildEventListener() {
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

            if (post.getUid().equals(Uid)){
                nameTextView.setText(post.getName());
                companyNameTextView.setText(post.getCompanyName());
                addressTextView.setText(post.getAddress());
                companyNumberTextView.setText(post.getCompanyNumber());
                totalEstimateTextView.setText(post.getTotalEstimate());
                unwatchEstimateTextView.setText(post.getUnwatchEstimate());
                thisPaymentTextView.setText(post.getThisPayment());
                nextPaymentTextView.setText(post.getNextPayment());
                totalEvaluationTextView.setText(post.getTotalEvaluation());
                moneyEvaluationTextView.setText(post.getMoneyEvaluation());
                industryTextView.setText(post.getIndustry());
                prTextView.setText(post.getPr());
                openedCompanyName = post.getCompanyName();
                openedBitmapString = post.getBitmapString();
                openedIndustry = post.getIndustry();
                flagTextView.setText(post.getFlag());
                Uid = mUid;

                if (post.getBitmapString() != null){
                    if ((post.getBitmapString().length()>10)) {
                        byte[] bytes = Base64.decode(bitmapString,Base64.DEFAULT);
                        if(bytes.length != 0){
                            Bitmap image = BitmapFactory.decodeByteArray(bytes,0,bytes.length).copy(Bitmap.Config.ARGB_8888,true);
                            companyImageView.setImageBitmap(image);
                        }
                    }
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
            businessDataArrayList.add(post);
            if (post.getUid().equals(Uid)){
                estimateButton.setVisibility(View.GONE);
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
        View v = inflater.inflate(R.layout.fragment_businessaccount,container,false);

        companyImageView = (ImageView)v.findViewById(R.id.companyImageView);
        estimateButton = (Button)v.findViewById(R.id.estimateButton);
        companyNameTextView = (TextView)v.findViewById(R.id.companyNameTextView);
        addressTextView = (TextView)v.findViewById(R.id.addressTextView);
        companyNumberTextView = (TextView)v.findViewById(R.id.companyNumberTextView);
        nameTextView = (TextView)v.findViewById(R.id.nameTextView);
        totalEstimateTextView = (TextView)v.findViewById(R.id.totalEstimateTextView);
        unwatchEstimateTextView = (TextView)v.findViewById(R.id.unwatchEstimateTextView);
        thisPaymentTextView = (TextView)v.findViewById(R.id.thisPaymentTextView);
        nextPaymentTextView = (TextView)v.findViewById(R.id.nextPaymentTextView);
        unwatchEstimateText = (TextView)v.findViewById(R.id.unwatchEstimateText);
        thisPaymentText = (TextView)v.findViewById(R.id.thisPaymentText);
        nextPaymentText = (TextView)v.findViewById(R.id.nextPaymentText);
        totalEvaluationTextView = (TextView)v.findViewById(R.id.totalEvaluationTextView);
        moneyEvaluationTextView = (TextView)v.findViewById(R.id.moneyEvaluationTextView);
        industryTextView = (TextView)v.findViewById(R.id.industryTextView);
        prTextView = (TextView)v.findViewById(R.id.prTextView);
        businessChangeButton = (Button)v.findViewById(R.id.businessChangeButton);
        flagTextView = (TextView)v.findViewById(R.id.flagTextView);
        businessDataArrayList = new ArrayList<BusinessListData>();

        return v;
    }

    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        businessRequestPathRef = databaseReference.child(Const.RequestEstimatePath).child(Const.BusinessPath).child(Const.BusinessRequestPath);
        customerRequestPathRef = databaseReference.child(Const.RequestEstimatePath).child(Const.CustomerPath).child(Const.CustomerRequestPath);
        businessUserPathRef = databaseReference.child(Const.BusinessPath);
        customerUserPathRef = databaseReference.child(Const.CustomerPath);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String flag = sp.getString(Const.FlagKEY, "");
        openName = sp.getString(Const.NameKEY, "");
        place = sp.getString(Const.PlaceKEY,"");
        requestEstimate = sp.getString(Const.RequestEstimateKEY,"");



        //notificationから開いたとき
        Bundle bundle = getArguments();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (bundle!=null){
            Uid = bundle.getString("Uid");
            String arFlag = bundle.getString("arFlag");
            if (arFlag!=null){
                if (arFlag.equals("request")){
                    estimateButton.setVisibility(View.GONE);
                }
            }
            String mUid = user.getUid();
            if (!(Uid.equals(mUid))){
                businessChangeButton.setVisibility(View.GONE);
                unwatchEstimateTextView.setVisibility(View.GONE);
                thisPaymentTextView.setVisibility(View.GONE);
                nextPaymentTextView.setVisibility(View.GONE);
                unwatchEstimateText.setVisibility(View.GONE);
                thisPaymentText.setVisibility(View.GONE);
                nextPaymentText.setVisibility(View.GONE);
            }
        }else {
            Uid = user.getUid();
            estimateButton.setVisibility(View.GONE);
        }
        if (flag.equals("business")){
            estimateButton.setVisibility(View.GONE);
        }

        businessUserPathRef.addChildEventListener(mEventListener);

        businessDataArrayList.clear();
        String oid = user.getUid();
        customerRequestPathRef.child(oid).addChildEventListener(cEventListener);



        view.findViewById(R.id.businessChangeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BusinessLoginFragment fragmentBusinessLogin = new BusinessLoginFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.container,fragmentBusinessLogin,BusinessLoginFragment.TAG)
                        .commit();

            }
        });

        view.findViewById(R.id.estimateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estimateButton.setVisibility(View.GONE);
                if (businessDataArrayList.size()==0){

                    String mUid = user.getUid();


                    //会社の情報
                    Map<String, String> data1 = new HashMap<String, String>();

                    String key1 = customerRequestPathRef.child(mUid).push().getKey();

                    data1.put("mUid", Uid);
                    data1.put("companyName", openedCompanyName);
                    data1.put("bitmapString",openedBitmapString);
                    data1.put("industry",openedIndustry);
                    data1.put("key1",key1);
                    //Uidは開いてるアカウントの会社のやつ
                    //mUidは開いてる人のやつ

                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put(key1, data1);

                    customerRequestPathRef.child(mUid).updateChildren(childUpdates);


//客の情報
                    Map<String, String> data2 = new HashMap<String, String>();
                    String key2 = businessRequestPathRef.child(Uid).push().getKey();

                    data2.put("mUid", mUid);
                    data2.put("name", openName);
                    data2.put("place",place);
                    data2.put("key1",key1);
                    data2.put("key2",key2);

                    Map<String, Object> childUpdate = new HashMap<>();
                    childUpdate.put(key2, data2);

                    businessRequestPathRef.child(Uid).updateChildren(childUpdate);


                    customerRequestPathRef.child(mUid).addChildEventListener(cEventListener);




                    int newUnwatchEstimate = Integer.parseInt(unwatchEstimateTextView.getText().toString());
                    newUnwatchEstimate += 1;
                    String newUnwatchEstimates = String.valueOf(newUnwatchEstimate);
                    int newRequestEstimate = Integer.parseInt(requestEstimate);
                    newRequestEstimate += 1;
                    String newRequestEstimates = String.valueOf(newRequestEstimate);

                    businessUserPathRef.child(Uid).child("UnwatchEstimate").setValue(newUnwatchEstimates);
                    customerUserPathRef.child(mUid).child("requestEstimate").setValue(newRequestEstimates);


                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(Const.UnwatchEstimateKEY, newUnwatchEstimates);
                    editor.commit();











                }

                for (BusinessListData aaa : businessDataArrayList){
                    if (aaa.getUid().equals(Uid)) {
                        estimateButton.setVisibility(View.GONE);
                    }else{



                        //会社の情報
                        String mUid = user.getUid();
                        Map<String, String> data1 = new HashMap<String, String>();
                        String key1 = customerRequestPathRef.child(mUid).push().getKey();

                        data1.put("mUid", Uid);
                        data1.put("companyName", openedCompanyName);
                        data1.put("bitmapString",openedBitmapString);
                        data1.put("industry",openedIndustry);
                        data1.put("key1",key1);
                        //Uidは開いてるアカウントの会社のやつ
                        //mUidは開いてる人のやつ

                        Map<String, Object> childUpdates = new HashMap<>();
                        childUpdates.put(key1, data1);

                        customerRequestPathRef.child(mUid).updateChildren(childUpdates);
//客の情報

                        Map<String, String> data2 = new HashMap<String, String>();
                        String key2 = businessRequestPathRef.child(Uid).push().getKey();

                        data2.put("mUid", mUid);
                        data2.put("name", openName);
                        data2.put("place",place);
                        data1.put("key1",key1);
                        data2.put("key2",key2);

                        Map<String, Object> childUpdate = new HashMap<>();
                        childUpdate.put(key2, data2);

                        businessRequestPathRef.child(Uid).updateChildren(childUpdate);

/*
                        int newUnwatchEstimate = Integer.parseInt(unwatchEstimateTextView.getText().toString());
                        newUnwatchEstimate += 1;
                        String newUnwatchEstimates = String.valueOf(newUnwatchEstimate);
                        int newRequestEstimate = Integer.parseInt(requestEstimate);
                        newRequestEstimate += 1;
                        String newRequestEstimates = String.valueOf(newRequestEstimate);

*/

                    }
                }

                businessDataArrayList.clear();

            }
        });


    }

}
