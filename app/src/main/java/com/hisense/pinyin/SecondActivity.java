package com.hisense.pinyin;

import android.app.Activity;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        final KeyboardView keyboard = (KeyboardView) findViewById(R.id.kv_keyboard);
        final EditText editText = (EditText) findViewById(R.id.edit_text1);
        //在我们点EditText的时候弹出我们的软键盘
//        new KeyBoardUtil(keyboard, editText,this).showKeyboard();

        //在我们点EditText的时候弹出我们的软键盘
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (editText.hasFocus()) {
                    //用来初始化我们的软键盘
                    new KeyBoardUtil(keyboard, editText, SecondActivity.this).showKeyboard();
                }
                return false;
            }
        });

    }
}
