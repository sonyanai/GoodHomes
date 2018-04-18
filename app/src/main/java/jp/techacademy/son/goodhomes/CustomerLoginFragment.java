package jp.techacademy.son.goodhomes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

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

public class CustomerLoginFragment extends Fragment {
    public static final String TAG = "CustomerLoginFragment";

    EditText PostalCodeEditText;
    EditText ageBuildEditText;
    Spinner propertySpinner;
    RadioGroup typesRadioGroup;
    RadioButton detachedRadioButton;
    RadioButton complexRadioButton;
    RadioButton storeRadioButton;
    RadioButton otherRadioButton;
    EditText formEditText;
    EditText typesEditText;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    CheckBox checkBox5;
    CheckBox checkBox6;
    CheckBox checkBox7;
    CheckBox checkBox8;
    CheckBox checkBox9;
    CheckBox checkBox10;
    CheckBox checkBox11;
    CheckBox checkBox12;
    CheckBox checkBox13;
    CheckBox checkBox14;
    CheckBox checkBox15;
    CheckBox checkBox16;
    CheckBox checkBox17;


    EditText otherReformEditText;
    Spinner budgedSpinner;
    Spinner ageSpinner;
    RadioGroup radioGroup;
    RadioButton manRadioButton;
    RadioButton womanRadioButton;
    RadioButton otherSexRadioButton;
    EditText requestEditText;
    Button accountButton;
    String form = "";
    DatabaseReference databaseReference;
    DatabaseReference customerPathRef;
    String sex;
    FirebaseUser user;
    String Uid;
    String check1="";
    String check2="";
    String check3="";
    String check4="";
    String check5="";
    String check6="";
    String check7="";
    String check8="";
    String check9="";
    String check10="";
    String check11="";
    String check12="";
    String check13="";
    String check14="";
    String check15="";
    String check16="";
    String check17="";




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
            final String request = (String) map.get("request");

            CustomerData post = new CustomerData(mUid, name, postalCode,ageBuild,form,otherForm,pro,otherPro,place,otherPlace,budget,age,sex,estimate,request);




