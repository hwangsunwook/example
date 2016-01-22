package com.example.hwang.popupcount;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PopupActivity extends AppCompatActivity {

    private CustomDialog mCustomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomDialog = new CustomDialog(PopupActivity.this,
                        "정답을 입력 하세요",
                        "5",
                        leftClickListener,
                        rightClickListener);
                mCustomDialog.show();
            }
        });

    }

/*

    public void onClickView(View v){
        switch (v.getId()) {
            case R.id.button:

                break;
        }
    }
*/

    private View.OnClickListener leftClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Toast.makeText(getApplicationContext(), "왼쪽버튼 Click!!",
					Toast.LENGTH_SHORT).show();
		}
	};

	private View.OnClickListener rightClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_right:
                    Toast.makeText(getApplicationContext(), "오른쪽버튼 Click!!",
                        Toast.LENGTH_SHORT).show();
                    mCustomDialog.dismiss();
                    break;
                default:
                    mCustomDialog.dismiss();
                    break;
            }

		}
	};
}
