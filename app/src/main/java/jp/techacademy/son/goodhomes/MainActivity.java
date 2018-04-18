package jp.techacademy.son.goodhomes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {



    public ListFragment fragmentList;
    public FavoriteFragment fragmentFavorite;
    public MessageFragment fragmentMessage;

    private FirebaseUser user;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.item_List:
                    fragmentList = new ListFragment();
                    transaction.replace(R.id.container, fragmentList);
                    transaction.commit();
                    return true;

                case R.id.item_Favorite:
                    fragmentFavorite = new FavoriteFragment();
                    transaction.replace(R.id.container, fragmentFavorite);
                    transaction.commit();
                    return true;

                case R.id.item_Message:
                    fragmentMessage = new MessageFragment();
                    transaction.replace(R.id.container, fragmentMessage);
                    transaction.commit();
                    return true;

            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







        //BottomNavigationViewの定義して設置する
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        //リスナーのセット
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        ListFragment fragmentList = new ListFragment();
        transaction.add(R.id.container, fragmentList);
        transaction.commit();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        user = FirebaseAuth.getInstance().getCurrentUser();
        switch (item.getItemId()){
            case R.id.sortButton:
                SortFragment fragmentSort = new SortFragment();
                transaction.replace(R.id.container, fragmentSort);
                transaction.commit();
                break;
            case R.id.accountButton:
                if(user==null){
                    View view = findViewById(android.R.id.content);
                    Snackbar.make(view, "アカウントを作成してください", Snackbar.LENGTH_LONG).show();
                    intentLogin();
                }else{
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
                    String data = sp.getString(Const.FlagKEY, "");
                    if (data.equals("customer")){
                        CustomerAccountFragment fragmentCustomerAccount = new CustomerAccountFragment();
                        transaction.replace(R.id.container, fragmentCustomerAccount);
                        transaction.commit();
                    }else {
                        BusinessAccountFragment fragmentBusinessAccount = new BusinessAccountFragment();
                        transaction.replace(R.id.container, fragmentBusinessAccount);
                        transaction.commit();
                    }

                }
                break;
            case R.id.loginButton:
                if(user==null) {
                    intentLogin();
                }else{
                    View view = findViewById(android.R.id.content);
                    Snackbar.make(view, "ログイン中です", Snackbar.LENGTH_LONG).show();
                }
                break;
            case R.id.logoutButton:
                if(user!=null) {
                    LogoutFragment fragmentLogout = new LogoutFragment();
                    transaction.replace(R.id.container, fragmentLogout);
                    transaction.commit();
                }else{
                    View view = findViewById(android.R.id.content);
                    Snackbar.make(view, "ログインしていません", Snackbar.LENGTH_LONG).show();
                }
                break;
        }
        return false;
    }
    public void intentLogin(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}