            if (post.getUid().equals(Uid)){





                //郵便番号
                PostalCodeEditText.setText(post.getPostalCode());
                //築年数
                ageBuildEditText.setText(post.getAgeBuild());
                //ラジオグループこんな感じ
                if (post.getForm().equals("一戸建て")){
                    detachedRadioButton.setChecked(true);
                }else if (post.getForm().equals("集合住宅")){
                    complexRadioButton.setChecked(true);
                }else if (post.getForm().equals("店舗")) {
                    storeRadioButton.setChecked(true);
                }else if (post.getForm().equals("その他")){
                    otherRadioButton.setChecked(true);
                }
                //その他の物件の種類
                formEditText.setText(post.getOtherForm());
                //構造スピナー
                if (post.getPro().equals("木造")){
                    propertySpinner.setSelection(0);
                }else if (post.getPro().equals("軽量鉄骨造(プレハブ)")){
                    propertySpinner.setSelection(1);
                }else if (post.getPro().equals("重量鉄骨造")){
                    propertySpinner.setSelection(2);
                }else if (post.getPro().equals("鉄筋コンクリート造")){
                    propertySpinner.setSelection(3);
                }else if (post.getPro().equals("ログハウス")){
                    propertySpinner.setSelection(4);
                }else if (post.getPro().equals("その他")){
                    propertySpinner.setSelection(5);
                }
                //その他の物件の構造
                typesEditText.setText(post.getOtherPro());



                //チェックボックスリフォーム箇所
                if(post.getPlace().indexOf("風呂")>-1){
                    checkBox1.setChecked(true);
                }
                if(post.getPlace().indexOf("浴室")>-1){
                    checkBox2.setChecked(true);
                }
                if(post.getPlace().indexOf("トイレ")>-1){
                    checkBox3.setChecked(true);
                }
                if(post.getPlace().indexOf("キッチン")>-1){
                    checkBox4.setChecked(true);
                }
                if(post.getPlace().indexOf("洗面所")>-1){
                    checkBox5.setChecked(true);
                }
                if(post.getPlace().indexOf("外壁")>-1){
                    checkBox6.setChecked(true);
                }
                if(post.getPlace().indexOf("屋根")>-1){
                    checkBox7.setChecked(true);
                }
                if(post.getPlace().indexOf("庭")>-1){
                    checkBox8.setChecked(true);
                }
                if(post.getPlace().indexOf("ベランダ・バルコニー")>-1){
                    checkBox9.setChecked(true);
                }
                if(post.getPlace().indexOf("リビング")>-1){
                    checkBox10.setChecked(true);
                }
                if(post.getPlace().indexOf("ダイニング")>-1){
                    checkBox11.setChecked(true);
                }
                if(post.getPlace().indexOf("洋室・和室")>-1){
                    checkBox12.setChecked(true);
                }
                if(post.getPlace().indexOf("玄関")>-1){
                    checkBox13.setChecked(true);
                }
                if(post.getPlace().indexOf("廊下")>-1){
                    checkBox14.setChecked(true);
                }
                if(post.getPlace().indexOf("階段")>-1){
                    checkBox15.setChecked(true);
                }
                if(post.getPlace().indexOf("リノベーション")>-1){
                    checkBox16.setChecked(true);
                }
                if(post.getPlace().indexOf("その他")>-1){
                    checkBox17.setChecked(true);
                }



                //その他のリフォーム箇所
                otherReformEditText.setText(post.getOtherPlace());




                //予算スピナー
                if (budget.equals("選択する")){
                    budgedSpinner.setSelection(0);
                }else if (budget.equals("～20万")){
                    budgedSpinner.setSelection(1);
                }else if (budget.equals("21万～40万")){
                    budgedSpinner.setSelection(2);
                }else if (budget.equals("41万～60万")){
                    budgedSpinner.setSelection(3);
                }else if (budget.equals("61万～80万")){
                    budgedSpinner.setSelection(4);
                }else if (budget.equals("81万～100万")){
                    budgedSpinner.setSelection(5);
                }else if (budget.equals("100万～150万")){
                    budgedSpinner.setSelection(6);
                }else if (budget.equals("150万～200万")){
                    budgedSpinner.setSelection(7);
                }else if (budget.equals("200万～")){
                    budgedSpinner.setSelection(8);
                }




                //年齢スピナー

                if (age.equals("選択する")){
                    ageSpinner.setSelection(0);
                }else if (age.equals("10代")){
                    ageSpinner.setSelection(1);
                }else if (age.equals("20代")){
                    ageSpinner.setSelection(2);
                }else if (age.equals("30代")){
                    ageSpinner.setSelection(3);
                }else if (age.equals("40代")){
                    ageSpinner.setSelection(4);
                }else if (age.equals("50代")){
                    ageSpinner.setSelection(5);
                }else if (age.equals("60代")){
                    ageSpinner.setSelection(6);
                }else if (age.equals("70代～")){
                    ageSpinner.setSelection(7);
                }


                //性別ラジオボタン
                if (post.getSex()!=null){
                    if (post.getSex().equals("男性")){
                        manRadioButton.setChecked(true);
                    }else if (post.getSex().equals("女性")){
                        womanRadioButton.setChecked(true);
                    }else {
                        otherSexRadioButton.setChecked(true);
                    }
                }

                //要求
                requestEditText.setText(post.getRequest());






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
        View v = inflater.inflate(R.layout.fragment_customerlogin,container,false);

        PostalCodeEditText = (EditText)v.findViewById(R.id.PostalCodeEditText);
        ageBuildEditText = (EditText)v.findViewById(R.id.ageBuildEditText);
        typesRadioGroup = (RadioGroup)v.findViewById(R.id.typesRadioGroup);
        detachedRadioButton = (RadioButton)v.findViewById(R.id.detachedRadioButton);
        complexRadioButton = (RadioButton)v.findViewById(R.id.complexRadioButton);
        storeRadioButton = (RadioButton)v.findViewById(R.id.storeRadioButton);
        otherRadioButton = (RadioButton)v.findViewById(R.id.otherRadioButton);
        formEditText = (EditText)v.findViewById(R.id.formEditText);
        propertySpinner = (Spinner)v.findViewById(R.id.propertySpinner);
        typesEditText = (EditText)v.findViewById(R.id.typesEditText);
        //CheckBox
        checkBox1 = (CheckBox)v.findViewById(R.id.reformCheckBox1);
        checkBox2 = (CheckBox)v.findViewById(R.id.reformCheckBox2);
        checkBox3 = (CheckBox)v.findViewById(R.id.reformCheckBox3);
        checkBox4 = (CheckBox)v.findViewById(R.id.reformCheckBox4);
        checkBox5 = (CheckBox)v.findViewById(R.id.reformCheckBox5);
        checkBox6 = (CheckBox)v.findViewById(R.id.reformCheckBox6);
        checkBox7 = (CheckBox)v.findViewById(R.id.reformCheckBox7);
        checkBox8 = (CheckBox)v.findViewById(R.id.reformCheckBox8);
        checkBox9 = (CheckBox)v.findViewById(R.id.reformCheckBox9);
        checkBox10 = (CheckBox)v.findViewById(R.id.reformCheckBox10);
        checkBox11 = (CheckBox)v.findViewById(R.id.reformCheckBox11);
        checkBox12 = (CheckBox)v.findViewById(R.id.reformCheckBox12);
        checkBox13 = (CheckBox)v.findViewById(R.id.reformCheckBox13);
        checkBox14 = (CheckBox)v.findViewById(R.id.reformCheckBox14);
        checkBox15 = (CheckBox)v.findViewById(R.id.reformCheckBox15);
        checkBox16 = (CheckBox)v.findViewById(R.id.reformCheckBox16);
        checkBox17 = (CheckBox)v.findViewById(R.id.reformCheckBox17);

        otherReformEditText = (EditText)v.findViewById(R.id.otherReformEditText);
        ageSpinner = (Spinner)v.findViewById(R.id.ageSpinner);
        budgedSpinner = (Spinner)v.findViewById(R.id.budgetSpinner);
        radioGroup = (RadioGroup)v.findViewById(R.id.radioGroup);
        manRadioButton = (RadioButton)v.findViewById(R.id.manRadioButton);
        womanRadioButton =(RadioButton)v.findViewById(R.id.womanRadioButton);
        otherSexRadioButton = (RadioButton)v.findViewById(R.id.otherSexRadioButton);

        requestEditText = (EditText)v.findViewById(R.id.requestEditText);
        accountButton = (Button)v.findViewById(R.id.accountButton);


        return v;
    }

    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        customerPathRef = databaseReference.child(Const.CustomerPath);
        user = FirebaseAuth.getInstance().getCurrentUser();
        Uid = user.getUid();
        customerPathRef.addChildEventListener(mEventListener);








