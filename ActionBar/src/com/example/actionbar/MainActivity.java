package com.example.actionbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu , menu);
	    return true;
	}

	public void onGroupItemClick(MenuItem item) {
	    // One of the group items (using the onClick attribute) was clicked
	    // The item parameter passed here indicates which item it is
	    // All other menu item clicks are handled by onOptionsItemSelected()
		 if (item.isChecked()) {
	         item.setChecked(false); 
	     } else {
	         item.setChecked(true);
	     }  	 
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{    
	    Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
	    return true;    
	}    
}
