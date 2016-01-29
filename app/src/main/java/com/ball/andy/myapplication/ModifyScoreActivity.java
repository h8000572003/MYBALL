package com.ball.andy.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.ball.andy.myapplication.dao.GameDAO;
import com.ball.andy.myapplication.domain.GamePO;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * Created by Andy on 2016/1/25.
 */
public class ModifyScoreActivity extends AppCompatActivity {
    private TextInputLayout inputLayoutA;
    private TextInputLayout inputLayoutB;
    private CheckBox gameCheckBox;

    private Button modify_btn;


    private GamePO gamePO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_play_layout);

        gamePO =
                (GamePO) getIntent().getSerializableExtra("data");

        setContentView(R.layout.score_play_layout);

        this.modify_btn = (Button) this.findViewById(R.id.modify_btn);
        this.gameCheckBox = (CheckBox) this.findViewById(R.id.gameCheckBox);

        this.gameCheckBox.setChecked(gamePO.getStatus().equals("Y")?true:false);

        inputLayoutA = (TextInputLayout) this.findViewById(R.id.textInputA);
        inputLayoutA.setHint(gamePO.getaName() + "分數");


        final EditText editText = inputLayoutA.getEditText();
        editText.setText(gamePO.getaScore());
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    inputLayoutA.setErrorEnabled(true);
                    inputLayoutA.setError("分數不得為空值");
                } else if (!NumberUtils.isNumber(s.toString())) {
                    inputLayoutA.setErrorEnabled(true);
                    inputLayoutA.setError("必須為數字");
                } else {

                    inputLayoutA.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        inputLayoutB = (TextInputLayout) this.findViewById(R.id.textInputB);
        inputLayoutB.setHint(gamePO.getbName() + "分數");

        final EditText editTextB = inputLayoutB.getEditText();
        editTextB.setText(gamePO.getbScore());
        editTextB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    inputLayoutB.setErrorEnabled(true);
                    inputLayoutB.setError("分數不得為空值");
                } else if (!NumberUtils.isNumber(s.toString())) {
                    inputLayoutB.setErrorEnabled(true);
                    inputLayoutB.setError("必須為數字");
                } else {

                    inputLayoutB.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        this.gameCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                gamePO.setStatus(isChecked ? "Y" : "");
            }
        });


        this.modify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameDAO dao = new GameDAO(ModifyScoreActivity.this);

                gamePO.setaScore(inputLayoutA.getEditText().getText().toString());
                gamePO.setbScore(inputLayoutB.getEditText().getText().toString());

                dao.update(gamePO);
                finish();
            }
        });
    }
}
