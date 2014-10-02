package com.example.login;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

public class pcom extends Application {

	private String companyMapState;
	public HashMap<String,String> authHM = new HashMap<String, String>();

	public String getCompanyMapState() {
		return companyMapState;
	}

	public void setCompanyMapState(String s) {
		companyMapState = s;
	}
	
	public ProgressDialog loadingDialog;

	public void startLoading(Context ctx) {
		loadingDialog = ProgressDialog.show(ctx, "Loading...", "Please wait...",
				false, true);
		Log.v("pcom", "startLoading" + ctx.toString());
	}

	public void endLoading() {
		Log.v("pcom", "endLoading");
		endLoader endLoader = new endLoader();
		Timer timer = new Timer(false);
		timer.schedule(endLoader, 1000);
	}

	class endLoader extends TimerTask {
		endLoader() {}
		public void run() {
			loadingDialog.dismiss();
		}
	}

	// if (act!=null) ((pcom) act.getApplication()).startLoading(act);
	// if (act!=null) ((pcom) act.getApplication()).endLoading();
}
