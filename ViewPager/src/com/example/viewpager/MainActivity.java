package com.example.viewpager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {
	
	private int mPrevPosition;				//이전에 선택되었던 포지션 값
	private ViewPager mPager;				//뷰 페이저
	private LinearLayout mPageMark;			//현재 몇 페이지 
	
	private ImageAdapter img = null;
	//아이템의 배경화면 색상. 아이템을 구분하기 위해.
	private int[] mColorArray = {Color.YELLOW, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GRAY,
										Color.GREEN, Color.LTGRAY, Color.MAGENTA, Color.RED, Color.WHITE};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        mPageMark = (LinearLayout)findViewById(R.id.page_mark);			//상단의 현재 페이지 나타내는 뷰

		mPager = (ViewPager)findViewById(R.id.view_pager);						//뷰 페이저
		mPager.setAdapter(new BkPagerAdapter(getApplicationContext()));//PagerAdapter로 설정
		mPager.setOnPageChangeListener(new OnPageChangeListener() {	//아이템이 변경되면, gallery나 listview의 onItemSelectedListener와 비슷
			//아이템이 선택이 되었으면
			@Override public void onPageSelected(int position) {
				mPageMark.getChildAt(mPrevPosition).setBackgroundResource(R.drawable.page_not);	//이전 페이지에 해당하는 페이지 표시 이미지 변경
				mPageMark.getChildAt(position).setBackgroundResource(R.drawable.page_select);		//현재 페이지에 해당하는 페이지 표시 이미지 변경
				mPrevPosition = position;				//이전 포지션 값을 현재로 변경
			}
			@Override public void onPageScrolled(int position, float positionOffest, int positionOffsetPixels) {}
			@Override public void onPageScrollStateChanged(int state) {}
		});

		initPageMark();	//현재 페이지 표시하는 뷰 초기화
        
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);
    }
 
    //상단의 현재 페이지 표시하는 뷰 초기화
  	private void initPageMark(){
  		
  		
  		for(int i=0; i< 3 ; i++)
  		{
  			ImageView iv = new ImageView(getApplicationContext());	//페이지 표시 이미지 뷰 생성
  			iv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

  			//첫 페이지 표시 이미지 이면 선택된 이미지로
  			if(i==0)
  				iv.setBackgroundResource(R.drawable.page_select);
  			else	//나머지는 선택안된 이미지로
  				iv.setBackgroundResource(R.drawable.page_not);

  			//LinearLayout에 추가
  			mPageMark.addView(iv);
  		}
  	}

    //Pager 아답터 구현
  	private class BkPagerAdapter extends PagerAdapter{
  		private Context mContext;
  		public BkPagerAdapter( Context con) { super(); mContext = con; }

  		@Override public int getCount() { return 3; }	//여기서는 3개만 할 것이다.

  		//뷰페이저에서 사용할 뷰객체 생성/등록
  		@Override public Object instantiateItem(View pager, int position) {
  			
  			TextView tv = new TextView(mContext);					//텍스트뷰
  			tv.setBackgroundColor(mColorArray[position]);			//배경색 지정
  			tv.setText("ViewPager Item"+(position+1));				//글자지정
  			tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);		//글자 크기 24sp
 			tv.setTextColor(mColorArray[mColorArray.length - (position+1)]);	//글자 색상은 배경과 다른 색으로
  			
  			((ViewPager)pager).addView(tv, 0);		//뷰 페이저에 추가

  			return tv; 
  		}

  		//뷰 객체 삭제.
  		@Override public void destroyItem(View pager, int position, Object view) {
  			((ViewPager)pager).removeView((View)view);
  		}

  		// instantiateItem메소드에서 생성한 객체를 이용할 것인지
  		@Override public boolean isViewFromObject(View view, Object obj) { return view == obj; }

  		@Override public void finishUpdate(View arg0) {}
  		@Override public void restoreState(Parcelable arg0, ClassLoader arg1) {}
  		@Override public Parcelable saveState() { return null; }
  		@Override public void startUpdate(View arg0) {}
  	}
}