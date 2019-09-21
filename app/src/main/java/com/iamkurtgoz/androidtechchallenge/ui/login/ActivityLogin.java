package com.iamkurtgoz.androidtechchallenge.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.iamkurtgoz.androidtechchallenge.R;
import com.iamkurtgoz.androidtechchallenge.tools.PreferenceConstants;
import com.iamkurtgoz.androidtechchallenge.ui.main.ActivityMain;
import com.iamkurtgoz.easystore.EasyStore;

public class ActivityLogin extends AppCompatActivity implements ActivityLoginPresenter.View {

    //VARIABLES
    private final String APP_USERNAME = "kariyer";
    private final String APP_PASSWORD = "2019ADev";
    private ActivityLoginPresenterImpl presenter;

    //VIEWS
    private EditText editUserName, editPassword;
    private Switch switchRemember;
    private AppCompatButton btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new ActivityLoginPresenterImpl(ActivityLogin.this, this);
        presenter.start();
    }

    @Override
    public void init(Boolean isRemember) {
        editUserName = findViewById(R.id.activity_login_editUserName);
        editPassword = findViewById(R.id.activity_login_editPassword);
        switchRemember = findViewById(R.id.activity_login_switchRemember);
        btnLogin = findViewById(R.id.activity_login_btnLogin);

        if (isRemember){ //Beni hatırla aktif ise direkt olarak ana ekrana yönlendiriyoruz
            Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
            startActivity(intent);
            finish(); //Ana aktiviteye geçtikten sonra login activitesini kapatıyorum
        }
    }

    @Override
    public void registerHandlers(){
        setBtnLoginClick();
    }

    private void setBtnLoginClick(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = editUserName.getText().toString();
                String password = editPassword.getText().toString();
                Boolean isRemember = switchRemember.isChecked();

                //Edittextler boşmu kontrol ediyoruz
                if (userName.length() == 0 || password.length() == 0){
                    Toast.makeText(ActivityLogin.this, getString(R.string.no_empty), Toast.LENGTH_SHORT).show();

                //Kullanıcı adı ve şifreyi kontrol ediyoruz.
                } else if (userName.equalsIgnoreCase(APP_USERNAME) && password.equalsIgnoreCase(APP_PASSWORD)){

                    //Beni hatırla durumunu kaydediyoruz
                    EasyStore.use().set(PreferenceConstants.BOOL_IS_REMEMBER, isRemember);
                    Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
                    startActivity(intent);
                    finish(); //Ana aktiviteye geçtikten sonra login activitesini kapatıyorum
                } else {
                    //Kullanıcı adı veya şifre uyumsuz ise hata mesajı veriyoruz
                    Toast.makeText(ActivityLogin.this, getString(R.string.username_or_pass_invalid), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
