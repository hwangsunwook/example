package com.example.sunwook.hanguljamo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class HangulJamoActivity extends AppCompatActivity {

    String Str = null;
    String rCho[] = { "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ","ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ" };
    String rJung[] = {"ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ", "ㅙ", "ㅚ", "ㅛ", "ㅜ","ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"};
    String rJong[] = {"", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ","ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ","ㅍ", "ㅎ"};
    String HangulJamo = null;
    String TAG = "Hangul";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangul_jamo);

        Log.d(TAG, "Start onCreate");

        EditText TouchText = (EditText)findViewById(R.id.editText);
        Str = TouchText.getText().toString();
        TouchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged" );
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable s) {

                Log.d(TAG, "afterTextChanged");

                EditText editText = (EditText)findViewById(R.id.editText);
                TextView tv_result = (TextView)findViewById(R.id.tvReult);

                Str = editText.getText().toString();

                if(Str.length() > 0) {

                    int i = 0;
                    int Count = Str.length();
                    String[] words = new String[Count];

                    HangulJamo = "";
                    for (i = 0; i < Count; i++) {
                        words[i] = Str.substring(i , i + 1);
                        HangulJamo = HangulJamo + HangulJamoFuc(words[i], 0);
                        tv_result.setText(HangulJamo);
                    }

                } else if (Str.length() == 0) {
                    tv_result.setText("");
                }


            }
        });
    }

    public String HangulJamoFuc(String jamo, int Pos) {

        String jamo_result = "";

        int cho;
        int jung;
        int jong;
        int nTmp;

        Log.d(TAG, "jamo = " + jamo);
        Log.d(TAG, "Pos = " + Pos);

        nTmp = jamo.charAt(Pos) - 0xAC00;
        jong = nTmp % 28; // 종성
        jung = ((nTmp - jong) / 28) % 21; // 중성
        cho = (((nTmp - jong) / 28) - jung) / 21; // 초성

        if (cho < 0) {
            return jamo;
        } else {
            jamo_result = rCho[cho];
            if (jung >= 0) {
                jamo_result = rCho[cho] + rJung[jung];
            }
            if (jong >= 0) {
                jamo_result = rCho[cho] + rJung[jung] + rJong[jong];
            }
            Log.d(TAG, "jamo_result" + jamo_result);
            return jamo_result;
        }
    }
}
