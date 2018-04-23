package jp.techacademy.son.goodhomes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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

public class ListFragment extends Fragment {
    public static final String TAG = "ListFragment";

    ListView mListView;
    DatabaseReference databaseReference;
    DatabaseReference customerUserPathRef;
    DatabaseReference userPathRef;
    public ArrayList<BusinessData> businessDataArrayList;
    private BusinessDataArrayListAdapter mAdapter;
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
    FirebaseUser user;
    String mUid;



    ChildEventListener cEventListener = new ChildEventListener() {
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


            if (post.getUid().equals(mUid)){
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


            mAdapter.setBusinessDataArrayList(businessDataArrayList);
            mListView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();


//            if (gyousyu.equals("reform")){
//                if(post.getGyousyu.equals("reform")){
//                    list.add(post);
//                }
//            }else if (gyousyu.equals("kaitai")){
//                if (post.getGyousyu.equals("kaitai")){
//                    list.add(post);
//                }
//            }else if ()
//

            //ListViewについか


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
        View v = inflater.inflate(R.layout.fragment_list,container,false);

        mListView = (ListView)v.findViewById(R.id.listView);
        businessDataArrayList = new ArrayList<BusinessData>();
        mAdapter = new BusinessDataArrayListAdapter(this.getActivity(), R.layout.new_list);

        return v;
    }

    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            mUid = user.getUid();
        }


        Bundle bundle = getArguments();
        if (bundle!=null){
            String flag = bundle.getString("flag");//searchfragmentからきた
        }


        databaseReference = FirebaseDatabase.getInstance().getReference();
        userPathRef = databaseReference.child(Const.BusinessPath);
        customerUserPathRef = databaseReference.child(Const.CustomerPath);

        customerUserPathRef.addChildEventListener(cEventListener);





        userPathRef.addChildEventListener(bEventListener);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bundle = new Bundle();
                bundle.putString("Uid", businessDataArrayList.get(position).getUid());
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
        });
    }
}
