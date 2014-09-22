package arabiannight.tistory.com.activitypopup;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setLayout();
	}
	
	@Override
	public void onClick(View v) {
		startActivity(new Intent(this, PopupActivity.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	
	/* 
	 * Layout 
	 */
	private Button mButton;
	
	private void setLayout(){
		mButton = (Button) findViewById(R.id.button1);
		mButton.setOnClickListener(this);
	}

}
