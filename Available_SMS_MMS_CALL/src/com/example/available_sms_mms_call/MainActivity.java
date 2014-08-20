package com.example.available_sms_mms_call;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	Uri uri;
	Intent intent;
	String attached_uri = "";
	String phonenumber = "01000000000";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        Button button1 = (Button) findViewById(R.id.button1);         
        button1.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
            	if(aviliableSMS(getApplicationContext())){
            	    uri = Uri.parse("sms:" + "01000000000");
            	    intent = new Intent(Intent.ACTION_SENDTO, uri);
            	    startActivity(intent);
            	}else{
            	    Toast.makeText(getApplicationContext(),"SMS 서비스를 이용할 수 없는 단말 입니다.", Toast.LENGTH_SHORT).show();
            	}
			}
        });
		
        Button button2 = (Button) findViewById(R.id.button2);         
        button2.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(aviliableMMS(getApplicationContext())){	
										
					//Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + resources.getResourcePackageName(R.drawable.flowser) + '/' + resources.getResourceTypeName(R.drawable.flowser) + '/' + resources.getResourceEntryName(R.drawable.flowser) );
			        Uri uri = Uri.parse("");//"android.resource://" + getPackageName() + "/" + R.drawable.flowser);
			        Intent it = new Intent(Intent.ACTION_SEND);   
			        it.putExtra("sms_body", "some text");   
			        it.putExtra(Intent.EXTRA_STREAM, uri);   
			        it.setType("image/*");
			        // 삼성 단말에서만 허용 ( 앱 선택 박스 없이 호출 )
//			      it.setComponent(new ComponentName("com.sec.mms", "com.sec.mms.Mms"));
			        startActivity(it);
			    }
			    else{
			        Toast.makeText(getApplicationContext(),"MMS 서비스를 이용할 수 없는 단말 입니다.", Toast.LENGTH_SHORT).show();
			    }			
			}
        });
        
        Button button3 = (Button) findViewById(R.id.button3);         
        button3.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(aviliableCALL(getApplicationContext())){
			        //uri = Uri.parse("tel:" + "01000000000");
			        //intent = new Intent(Intent.ACTION_CALL, uri);
			        //startActivity(intent);
			        Intent intentcall = new Intent();
			        intentcall.setAction(Intent.ACTION_CALL);
			        intentcall.setData(Uri.parse("tel:" + phonenumber)); // set the Uri
			        startActivity(intentcall);
			    }else{
			        Toast.makeText(getApplicationContext(),"전화 서비스를 이용할 수 없는 단말 입니다.",Toast.LENGTH_SHORT).show();
			    }
			}
        });
        
        
        Button button4 = (Button) findViewById(R.id.button4);         
        button4.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
				
				// 전송할 파일의 경로
				//String szSendFilePath = "android.resource//"+getPackageName()+"/raw/sample/flowser.png";
				//String szSendFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/flowser.png";
				//Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.flowser);
				String szSendFilePath = "android.resource://" + getPackageName() + "/" + R.drawable.flowser;
				
				File f = new File(szSendFilePath);
				if (!f.exists()) {
					Toast.makeText(getApplicationContext(), "파일이 없습니다.", Toast.LENGTH_SHORT).show();
				}

				// File객체로부터 Uri값 생성
				final Uri fileUri = Uri.fromFile(f);
				
				Intent it = new Intent(Intent.ACTION_SEND);
				it.setType("text/plain");

				// 수신인 주소 - tos배열의 값을 늘릴 경우 다수의 수신자에게 발송됨
				String[] tos = { "test@gmail.com" };
				it.putExtra(Intent.EXTRA_EMAIL, tos);

				it.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
				it.putExtra(Intent.EXTRA_TEXT, "The email body text");

				// 파일첨부
				it.putExtra(Intent.EXTRA_STREAM, fileUri);

				startActivity(it);
			}
        });
	}
	
	/**
     * SMS
     */
	
	public static boolean aviliableSMS(Context context) {
         
        PackageManager pac = context.getPackageManager();
 
        Uri smsUri = Uri.parse("sms:"); 
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, smsUri);
 
        List<ResolveInfo> list = pac.queryIntentActivities
                (smsIntent, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);    
        ArrayList<ActivityInfo> tempList = new ArrayList<ActivityInfo>();
         
        int count = list.size();
        String packageName = "";
         
        for(int i = 0; i < count; i++)  
        {
            ResolveInfo firstInfo = list.get(i);
            packageName = firstInfo.activityInfo.applicationInfo.packageName;
            tempList.add(list.get(i).activityInfo);
 
            Log.d("packageName", "packageName = " + packageName);
        }
         
        if(packageName == null || packageName.equals("")){
            return false;
        }else{
            return true;
        }
    }
     
    /**
     * MMS
     */
    public static boolean aviliableMMS(Context context) {
         
        PackageManager pac = context.getPackageManager();
 
        Uri mmsUri = Uri.parse("mmsto:");
        Intent mmsIntent = new Intent(Intent.ACTION_VIEW, mmsUri); 
 
        List<ResolveInfo> list = pac.queryIntentActivities
                (mmsIntent, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);    
        ArrayList<ActivityInfo> tempList = new ArrayList<ActivityInfo>();
         
        int count = list.size();
        String packageName = "";
         
        for(int i = 0; i < count; i++)  
        {
            ResolveInfo firstInfo = list.get(i);
            packageName = firstInfo.activityInfo.applicationInfo.packageName;
            tempList.add(list.get(i).activityInfo);
 
            Log.d("packageName", "packageName = " + packageName);
        }
         
        if(packageName == null || packageName.equals("")){
            return false;
        }else{
            return true;
        }
    }
     
    /**
     * CALL
     */
    public static boolean aviliableCALL(Context context) {
         
        PackageManager pac = context.getPackageManager();
 
        Uri callUri = Uri.parse("tel:");
        Intent callIntent = new Intent(Intent.ACTION_CALL, callUri);
 
        List<ResolveInfo> list = pac.queryIntentActivities
                (callIntent, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);   
        ArrayList<ActivityInfo> tempList = new ArrayList<ActivityInfo>();
         
        int count = list.size();
        String packageName = "";
         
        for(int i = 0; i < count; i++)  
        {
            ResolveInfo firstInfo = list.get(i);
            packageName = firstInfo.activityInfo.applicationInfo.packageName;
            tempList.add(list.get(i).activityInfo);
 
            Log.d("packageName", "packageName = " + packageName);
        }
         
        if(packageName == null || packageName.equals("")){
            return false;
        }else{
            return true;
        }
    }
    
    
    ///
    /* Email
    private void SendEmail(String subject, String text, ArrayList<String>filePaths, String... addressTo) {
		Intent sendIntent = new Intent(Intent.ACTION_SEND);
		sendIntent.setType("image/jpeg");
		sendIntent.putExtra(Intent.EXTRA_SUBJECT, "subject");
		sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+filePaths.get(0)));
		sendIntent.putExtra(Intent.EXTRA_TEXT, "Enjoy the photo");
		startActivity(Intent.createChooser(sendIntent, "Email:"));
	}
	*/
    ///
    /* MMS
    private void SendMMS(Context context, ArrayList<String> urlString) {
		boolean exceptionCheck = false;


		Intent sendIntent = new Intent();

		// Selection count
		if (urlString.size() > 1) {
			sendIntent.setAction(Intent.ACTION_SEND_MULTIPLE);

		} else if (urlString.size() == 1) {
			sendIntent.setAction(Intent.ACTION_SEND);
		} else {
			Toast.makeText(this, "Please Check the Image.",                                                     Toast.LENGTH_LONG)
					.show();
			exceptionCheck = true;
		}

		if (!exceptionCheck) {
			sendIntent.setData(Uri.parse("mmsto:"));
			sendIntent.addCategory("android.intent.category.DEFAULT");

			ArrayList<Uri> uris = new ArrayList<Uri>();

			for (String file : urlString) {
				File fileIn = new File("file://" + file);
				Uri u = Uri.fromFile(fileIn);
				uris.add(u);
			}
			sendIntent.setType("image/jpeg");

			if (urlString.size() > 1) {
				sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,
						uris);

			} else {
				sendIntent.putExtra(Intent.EXTRA_STREAM,
						Uri.parse("file://" + urlString.get(0)));
			}

		}

		try {
			startActivity(sendIntent);

		} catch (Exception e) {
			Toast.makeText(this, "Send Failed..", Toast.LENGTH_LONG).show();

		}
     } 
    */
    ///
}
