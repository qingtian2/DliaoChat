package androidpermission.com.bw.test.dliao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.mob.MobSDK;

import androidpermission.com.bw.test.dliao.R;
import androidpermission.com.bw.test.dliao.base.BaseMvpActivity;
import androidpermission.com.bw.test.dliao.fragments.FragmentRegist;
import androidpermission.com.bw.test.dliao.mview.RegistView;
import androidpermission.com.bw.test.dliao.presenter.RegistPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;


public class RegistActivity extends BaseMvpActivity<RegistView,RegistPresenter> {

    @BindView(R.id.pub_back)
    TextView pub_back;
    @BindView(R.id.pub_title)
    TextView pub_title;
    @BindView(R.id.resist_user)
    EditText resist_user;
    @BindView(R.id.regist_pwd)
    EditText regist_pwd;
    @BindView(R.id.regist_button)
    Button regist_button;
    @BindView(R.id.getnum)
    Button getnum;
    EventHandler eventHandler;

    @Override
    public RegistPresenter initPresenter() {
        return new RegistPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        getSMS();
        MobSDK.init(this, "1f3067768039f", "8e74e9ceeaaa61d8837eeb6c9792c891");
        ButterKnife.bind(this);
        pub_title.setText("登录");

    }

    private void getSMS() {
        // 创建EventHandler对象
         eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable)data;
                    String msg = throwable.getMessage();
                    Toast.makeText(RegistActivity.this, msg, Toast.LENGTH_SHORT).show();
                } else {
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        // 处理你自己的逻辑

                    }
                }
            }
        };

        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);
    }

    @OnClick(R.id.pub_back)
    public void onBack(){
        finish();
    }

    @OnClick(R.id.pub_title)
    public void onTitle(){
        startActivity(new Intent(this, LoginActivity.class));
    }

    @OnClick(R.id.regist_button)
    public void onRegist(){
        getSupportFragmentManager().beginTransaction().replace(R.id.regist_layout, new FragmentRegist()).commit();
    }
    @OnClick(R.id.getnum)
    public void getNum(){
    SMSSDK.getVerificationCode("+86","181110020236");

    }

    protected void onDestroy() {
        super.onDestroy();

        SMSSDK.unregisterEventHandler(eventHandler);
    }
}
