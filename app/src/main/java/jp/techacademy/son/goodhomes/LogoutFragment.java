package jp.techacademy.son.goodhomes;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * Created by taiso on 2018/04/03.
 */

public class LogoutFragment extends Fragment {
    public static final String TAG = "LogoutFragment";


    private FirebaseUser user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_logout,container,false);

        return v;
    }

    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        view.findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = FirebaseAuth.getInstance().getCurrentUser();
                if (user==null){
                    MainActivity activity = (MainActivity) getActivity();
                    activity.intentLogin();
                }else{
                    Snackbar.make(v, "ログイン中です", Snackbar.LENGTH_LONG).show();
                }

            }
        });

        view.findViewById(R.id.logoutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = FirebaseAuth.getInstance().getCurrentUser();
                if (user !=null){
                    FirebaseAuth.getInstance().signOut();
                    Snackbar.make(v, "ログアウトしました", Snackbar.LENGTH_LONG).show();
                }else{
                    Snackbar.make(v, "ログインしていません", Snackbar.LENGTH_LONG).show();
                }

            }
        });
    }

}