        view.findViewById(R.id.accountButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(v);

                //bundleでflagみたいなのあげてそれならpreferenceから値を取得してアカウントを表示
                CustomerAccountFragment fragmentCustomerAccount = new CustomerAccountFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.container,fragmentCustomerAccount,CustomerAccountFragment.TAG)
                        .commit();

            }
        });



    }
    public void save(View v){
        //郵便番号
        String postalCode = PostalCodeEditText.getText().toString();

        //築年数
        String ageBuild = ageBuildEditText.getText().toString();

        //物件の種類
        int t = typesRadioGroup.getCheckedRadioButtonId();
        int d = detachedRadioButton.getId();
        int c = complexRadioButton.getId();
        int s = storeRadioButton.getId();
        int o = otherRadioButton.getId();
        if (t==d){
            form = "一戸建て";
        }else if (t==c){
            form = "集合住宅";
        }else if (t==s){
            form = "店舗";
        }else{
            form = "その他";
        }
        //その他の時のeditText
        String otherForm =formEditText.getText().toString();

        String pro = (String)propertySpinner.getSelectedItem();

        //その他の時
        String otherPro = typesEditText.getText().toString();

        //リフォームの個所
        if (checkBox1.isChecked()){
            check1 ="風呂　";
        }
        if (checkBox2.isChecked()){
            check2 ="浴室　";
        }
        if (checkBox3.isChecked()){
            check3 ="トイレ　";
        }
        if (checkBox4.isChecked()){
            check4 ="キッチン　";
        }
        if (checkBox5.isChecked()){
            check5 ="洗面所　";
        }
        if (checkBox6.isChecked()){
            check6 ="外壁　";
        }
        if (checkBox7.isChecked()){
            check7 ="屋根　";
        }
        if (checkBox8.isChecked()){
            check8 ="庭　";
        }
        if (checkBox9.isChecked()){
            check9 ="ベランダ・バルコニー　";
        }
        if (checkBox10.isChecked()){
            check10 ="リビング　";
        }
        if (checkBox11.isChecked()){
            check11 ="ダイニング　";
        }
        if (checkBox12.isChecked()){
            check12 ="洋室・和室　";
        }
        if (checkBox13.isChecked()){
            check13 ="玄関　";
        }
        if (checkBox14.isChecked()){
            check14 ="廊下　";
        }
        if (checkBox15.isChecked()){
            check15 ="階段　";
        }
        if (checkBox16.isChecked()){
            check16 ="リノベーション　";
        }
        if (checkBox17.isChecked()){
            check17 ="その他　";
        }

        String place =check1+check2+check3+check4+check5+check6+check7+check8+check9+check10+check11+check12+check13+check14+check15+check16+check17;




        //その他のリフォーム箇所
        String otherPlace = otherReformEditText.getText().toString();

        //予算任意
        String budget = (String)budgedSpinner.getSelectedItem();

        //年齢任意
        String age = (String)ageSpinner.getSelectedItem();


        //性別任意
        int i = radioGroup.getCheckedRadioButtonId();
        int m = manRadioButton.getId();
        int w = womanRadioButton.getId();

        if (i == m){
            sex = "男性";
        }else if (i == w){
            sex = "女性";
        }else{
            sex = "未設定";
        }

        //要望
        String request = requestEditText.getText().toString();
        String estimate ="0";

        String mUid = user.getUid();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String flag = sp.getString(Const.FlagKEY, "");
        String name = sp.getString(Const.NameKEY,"");

        Map<String, String> data = new HashMap<String, String>();
        data.put("mUid",mUid);
        data.put("UserName",name);
        data.put("postalCode" ,postalCode);
        data.put("ageBuild" ,ageBuild);
        data.put("form" , form);
        data.put("otherForm" ,otherForm);
        data.put("pro" ,pro);
        data.put("otherPro" ,otherPro);
        data.put("place" ,place);
        data.put("otherPlace" ,otherPlace);
        data.put("budget" ,budget);
        data.put("age" ,age);
        data.put("sex" ,sex);
        data.put("estimate" ,estimate);
        data.put("request" ,request);
        data.put("flag" ,flag);

        customerPathRef.child(mUid).setValue(data);

        savePersonalData(mUid,name,postalCode,ageBuild,form,otherForm,pro,otherPro,place,otherPlace,budget,age,sex,estimate,request,flag);



        CustomerAccountFragment fragmentCustomerAccount = new CustomerAccountFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.container,fragmentCustomerAccount,CustomerAccountFragment.TAG)
                .commit();


//            Snackbar.make(v, "郵便番号を入力してください", Snackbar.LENGTH_LONG).show();




    }
    private void savePersonalData(String mUid,String name,String postalCode,String ageBuild,String form,String otherForm,String pro,String otherPro,String place,String otherPlace,String budget,String age,String sex,String estimate,String request,String flag) {
        // Preferenceに保存する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Const.mUidKEY, mUid);
        editor.putString(Const.NameKEY, name);
        editor.putString(Const.PostalCodeKEY, postalCode);
        editor.putString(Const.AgeBuildKEY, ageBuild);
        editor.putString(Const.FormKEY, form);
        editor.putString(Const.OtherFormKEY, otherForm);
        editor.putString(Const.ProKEY, pro);
        editor.putString(Const.OtherProKEY, otherPro);
        editor.putString(Const.PlaceKEY, place);
        editor.putString(Const.OtherPlaceKEY, otherPlace);
        editor.putString(Const.BudgetKEY, budget);
        editor.putString(Const.AgeKEY, age);
        editor.putString(Const.SexKEY, sex);
        editor.putString(Const.EstimateKEY, estimate);
        editor.putString(Const.RequestKEY, request);
        editor.putString(Const.FlagKEY, flag);


        editor.commit();


    }

}
