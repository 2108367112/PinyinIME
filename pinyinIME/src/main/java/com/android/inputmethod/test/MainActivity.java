package com.android.inputmethod.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.inputmethod.pinyin.R;
import com.android.inputmethod.pinyin.SkbPool;
import com.android.inputmethod.pinyin.SoftKeyboardView;

public class MainActivity extends Activity implements View.OnClickListener {

    private int i = 0;
    SoftKeyboardView softKeyboardView;
    SkbPool skbPool;
    Button btnSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        softKeyboardView = (SoftKeyboardView) findViewById(R.id.my_soft_keyboard_view);
        btnSwitch = (Button) findViewById(R.id.btn_switch);
        btnSwitch.setOnClickListener(this);
        skbPool = SkbPool.getInstance();
        softKeyboardView.setSoftKeyboard(skbPool.getSoftKeyboard(R.xml.skb_phone, R.xml.skb_phone9, 720, 500, this));
//        softKeyboardView.setSoftKeyboard(skbPool.getSoftKeyboard(R.xml.skb_qwerty, R.xml.skb_qwerty, 720, 500, this));
//        softKeyboardView.setSoftKeyboard(skbPool.getSoftKeyboard(R.xml.skb_smiley, R.xml.skb_smiley, 720, 500, this));
//        softKeyboardView.setSoftKeyboard(skbPool.getSoftKeyboard(R.xml.skb_sym1, R.xml.skb_sym1, 720, 500, this));
//        softKeyboardView.setSoftKeyboard(skbPool.getSoftKeyboard(R.xml.skb_sym2, R.xml.skb_sym2, 720, 500, this));
    }

    @Override
    public void onClick(View view) {
        i++;
        if (i >= 5) i = 0;
        switch (i) {
            case 0:
//                softKeyboardView.setSoftKeyboard(skbPool.getSoftKeyboard(R.xml.skb_phone, R.xml.skb_phone, 720, 500, this));
                softKeyboardView.setSoftKeyboard(skbPool.getSoftKeyboard(R.xml.skb_phone, R.xml.skb_phone9, 720, 500, this));
                btnSwitch.setText("skb_phone 4行");
                break;
            case 1:
                softKeyboardView.setSoftKeyboard(skbPool.getSoftKeyboard(R.xml.skb_qwerty, R.xml.skb_qwerty, 720, 500, this));
                btnSwitch.setText("skb_qwerty 6行");
                break;
            case 2:
                softKeyboardView.setSoftKeyboard(skbPool.getSoftKeyboard(R.xml.skb_smiley, R.xml.skb_smiley, 720, 500, this));
                btnSwitch.setText("skb_smiley 4行");
                break;
            case 3:
                softKeyboardView.setSoftKeyboard(skbPool.getSoftKeyboard(R.xml.skb_sym1, R.xml.skb_sym1, 720, 500, this));
                btnSwitch.setText("skb_sym1 4行");
                break;
            case 4:
                softKeyboardView.setSoftKeyboard(skbPool.getSoftKeyboard(R.xml.skb_sym2, R.xml.skb_sym2, 720, 500, this));
                btnSwitch.setText("skb_sym2 4行");
                break;
        }
        softKeyboardView.invalidate();//该方法会导致 SoftKeyboardView 重新绘制
    }
}
