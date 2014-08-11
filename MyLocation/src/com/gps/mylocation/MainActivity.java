package com.gps.mylocation;

import java.io.IOException;
import java.util.*;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap; 
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.util.*;

public class MainActivity extends Activity {

	// Google Map
	
	private GoogleMap googleMap;
	double latitude = 0L;
	double longitude = 0L;
	// Button event
	Button position_btn, address_btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		try
		{
			initializeMap();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		initialCall();
		
	}
	
	void initialCall()
	{
		position_btn = (Button) findViewById(R.id.position_btn);
		position_btn.setOnClickListener(mOn_Click);
		address_btn = (Button) findViewById(R.id.address_btn);
		address_btn.setOnClickListener(mOn_Click);
	}
	
	View.OnClickListener mOn_Click = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			switch (v.getId())
			{
			case R.id.position_btn :
				// ��ư�� ������ ����, ���� ������ �浵�� �޾Ƽ�, ������ ���
				LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
				Criteria criteria = new Criteria();
				Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria,true));
				
				double lat = location.getLatitude();
				double lng = location.getLongitude();
				goMap(lat,lng);
				
				// address�� ���ؼ� ���� ������ ����
				latitude = lat;
				longitude = lng;				
				
				break;
				
			case R.id.address_btn:
				Toast.makeText(getApplicationContext(), 
						getAddress(latitude,longitude), 
						Toast.LENGTH_SHORT).show();
				
			}
			
			
		}
	};
	
	private void goMap(double latitude, double longitude) 
	{
		googleMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
		CameraUpdate update = CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude) );
		googleMap.moveCamera(update);
		
		CameraUpdate zoom = CameraUpdateFactory.zoomTo(17);
		googleMap.animateCamera(zoom);
		
		MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(latitude,longitude)).title("�ȵ���̵� ���� ��").snippet("���� �̷������.");
		googleMap.addMarker(markerOptions).showInfoWindow();
		
		googleMap.setOnMarkerClickListener(new 
	    OnMarkerClickListener() 
		{
			@Override
			public boolean onMarkerClick(Marker marker) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "�����!!", Toast.LENGTH_SHORT).show();
				return false;
			}
		});		
/*		
		googleMap.setOnMapClickListener(new 
		OnMapClickListener() 
		{
			@Override
			public void onMapClick(LatLng arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "�����!!", Toast.LENGTH_SHORT).show();
			}
		});
*/			
	}
	
	private void initializeMap()
	{
		if (googleMap == null)
		{
			googleMap = ( (MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
			MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude,longitude)).title("Hi!! GoogleMap");
			googleMap.addMarker(marker);
			
			if (googleMap == null)
			{
				Toast.makeText(getApplicationContext(), "Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	private String getAddress(double lat, double lng)
	{
		Geocoder gck = new Geocoder(getApplicationContext(),Locale.getDefault());		
		
		String res = "";
		try
		{
			List<Address> addresses = gck.getFromLocation(lat, lng, 1);
			StringBuilder sb = new StringBuilder();
			
			if (null != addresses && addresses.size() > 0) 
			{
				Address address = addresses.get(0);
				sb.append(address.getLocality()).append(" ");
				sb.append(address.getThoroughfare()).append(" ");
				sb.append(address.getFeatureName());
				res = sb.toString();				
			}			
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return res;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		initializeMap();
		//���� ��ܿ� ��ư�� �����, �� ��ư�� ������ Zoom�ϹǷν�, ���� ��ġ�� ��Բ� �� �ش�.
		googleMap.setMyLocationEnabled(true);
	}
		
}
