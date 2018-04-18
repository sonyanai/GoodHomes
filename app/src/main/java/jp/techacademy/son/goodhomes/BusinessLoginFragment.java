package jp.techacademy.son.goodhomes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class BusinessLoginFragment extends Fragment {
    public static final String TAG = "BusinessLoginFragment";

    EditText companyNameEditText;
    EditText addressEditText;
    EditText companyNumberEditText;
    EditText userNameEditText;
    EditText prEditText;
    ImageView companyImageView;
    Button okButton;
    FirebaseUser user;
    DatabaseReference databaseReference;
    DatabaseReference businessPathRef;
    String Uid;
    TextView totalEstimateTextView;
    TextView unwatchEstimateTextView;
    TextView thisPaymentTextView;
    TextView nextPaymentTextView;
    TextView totalEvaluationTextView;
    TextView moneyEvaluationTextView;
    TextView industryTextView;
    String flag;




    ChildEventListener mEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(final DataSnapshot dataSnapshot, String s) {
            HashMap map = (HashMap) dataSnapshot.getValue();
            final String mUids = (String) map.get("mUid");
            final String companyNames = (String) map.get("CompanyName");
            final String addresss = (String) map.get("Address");
            final String companyNumbers = (String) map.get("CompanyNumber");
            final String names = (String) map.get("name");
            final String bitmapStrings = (String) map.get("BitmapString");
            final String totalEstimate = (String) map.get("TotalEstimate");
            final String unwatchEstimate = (String) map.get("UnwatchEstimate");
            final String thisPayment = (String) map.get("ThisPayment");
            final String nextPayment = (String) map.get("NextPayment");
            final String totalEvaluation = (String) map.get("TotalEvaluation");
            final String moneyEvaluation = (String) map.get("MoneyEvaluation");
            final String industry = (String)map.get("Industry");
            final String pr = (String) map.get("Pr");

            BusinessData post = new BusinessData(mUids, companyNames,addresss,companyNumbers,names,bitmapStrings,totalEstimate,unwatchEstimate,thisPayment,nextPayment,totalEvaluation,moneyEvaluation,industry,pr);

            if (post.getUid().equals(Uid)){
                companyNameEditText.setText(post.getCompanyName());
                addressEditText.setText(post.getAddress());
                companyNumberEditText.setText(post.getCompanyNumber());
                userNameEditText.setText(post.getName());
                prEditText.setText(post.getPr());
                totalEstimateTextView.setText(post.getTotalEstimate());
                unwatchEstimateTextView.setText(post.getUnwatchEstimate());
                thisPaymentTextView.setText(post.getThisPayment());
                nextPaymentTextView.setText(post.getNextPayment());
                totalEvaluationTextView.setText(post.getTotalEvaluation());
                moneyEvaluationTextView.setText(post.getMoneyEvaluation());
                industryTextView.setText(post.getIndustry());

                //ImageView.set
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
        View v = inflater.inflate(R.layout.fragment_businesslogin,container,false);

        companyNameEditText = (EditText)v.findViewById(R.id.companyNameEditText);
        addressEditText = (EditText)v.findViewById(R.id.addressEditText);
        companyNumberEditText = (EditText)v.findViewById(R.id.companyNumberEditText);
        prEditText = (EditText)v.findViewById(R.id.prEditText);
        userNameEditText = (EditText)v.findViewById(R.id.userNameEditText);
        companyImageView = (ImageView)v.findViewById(R.id.companyImageView);
        totalEstimateTextView =(TextView)v.findViewById(R.id.totalEstimateTextView);
        unwatchEstimateTextView = (TextView)v.findViewById(R.id.unwatchEstimateTextView);
        thisPaymentTextView = (TextView)v.findViewById(R.id.thisPaymentTextView);
        nextPaymentTextView = (TextView)v.findViewById(R.id.nextPaymentTextView);
        totalEvaluationTextView = (TextView)v.findViewById(R.id.totalEvaluationTextView);
        moneyEvaluationTextView = (TextView)v.findViewById(R.id.moneyEvaluationTextView);
        industryTextView = (TextView)v.findViewById(R.id.industryTextView);


        okButton = (Button)v.findViewById(R.id.okButton);


        return v;
    }

    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        businessPathRef = databaseReference.child(Const.BusinessPath);
        Uid = user.getUid();
        businessPathRef.addChildEventListener(mEventListener);






        view.findViewById(R.id.okButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String companyName = companyNameEditText.getText().toString();
                String address = addressEditText.getText().toString();
                String companyNumber = companyNumberEditText.getText().toString();
                String UserName = userNameEditText.getText().toString();
                String pr = prEditText.getText().toString();

                String bitmapString="";
                String totalEstimate = totalEstimateTextView.getText().toString();
                String unwatchEstimate = unwatchEstimateTextView.getText().toString();
                String thisPayment = thisPaymentTextView.getText().toString();
                String nextPayment = nextPaymentTextView.getText().toString();
                String moneyEvaluation = moneyEvaluationTextView.getText().toString();
                String totalEvaluation = totalEvaluationTextView.getText().toString();
                String industry = industryTextView.getText().toString();



                Map<String, String> data = new HashMap<String, String>();

                data.put("mUid", Uid);
                data.put("CompanyName", companyName);
                data.put("Address", address);
                data.put("CompanyNumber", companyNumber);
                data.put("name", UserName);
                data.put("BitmapString", bitmapString);

                data.put("TotalEstimate", totalEstimate);
                data.put("UnwatchEstimate", unwatchEstimate);
                data.put("ThisPayment", thisPayment);
                data.put("NextPayment", nextPayment);
                data.put("TotalEvaluation", totalEvaluation);
                data.put("MoneyEvaluation", moneyEvaluation);
                data.put("Industry",industry);

                data.put("Pr",pr);
                data.put("flag", flag);

                businessPathRef.child(Uid).setValue(data);
                saveBusinessData(Uid,companyName,address,companyNumber,UserName,bitmapString,totalEstimate,unwatchEstimate,thisPayment,nextPayment,totalEvaluation,moneyEvaluation,pr,flag);

                BusinessAccountFragment fragmentBusinessAccount = new BusinessAccountFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.container,fragmentBusinessAccount,BusinessAccountFragment.TAG)
                        .commit();

            }
        });
    }

    private void saveBusinessData(String Uid,String companyName,String address,String companyNumber,String UserName,String bitmapString,String totalEstimate,String unwatchEstimate,String thisPayment,String nextPayment,String totalEvaluation,String moneyEvaluation,String pr,String flag) {
        // Preferenceに保存する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Const.mUidKEY, Uid);
        editor.putString(Const.CompanyNameKEY, companyName);
        editor.putString(Const.AddressKEY, address);
        editor.putString(Const.CompanyNumberKEY, companyNumber);
        editor.putString(Const.NameKEY, UserName);
        editor.putString(Const.BitmapStringKEY, bitmapString);
        editor.putString(Const.TotalEstimateKEY, totalEstimate);
        editor.putString(Const.UnwatchEstimateKEY, unwatchEstimate);
        editor.putString(Const.ThisPaymentKEY, thisPayment);
        editor.putString(Const.NextPaymentKEY, nextPayment);
        editor.putString(Const.TotalEvaluationKEY, totalEvaluation);
        editor.putString(Const.MoneyEvaluationKEY, moneyEvaluation);
        editor.putString(Const.PrKEY, pr);
        editor.putString(Const.FlagKEY, flag);

        editor.commit();
    }

}
