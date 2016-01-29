package com.ball.andy.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Andy on 2016/1/21.
 */
public class ChoosePlayerActivity extends AppCompatActivity {


    private TextInputLayout inputLayout;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.choose_player_layout);
        inputLayout = (TextInputLayout) this.findViewById(R.id.textInput);
        inputLayout.setHint("请输入姓名:");


        final EditText editText = inputLayout.getEditText();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    inputLayout.setErrorEnabled(true);
                    inputLayout.setError("名字不得為空值");
                } else {
                    inputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        final Button button = (Button) this.findViewById(R.id.addBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editText.setText("");
                Snackbar.make(v, "新增成功", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                inputLayout.setErrorEnabled(false);
            }
        });
    }
}
