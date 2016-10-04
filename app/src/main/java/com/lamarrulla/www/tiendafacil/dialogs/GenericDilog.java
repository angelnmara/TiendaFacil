package com.lamarrulla.www.tiendafacil.dialogs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.lamarrulla.www.tiendafacil.LoginActivity;
import com.lamarrulla.www.tiendafacil.MainActivity;
import com.lamarrulla.www.tiendafacil.R;
//import com.lamarrulla.www.tiendafacil.preference.UserAccount;

public class GenericDilog extends Activity implements View.OnClickListener {
    boolean isOK = false;
    boolean isOKLogin = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_generic_dilog);
        //getSupportActionBar().hide();
        getWindow().getAttributes().windowAnimations = R.style.dialog_animation;

        TextView tvMessage=(TextView) findViewById(R.id.tv_message_dialog);
        TextView btnAgree=(TextView) findViewById(R.id.btn_agree);


        ImageView iconDialog=(ImageView) findViewById(R.id.iv_generic_dialog);

        findViewById(R.id.btn_agree).setOnClickListener(this);



        Bundle extras = getIntent().getExtras();
        String message="";


        if (extras != null) {
            message = extras.getString("message");
            isOK=extras.getBoolean("isOk");
            isOKLogin=extras.getBoolean("isOkLogin");

        }


        if (isOK || isOKLogin){
            iconDialog.setImageResource(R.drawable.ic_ok_dialog);
            btnAgree.setTextColor(ContextCompat.getColor(this,R.color.colorAccent));
        }


        tvMessage.setText(message);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_agree:

                if (isOK){
                    Intent i = new Intent(this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    overridePendingTransition(R.anim.top_in, R.anim.top_out);

                }else if(isOKLogin){

                    /*UserAccount userAccount;

                    userAccount = new UserAccount(this);
                    userAccount.setUserId("");
                    userAccount.setEmail("");
                    userAccount.setToken("");
                    userAccount.setSessionStart(true);*/

                    Intent i = new Intent(this, LoginActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(i);
                    overridePendingTransition(R.anim.top_in, R.anim.top_out);

            }else{
                   finish();
                }


                break;
        }
    }
}
