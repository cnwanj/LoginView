package com.weiyh.loginview;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    // 手机号
    private EditText editPhone;
    private TextView editGetPhone;
    // 验证码
    private EditText editCode;

    private Button btnNext;
    private Button btnLogin;
    private Button btnGetCode;
    private LinearLayout layoutNext;
    private LinearLayout layoutLogin;
    private Handler handler;

    private Fragment nextFragment;
    private Fragment loginFragment;

    private FragmentManager fragmentManager;

    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        fragmentManager = getSupportFragmentManager();
//        // 实例化要显示的碎片
//        nextFragment = new NextFragment();
//        showOrHideFragment(nextFragment);

        editPhone = findViewById(R.id.edit_phone);
        editGetPhone = findViewById(R.id.edit_get_phone);
        editCode = findViewById(R.id.edit_code);
        layoutNext = findViewById(R.id.layout_next);
        layoutLogin = findViewById(R.id.layout_login);
        btnGetCode = findViewById(R.id.btn_get_code);

        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.obj == null) {
                    // 获取秒数
                    Log.d("====>", msg.what + "");
                    btnGetCode.setText(msg.what + "秒后重试");
                } else {
                    // 已完成
                    Log.d("====>obj", msg.obj.toString());
                    btnGetCode.setText("重新获取");
                    btnGetCode.setClickable(true);
                }
            }
        };
    }

    /**
     * 点击下一步
     * @param view
     */
    public void clickNext(View view) {
//        if (loginFragment == null) {
//            // 若要显示的碎片为null，则实例化
//            loginFragment = new LoginFragment();
//        }
//        showOrHideFragment(loginFragment);
//        // 将当前事务添加到返回栈中，即按下“返回”时，回退上一步的操作
//        transaction.addToBackStack(null);

        Editable phone = editPhone.getText();
        if (TextUtils.isEmpty(phone)) {
            // 若手机号为空，提示
            Toast.makeText(this, "手机号不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            // 将获取到的手机号拼接显示到登录页
            editGetPhone.append(phone);
            // 隐藏手机号填写页面，GONE：不占用空间
            layoutNext.setVisibility(View.GONE);
            layoutLogin.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 方法1：timer
     * 点击获取验证码
     * @param view
     */
    /*public void getCode1(View view) {
        btnGetCode.setBackgroundColor(Color.argb(0, 0, 0, 0));
        btnGetCode.setTextColor(Color.GRAY);
        btnGetCode.setClickable(false);
        CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                btnGetCode.setText((millisUntilFinished / 1000) + "秒后重试");
                Log.d(">>>>>>>", (millisUntilFinished / 1000) + "");
            }

            @Override
            public void onFinish() {
                btnGetCode.setText("重新获取");
                btnGetCode.setClickable(true);
            }
        }.start();
        // timer.onFinish();
    }*/

    /**
     * 方法2：handler
     * 点击获取验证码
     * @param view
     */
    public void getCode(View view) {
        btnGetCode.setBackgroundColor(Color.argb(0, 0, 0, 0));
        btnGetCode.setTextColor(Color.GRAY);
        btnGetCode.setClickable(false);
        // 原子类型的Integer，在多线程情况可以避免重排序
        final AtomicInteger time = new AtomicInteger(60);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(time.intValue() >= 0) {
                    try {
                        // 通过handler发送当前计时
                        handler.sendEmptyMessage(time.intValue());
                        Thread.sleep(1000);
                        // 每隔1秒进行time--
                        time.decrementAndGet();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 计时结束
                Message msg = handler.obtainMessage();
                msg.obj = "重新获取";
                handler.sendMessage(msg);
            }
        }).start();
    }

    /**
     * 点击登录
     * @param view
     */
    public void login(View view) {
        Editable code = editCode.getText();
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, "验证码不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            finish();
        }
    }

    /**
     * 将碎片添加到事务中，并显示或隐藏碎片
     * @param addFragment   要添加的碎片
     */
//    public void showOrHideFragment(Fragment addFragment) {
//        // 通过碎片管理器开启事务
//        transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.show_content, addFragment);
//        transaction.commit();
//    }
}
