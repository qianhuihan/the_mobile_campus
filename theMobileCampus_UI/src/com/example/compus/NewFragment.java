package com.example.compus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.bean.Courses;
import com.example.bean.News;
import com.example.compus.R.drawable;
import com.google.gson.Gson;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.SimpleAdapter;

public class NewFragment extends Fragment {  
    private View view,view3,view2;
	private List<View> mViews;
    private LayoutInflater mInflater;
    private ViewPager mViewPager;
    private LinearLayout item_first,item_second;
    private ImageView mImageCursor;//滚动条的动画。
    private int mCursorWidth;//动画的宽度。
    private int mOffset;//动画图片的偏移量。
    private int currIndex = 0;//当前页码编号
    private MyPagerAdapter myPagerAdapter;
    private Fragment fg;
    private News newsarray[]=new News[50];
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
    	view = inflater.inflate(R.layout.fragment_new, container, false);  
        mViewPager = (ViewPager)view.findViewById(R.id.viewpager_define_title);
        mInflater =getLayoutInflater(savedInstanceState);
        //初始化页面数据。
        mViews = new ArrayList<View>();
        View view1 = mInflater.inflate(R.layout.item_first,null);
        view2 = mInflater.inflate(R.layout.item_second, null);
        view3 = mInflater.inflate(R.layout.item_third, null);
        
        mViews.add(view1);
        mViews.add(view2);
        mViews.add(view3);
        
