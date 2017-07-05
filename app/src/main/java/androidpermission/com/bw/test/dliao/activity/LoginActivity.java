package androidpermission.com.bw.test.dliao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidpermission.com.bw.test.dliao.R;
import androidpermission.com.bw.test.dliao.base.BaseMvpActivity;
import androidpermission.com.bw.test.dliao.cipher.aes.JNCryptorUtils;
import androidpermission.com.bw.test.dliao.cipher.rsa.RsaUtils;
import androidpermission.com.bw.test.dliao.mview.LoginView;
import androidpermission.com.bw.test.dliao.presenter.LoginPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public  class LoginActivity extends BaseMvpActivity<LoginView,LoginPresenter> {


    @BindView(R.id.login_login)
    Button login_login;
    @BindView(R.id.pub_back)
     TextView pub_back;
    @BindView(R.id.pub_title)
    TextView pub_title;
    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        pub_title.setText("注册");

        //随机数  rsa公钥加密
        String random = RsaUtils.getInstance().createRandom();
        System.out.println(random);
      //将随机数进行公钥加密
        String rsaKey = RsaUtils.getInstance().createRsaSecret(getApplicationContext(), random);
        System.out.println(rsaKey);
        String mobile = JNCryptorUtils.getInstance().encryptData("18511085102", getApplicationContext(), random);
        System.out.println("mobile = " + mobile);

    }

    @OnClick(R.id.login_login)
    public void btClick(){

        startActivity(new Intent(this, RegistActivity.class));

    }


    @OnClick(R.id.pub_back)
    public void onBack(){
        finish();
    }

    @OnClick(R.id.pub_title)
    public void onTitle(){
        startActivity(new Intent(this, RegistActivity.class));
    }






}
