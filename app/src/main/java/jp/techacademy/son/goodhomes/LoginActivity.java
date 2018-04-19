package jp.techacademy.son.goodhomes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by taiso on 2018/04/03.
 */

public class LoginActivity extends AppCompatActivity {

    EditText mEmailEditText;
    EditText mPasswordEditText;
    EditText UserNameEditText;

    String name;
    String postalCode;
    String ageBuild;
    String form;
    String otherForm;
    String pro;
    String otherPro;
    String place;
    String otherPlace;
    String budget;
    String age;
    String sex;
    String estimate;
    String flag;
    String request;
    RadioGroup cbRadioGroup;
    RadioButton customerRadioButton;
    RadioButton businessRadioButton;



    FirebaseAuth mAuth;
    OnCompleteListener<AuthResult> mCreateAccountListener;
    OnCompleteListener<AuthResult> mLoginListener;
    DatabaseReference mDataBaseReference;
    DatabaseReference userRef;

    // アカウント作成時にフラグを立て、ログイン処理後に名前をFirebaseに保存する
    boolean mIsCreateAccount = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);






        mDataBaseReference = FirebaseDatabase.getInstance().getReference();

        // FirebaseAuthのオブジェクトを取得する
        mAuth = FirebaseAuth.getInstance();

        // アカウント作成処理のリスナー
        mCreateAccountListener = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {


                if (task.isSuccessful()) {
                    // 成功した場合
                    // ログインを行う
                    FirebaseUser user = mAuth.getCurrentUser();


                    if (mIsCreateAccount) {
                        if (flag.equals("customer")){
                            String mUid = user.getUid();

                            postalCode ="0";
                            ageBuild ="0";
                            form ="0";
                            otherForm ="0";
                            pro ="0";
                            otherPro ="0";
                            place ="0";
                            otherPlace ="0";
                            budget ="0";
                            age ="0";
                            sex ="0";
                            estimate ="0";
                            request ="0";



                            name = UserNameEditText.getText().toString();
                            Map<String,String> data = new HashMap<String,String>();

                            data.put("mUid",mUid);
                            data.put("UserName",name);
                            data.put("postalCode" ,postalCode);
                            data.put("ageBuild" ,ageBuild);
                            data.put("form" ,form);
                            data.put("otherForm" ,otherForm);
                            data.put("pro" ,pro);
                            data.put("otherPro" ,otherPro);
                            data.put("place" ,place);
                            data.put("otherPlace" ,otherPlace);
                            data.put("budget" ,budget);
                            data.put("age" ,age);
                            data.put("sex" ,sex);
                            data.put("estimate" ,estimate);
                            data.put("request",request);
                            data.put("flag" ,flag);

                            Map<String,Object> childUpdates = new HashMap<>();
                            childUpdates.put(mUid,data);





                            userRef.updateChildren(childUpdates);


                            // 表示名をPrefarenceに保存する
                            savePersonalData(mUid,name,postalCode,ageBuild,form,otherForm,pro,otherPro,place,otherPlace,budget,age,sex,estimate,request,flag);


                            // アカウント作成の時は表示名をFirebaseに保存する
                            String email = mEmailEditText.getText().toString();
                            String password = mPasswordEditText.getText().toString();
                            login(email, password);

                        }else {
                            String mUid = user.getUid();

                            String companyName="0";
                            String address="0";
                            String companyNumber="0";
                            String sex="0";
                            String age="0";
                            String bitmapString="0";
                            String totalEstimate="0";
                            String unwatchEstimate="0";
                            String thisPayment="0";
                            String nextPayment="0";
                            String pr = "0";
                            String industry="0";
                            String totalEvaluation="0";
                            String moneyEvaluation="0";


                            name = UserNameEditText.getText().toString();
                            Map<String,String> data = new HashMap<String,String>();

                            data.put("mUid",mUid);
                            data.put("CompanyName" ,companyName);
                            data.put("Address" ,address);
                            data.put("CompanyNumber" ,companyNumber);
                            data.put("name",name);
                            data.put("BitmapString" ,bitmapString);
                            data.put("TotalEstimate" ,totalEstimate);
                            data.put("UnwatchEstimate" ,unwatchEstimate);
                            data.put("ThisPayment" ,thisPayment);
                            data.put("NextPayment" ,nextPayment);
                            data.put("TotalEvaluation" ,totalEvaluation);
                            data.put("MoneyEvaluation" ,moneyEvaluation);
                            data.put("Industry" ,industry);
                            data.put("Pr" ,pr);
                            data.put("flag" ,flag);


                            Map<String,Object> childUpdates = new HashMap<>();
                            childUpdates.put(mUid,data);





                            userRef.updateChildren(childUpdates);


                            // 表示名をPrefarenceに保存する
                            saveCompanyData(mUid,companyName,address,companyNumber,name,bitmapString,totalEstimate,unwatchEstimate,thisPayment,nextPayment,totalEvaluation,moneyEvaluation,industry,pr,flag);


                            // アカウント作成の時は表示名をFirebaseに保存する
                            String email = mEmailEditText.getText().toString();
                            String password = mPasswordEditText.getText().toString();
                            login(email, password);

                        }





                    } else {
                        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                Map data = (Map) snapshot.getValue();
                                saveName((String)data.get("name"));
                            }
                            @Override
                            public void onCancelled(DatabaseError firebaseError) {
                            }
                        });
                    }

                } else {

                    // 失敗した場合
                    // エラーを表示する
                    View view = findViewById(android.R.id.content);
                    Snackbar.make(view, "アカウント作成に失敗しました", Snackbar.LENGTH_LONG).show();

                }
            }
        };

        // ログイン処理のリスナー
        mLoginListener = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    View view = findViewById(android.R.id.content);
                    Snackbar.make(view, "ログインに成功しました", Snackbar.LENGTH_LONG).show();


                    if (flag!=null){
                        Bundle bundle = new Bundle();

                        bundle.putString("data",flag);

                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }





                } else {
                    // 失敗した場合
                    // エラーを表示する
                    View view = findViewById(android.R.id.content);
                    Snackbar.make(view, "ログインに失敗しました", Snackbar.LENGTH_LONG).show();
                }
            }
        };

        // UIの準備
        setTitle("ログイン");

        mEmailEditText = (EditText) findViewById(R.id.emailText);
        mPasswordEditText = (EditText) findViewById(R.id.passwordText);
        UserNameEditText = (EditText) findViewById(R.id.UserNameEditText);

        cbRadioGroup = (RadioGroup)findViewById(R.id.cbRadioGroup);
        customerRadioButton = (RadioButton)findViewById(R.id.customerRadioButton);
        businessRadioButton = (RadioButton)findViewById(R.id.businessRadioButton);

        Button createButton = (Button) findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // キーボードが出てたら閉じる
                InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                int i = cbRadioGroup.getCheckedRadioButtonId();
                int c = customerRadioButton.getId();
                int b = businessRadioButton.getId();



                if (i == c){
                    userRef = mDataBaseReference.child(Const.CustomerPath);
                    flag = "customer";
                    String email = mEmailEditText.getText().toString();
                    String password = mPasswordEditText.getText().toString();
                    name = UserNameEditText.getText().toString();

                    if (email.length() != 0 && password.length() >= 6 && name.length() > 0) {
                        // ログイン時に表示名を保存するようにフラグを立てる
                        mIsCreateAccount = true;

                        createAccount(email, password);
                    } else {
                        // エラーを表示する
                        Snackbar.make(v, "正しく入力してください", Snackbar.LENGTH_LONG).show();
                    }

                }else if (i==b){
                    userRef = mDataBaseReference.child(Const.BusinessPath);
                    flag = "business";
                    String email = mEmailEditText.getText().toString();
                    String password = mPasswordEditText.getText().toString();
                    name = UserNameEditText.getText().toString();

                    if (email.length() != 0 && password.length() >= 6 && name.length() > 0) {
                        // ログイン時に表示名を保存するようにフラグを立てる
                        mIsCreateAccount = true;

                        createAccount(email, password);
                    } else {
                        // エラーを表示する
                        Snackbar.make(v, "正しく入力してください", Snackbar.LENGTH_LONG).show();
                    }

                }else{
                    Snackbar.make(v, "個人か企業を選択してください", Snackbar.LENGTH_LONG).show();

                }





            }
        });

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // キーボードが出てたら閉じる
                InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                int i = cbRadioGroup.getCheckedRadioButtonId();

                if (i > 0) {

                    String email = mEmailEditText.getText().toString();
                    String password = mPasswordEditText.getText().toString();


                    if (email.length() != 0 && password.length() >= 6) {
                        // フラグを落としておく
                        mIsCreateAccount = false;

                        login(email, password);
                    } else {
                        // エラーを表示する
                        Snackbar.make(v, "正しく入力してください", Snackbar.LENGTH_LONG).show();
                    }
                }else{
                    Snackbar.make(v, "個人か企業かを選択してください", Snackbar.LENGTH_LONG).show();
                }


            }
        });
    }

    private void createAccount(String email, String password) {

        // アカウントを作成する
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(mCreateAccountListener);
    }

    private void login(String email, String password) {

        // ログインする
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(mLoginListener);



    }

    private void saveName(String name) {
        // Preferenceに保存する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Const.NameKEY, name);
        editor.commit();
    }

    private void savePersonalData(String mUid,String name,String postalCode,String ageBuild,String form,String otherForm,String pro,String otherPro,String place,String otherPlace,String budget,String age,String sex,String estimate,String request,String flag) {
        // Preferenceに保存する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
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

    private void saveCompanyData(String mUid,String companyName,String address,String companyNumber,String name,String bitmapString,String totalEstimate,String unwatchEstimate,String thisPayment,String nextPayment,String totalEvaluation,String moneyEvaluation,String industry,String pr,String flag) {
        // Preferenceに保存する
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Const.mUidKEY, mUid);
        editor.putString(Const.CompanyNameKEY,companyName );
        editor.putString(Const.AddressKEY, address);
        editor.putString(Const.CompanyNumberKEY, companyNumber);
        editor.putString(Const.NameKEY, name);
        editor.putString(Const.BitmapStringKEY, bitmapString);
        editor.putString(Const.TotalEstimateKEY, totalEstimate);
        editor.putString(Const.UnwatchEstimateKEY, unwatchEstimate);
        editor.putString(Const.ThisPaymentKEY, thisPayment);
        editor.putString(Const.NextPaymentKEY, nextPayment);
        editor.putString(Const.TotalEvaluationKEY, totalEvaluation);
        editor.putString(Const.MoneyEvaluationKEY, moneyEvaluation);
        editor.putString(Const.IndustryKEY, industry);
        editor.putString(Const.PrKEY, pr);
        editor.putString(Const.FlagKEY, flag);


        editor.commit();


    }


}

