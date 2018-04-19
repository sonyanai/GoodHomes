package jp.techacademy.son.goodhomes;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
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

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_CODE = 100;
    private static final int CHOOSER_REQUEST_CODE = 100;

    public ListFragment fragmentList;
    public NotificationFragment fragmentNotification;
    public MessageFragment fragmentMessage;
    Uri mPictureUri;

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

                case R.id.item_Notification:
                    fragmentNotification = new NotificationFragment();
                    transaction.replace(R.id.container, fragmentNotification);
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
    public void onSelfCheck() {
        // パーミッションの許可状態を確認する
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                showChooser();
            } else {
                // 許可されていないので許可ダイアログを表示する
                requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_CODE);
                return;
            }
        } else {
            showChooser();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //許可された
                    showChooser();
                } else {
                    //許可されなかった
                }
                break;
            default:
                break;
        }
    }

    public void showChooser() {
        // ギャラリーから選択するIntent
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_PICK);
        //galleryに飛ばして選択させる
        startActivityForResult(Intent.createChooser(galleryIntent,"画像/動画を選択"), CHOOSER_REQUEST_CODE);
    }

    //選択した結果を受け取る
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHOOSER_REQUEST_CODE && resultCode == RESULT_OK) {


            if (resultCode != RESULT_OK) {
                if (mPictureUri != null) {
                    getContentResolver().delete(mPictureUri, null, null);
                    mPictureUri = null;
                }
                return;
            }

            // 画像を取得
            Uri uri = (data == null || data.getData() == null) ? mPictureUri : data.getData();

            // URIからBitmapを取得する
            Bitmap image;
            try {
                ContentResolver contentResolver = getContentResolver();

                InputStream inputStream = contentResolver.openInputStream(uri);
                image = BitmapFactory.decodeStream(inputStream);


/*
            // 取得したBimapの長辺を500ピクセルにリサイズする
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();
            float scale = Math.min((float) 500 / imageWidth, (float) 500 / imageHeight); // (1)

            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);

            Bitmap resizedImage =  Bitmap.createBitmap(image, 0, 0, imageWidth, imageHeight, matrix, true);
*/

                Bitmap bbb = Bitmap.createBitmap(image);

                BusinessLoginFragment.companyImageView.setImageBitmap(bbb);

                inputStream.close();
            } catch (Exception e) {
                return;
            }











            mPictureUri = null;

        }
    }





    public static String getPath(Context context, Uri uri) {
        ContentResolver contentResolver = context.getContentResolver();
        String[] columns = { MediaStore.Images.Media.DATA };
        Cursor cursor = contentResolver.query(uri, columns, null, null, null);
        cursor.moveToFirst();
        String path = cursor.getString(0);
        cursor.close();
        return path;
    }


}