        initCursorPos();
        myPagerAdapter = new MyPagerAdapter(mViews);
        mViewPager.setAdapter(myPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int one = mOffset * 2 + mCursorWidth;// 页卡1 -> 页卡2 偏移量
            int two = one * 2;// 页卡1 -> 页卡3 偏移量
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            /*
            通过事件的监听，实现动画的切换
             */
            @Override
            public void onPageSelected(int position) {
                Animation animation = null;
                switch (position) {
                    case 0:
                        if (currIndex == 1) {
                            animation = new TranslateAnimation(one, 0, 0, 0);
                        } else if (currIndex == 2) {
                            animation = new TranslateAnimation(two, 0, 0, 0);
                        }
                        break;
                    case 1:
                        if (currIndex == 0) {
                            animation = new TranslateAnimation(mOffset, one, 0, 0);
                        } else if (currIndex == 2) {
                            animation = new TranslateAnimation(two, one, 0, 0);
                        }
                        break;
                    case 2:
                        if (currIndex == 0) {
                            animation = new TranslateAnimation(mOffset, two, 0, 0);
                        } else if (currIndex == 1) {
                            animation = new TranslateAnimation(one, two, 0, 0);
                        }
                        break;
                }
                currIndex = position;
                animation.setFillAfter(true);// True:图片停在动画结束位置
                animation.setDuration(300);
                mImageCursor.startAnimation(animation);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        item_first=(LinearLayout) view1.findViewById(R.id.item_first_linearLayout1);
        setitme_firstLayout();
        
        item_second=(LinearLayout) view2.findViewById(R.id.item_second_linearLayout1);
        setitme_secondLayout();
        
        setitme_thirdLayout();
        return view; 
    }
    
    public void onActivityCreated(Bundle savedInstanceState) {  
        super.onActivityCreated(savedInstanceState);  
        
        //重要通知按钮注册
        ImageButton button1 = (ImageButton) view.findViewById(R.id.fragment_imageButton1);  
        button1.setOnClickListener(new OnClickListener() { 
            public void onClick(View v) {
            	Intent it = new Intent(getActivity(),importantMessage.class);
            	startActivity(it);
            }    
        });  
        
        //查看全部课程表按钮注册
        Button allclass=(Button) view2.findViewById(R.id.all_class);
        allclass.setOnClickListener(new OnClickListener() { 
            public void onClick(View v) {
            	Button b1=(RadioButton) getActivity().findViewById(R.id.rbNew);
            	Button b3=(RadioButton) getActivity().findViewById(R.id.rbFresh);
            	
            	b3.setTextColor(Color.parseColor("#5e8fe1"));
                Drawable drawable1 = getResources().getDrawable(R.drawable.p13);
                drawable1.setBounds(0, 0,DensityUtil.dip2px(getActivity(),35), DensityUtil.dip2px(getActivity(),35));
                b3.setCompoundDrawables(null, drawable1, null, null); 
                
                b1.setTextColor(Color.parseColor("#a2b0c7"));
                Drawable drawable2 = getResources().getDrawable(R.drawable.p1);
                drawable2.setBounds(0, 0,DensityUtil.dip2px(getActivity(),35), DensityUtil.dip2px(getActivity(),35));
                b1.setCompoundDrawables(null, drawable2, null, null);
                
            	FragmentTransaction fs = getFragmentManager().beginTransaction();
            	fg=new class_fragment();
            	fs.replace(R.id.fragment_container,fg);
            	fs.commit();	
            }    
        });  
        
    }
    
    
    /*
    初始化动画的方法
     */
    private void initCursorPos() {
        // 初始化动画
        mImageCursor = (ImageView)view.findViewById(R.id.imageview_cursor);
        mCursorWidth = BitmapFactory.decodeResource(getResources(), R.drawable.image).getWidth();// 获取图片宽度
        //获得屏幕的宽度
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenW = metrics.widthPixels;
        // 计算偏移量
        mOffset = (screenW / mViews.size() - mCursorWidth) / 2;
        // 设置动画初始位置
        Matrix matrix = new Matrix();
        matrix.postTranslate(mOffset, 0);
        mImageCursor.setImageMatrix(matrix);
    }
    
    //添加新闻界面数据
	private void setitme_firstLayout() { 
    	int resID;
    	try{
    		List<Map<String,News>> news=(List<Map<String, News>>) getActivity().getIntent().getSerializableExtra("conews");
    		//List<News> news=(List<News>) getActivity().getIntent().getSerializableExtra("conews");

    	    for(int i=0;i<news.size();i++)
            {
        	    News conew=new News();
    	    	Map map=(Map)news.get(i);
    	    	conew.setDate((String) map.get("date"));
    	    	conew.setBody((String) map.get("body"));
    	    	conew.setEditor((String) map.get("editor"));
    	    	conew.setNID((Integer) map.get("NID"));
    	    	conew.setPicture((String) map.get("picture"));
    	    	conew.setSource((String) map.get("sourse"));
    	    	conew.setTitle((String) map.get("title"));

        		LinearLayout.LayoutParams c_image=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(getActivity(),170));
            	ImageView imageView=new ImageView(getActivity());
            	imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            	
            	Class<drawable> cls = R.drawable.class;
                Integer value = cls.getDeclaredField(conew.getPicture() .substring(0,conew.getPicture().lastIndexOf("."))).getInt(null);
                
            	imageView.setImageResource(value);
            	imageView.setLayoutParams(c_image);
            	
            	//新闻内容
            	LinearLayout.LayoutParams c_title=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            	TextView textView=new TextView(getActivity());
            	textView.setPadding(0, DensityUtil.dip2px(getActivity(),2), 0, 0);
            	textView.setText(conew.getTitle());
            	textView.setTextSize(14);
            	textView.getPaint().setFakeBoldText(true);
            	textView.setLayoutParams(c_title);
            	int color = getActivity().getResources().getColor(R.color.grpp);
            	textView.setTextColor(color);
            	textView.setMaxLines(2);
            	textView.setEllipsize(TruncateAt.MARQUEE);
            	textView.setMarqueeRepeatLimit(2);
            
            	LinearLayout.LayoutParams c_linear=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            	LinearLayout firstLinearLayout=new LinearLayout(getActivity());
            	firstLinearLayout.setLayoutParams(c_linear);
            	firstLinearLayout.setPadding(DensityUtil.dip2px(getActivity(),20),DensityUtil.dip2px(getActivity(),20),DensityUtil.dip2px(getActivity(),20),DensityUtil.dip2px(getActivity(),5));
            	firstLinearLayout.setOrientation(1);
            	firstLinearLayout.addView(imageView);
            	firstLinearLayout.addView(textView);
            	
            	newsarray[i]=conew;
            	firstLinearLayout.setTag(i); 
            	firstLinearLayout.setOnClickListener(new OnClickListener() { 
    		    	@Override
    		        	public void onClick(View v) {
    		        		int i = (Integer) v.getTag();
    		        		News conewarray=newsarray[i];
    		        		Intent it = new Intent(getActivity(),concrete_news.class);
    		                it.putExtra("conews",new Gson().toJson(conewarray));          
    		                startActivity(it);  
    		        		}
    		    	});
            	item_first.addView(firstLinearLayout);
            	}
		    LinearLayout.LayoutParams bottom=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, DensityUtil.dip2px(getActivity(),55));
	    	TextView bott=new TextView(getActivity());
	    	LinearLayout firstLinearLayout=new LinearLayout(getActivity());
	    	firstLinearLayout.addView(bott,bottom);
		    item_first.addView(firstLinearLayout);
    	}
    	catch (Exception e) {
            e.printStackTrace();
        }
        TextView today=(TextView) view2.findViewById(R.id.my_number);
	}
	
	//添加今日课表界面数据
    private void setitme_secondLayout() {  
    	
    	Courses cocourse=new Courses();
    	try{
    		List<Map<String,Courses>> cours=(List<Map<String, Courses>>) getActivity().getIntent().getSerializableExtra("todaycourses");
    	    
    		if(cours.size()==0)
    		{
    			LinearLayout.LayoutParams t_all1=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    	    	t_all1.gravity=Gravity.CENTER;
    	    	TextView textView1=new TextView(getActivity());
    	    	textView1.setTextSize(20);
    	    	textView1.setTextColor(Color.parseColor("#000000"));
    	    	textView1.setGravity(Gravity.CENTER);
    	    	textView1.setText("今日暂无课程！");
    	    	
    	    	LinearLayout.LayoutParams t_all=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    	    	LinearLayout bigLinearLayout=new LinearLayout(getActivity());
    	    	bigLinearLayout.setPadding(DensityUtil.dip2px(getActivity(),20),DensityUtil.dip2px(getActivity(),20),DensityUtil.dip2px(getActivity(),20),DensityUtil.dip2px(getActivity(),20));       
    	    	bigLinearLayout.addView(textView1,t_all1);	
    	    	
    	    	item_second.addView(bigLinearLayout,t_all);
    		} 	
    		for(int i=0;i<cours.size();i++)
            {
    	    	Map map=(Map)cours.get(i);
    	    	cocourse.setCID((String) map.get("CID"));
    	    	cocourse.setCname((String) map.get("Cname"));
    	    	cocourse.setPlace((String) map.get("place"));
    	    	cocourse.setTID((String) map.get("TID"));
    	    	cocourse.setTime((String) map.get("time"));
    	    	cocourse.setWday((String) map.get("wday"));
    	    	
    	    	//课程节数
    	    	LinearLayout.LayoutParams t_all1=new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,1.0f);
    	    	t_all1.gravity=Gravity.CENTER;
    	    	TextView textView1=new TextView(getActivity());
    	    	textView1.setTextSize(20);
    	    	textView1.setTextColor(Color.parseColor("#000000"));
    	    	textView1.setText(cocourse.getTime());
    	    	
    	    	//课程名称
    	    	LinearLayout.LayoutParams t_fl1=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    	    	TextView textView2=new TextView(getActivity());
    	    	textView2.setTextColor(Color.parseColor("#000000"));
    	    	textView2.setTextSize(16);
    	    	textView2.setText(cocourse.getCname());
    	    	
    	    	//课程地点
    	    	LinearLayout.LayoutParams t_fl2=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    	    	t_fl2.setMargins(0, 5, 0, 0);
    	    	TextView textView3=new TextView(getActivity());
    	    	textView3.setTextColor(Color.parseColor("#b5becd"));
    	    	textView3.setTextSize(12);
    	    	textView3.setText(cocourse.getPlace());
    	    	
    	    	LinearLayout.LayoutParams t_all2=new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,3.0f);
    	    	LinearLayout firstLinearLayout=new LinearLayout(getActivity());
    	    	firstLinearLayout.setOrientation(1);
    	    	firstLinearLayout.addView(textView2,t_fl1);
    	    	firstLinearLayout.addView(textView3,t_fl2);
    	    	
    	    	//大布局
    	    	LinearLayout.LayoutParams t_all=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    	    	LinearLayout bigLinearLayout=new LinearLayout(getActivity());
    	    	bigLinearLayout.setOrientation(0);
    	    	bigLinearLayout.setPadding(DensityUtil.dip2px(getActivity(),20),DensityUtil.dip2px(getActivity(),20),DensityUtil.dip2px(getActivity(),20),DensityUtil.dip2px(getActivity(),20));       
    	    	bigLinearLayout.addView(textView1,t_all1);
    	    	bigLinearLayout.addView(firstLinearLayout,t_all2);
    	    	
    	    	item_second.addView(bigLinearLayout,t_all);
    	    	
    	    	LinearLayout.LayoutParams t_all3=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,DensityUtil.dip2px(getActivity(),2));
    	    	View dasnline=new View(getActivity());
    	    	dasnline.setBackgroundResource(drawable.dash_view);
    	    	dasnline.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    	    	item_second.addView(dasnline,t_all3);
    	    	
            }
    	}
    	catch (Exception e) {
            e.printStackTrace();
        }
        TextView today=(TextView) view2.findViewById(R.id.my_number);
        today.setText(cocourse.getWday());
  
    }
    
    //添加常用电话界面数据
    private void setitme_thirdLayout() { 
       	//绑定XML中的ListView，作为Item的容器  
           ListView list = (ListView) view3.findViewById(R.id.phone_MyListView);  
             
           List<HashMap<String, String>> mylist=(List<HashMap<String, String>>) getActivity().getIntent().getSerializableExtra("cophones");
           SimpleAdapter mAdapter = new SimpleAdapter(
        		   getActivity(),
        		   mylist,
        		   R.layout.phone_item,
        		   new String[] { "departmentName", "phone"},  
                   new int[] {R.id.ItemPhonename,R.id.ItemPhonenumber}); 
           list.setAdapter(mAdapter); 	
    }
    
    
}
