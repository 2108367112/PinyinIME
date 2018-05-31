package com.hisense.pinyin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    EditText edittext1;
    PopupKeyboardUtil smallKeyboardUtil;
    private View viewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String.format(getString(R.string.app_text),3,1986);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        edittext1 = findViewById(R.id.edit_text1);

        smallKeyboardUtil = new PopupKeyboardUtil(this);
//        smallKeyboardUtil.attachTo(edittext1, false);
        //smallKeyboardUtil.setAutoShowOnFocs(false);
    }

    public void onClickView(View view) {
        if (view.getId() == R.id.btn1)
            smallKeyboardUtil.showSoftKeyboard();
        if (view.getId() == R.id.btn2)
            smallKeyboardUtil.hideSoftKeyboard();

    }
}
