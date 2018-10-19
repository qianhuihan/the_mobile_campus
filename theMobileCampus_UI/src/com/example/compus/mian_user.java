package com.example.compus;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;

public class mian_user extends FragmentActivity {
    
    private static final int RB_NEW = 0;  
    private static final int RB_UPLOAD = 1;  
    private static final int RB_FRESH = 2;  
    private static final int RB_SORT = 3;  
    private static final int RB_TRANSFER = 4;  
    private RadioButton[] rbs = new RadioButton[5]; 
    private Fragment fragment1,fragment2,fragment3,fragment4,fragment5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mainmenu);
        
        rbs[0] = (RadioButton) this.findViewById(R.id.rbNew);  
        rbs[1] = (RadioButton) this.findViewById(R.id.rbUpload);  
        rbs[2] = (RadioButton) this.findViewById(R.id.rbFresh);  
        rbs[3] = (RadioButton) this.findViewById(R.id.rbSort);  
        rbs[4] = (RadioButton) this.findViewById(R.id.rbTransfer);  

        for(int i=0; i<rbs.length; i++) {  
            rbs[i].setOnClickListener(new RBOnClickListener());  
        }
        resetImg();
        rbs[0].setTextColor(Color.parseColor("#5e8fe1"));
        Drawable drawable1 = getResources().getDrawable(R.drawable.p11);
        drawable1.setBounds(0, 0,DensityUtil.dip2px(this,35), DensityUtil.dip2px(this,35));
        rbs[0].setCompoundDrawables(null, drawable1, null, null); 
        
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();  
    	transaction.add(R.id.fragment_container,getItem(RB_NEW));
    	transaction.commit();
    }
    class RBOnClickListener implements View.OnClickListener {  
    	  
        @Override  
        public void onClick(View v) {
        	FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
        	 hideAllFragment(transaction1);
            switch (v.getId()) {  
                case R.id.rbNew:
                	if(fragment1== null){
                		transaction1.add(R.id.fragment_container,getItem(RB_NEW));
                	}
                	else{
                		//transaction1.show(fragment1);
                		transaction1.replace(R.id.fragment_container,getItem(RB_NEW));
                    }
                    resetImg();
                    rbs[0].setTextColor(Color.parseColor("#5e8fe1"));
                    Drawable drawable1 = getResources().getDrawable(R.drawable.p11);
                    drawable1.setBounds(0, 0,DensityUtil.dip2px(mian_user.this,35), DensityUtil.dip2px(mian_user.this,35));
                    rbs[0].setCompoundDrawables(null, drawable1, null, null);
                    break;  
                case R.id.rbUpload: 
                	if(fragment2== null){
                		transaction1.add(R.id.fragment_container,getItem(RB_UPLOAD));
                	}
                	else{
                		transaction1.replace(R.id.fragment_container,getItem(RB_UPLOAD));
                    }
                    resetImg();
                    rbs[1].setTextColor(Color.parseColor("#5e8fe1"));
                    drawable1 = getResources().getDrawable(R.drawable.p12);
                    drawable1.setBounds(0, 0,DensityUtil.dip2px(mian_user.this,35), DensityUtil.dip2px(mian_user.this,35));
                    rbs[1].setCompoundDrawables(null, drawable1, null, null);
                    break;  
                case R.id.rbFresh: 
                	if(fragment3== null){
                		transaction1.add(R.id.fragment_container,getItem(RB_FRESH));
                	}
                	else{
                		transaction1.replace(R.id.fragment_container,getItem(RB_FRESH));
                    }
                    resetImg();
                    rbs[2].setTextColor(Color.parseColor("#5e8fe1"));
                    drawable1 = getResources().getDrawable(R.drawable.p13);
                    drawable1.setBounds(0, 0,DensityUtil.dip2px(mian_user.this,35), DensityUtil.dip2px(mian_user.this,35));
                    rbs[2].setCompoundDrawables(null, drawable1, null, null);
                    break;  
                case R.id.rbSort:
                	if(fragment4== null){
                		transaction1.add(R.id.fragment_container,getItem(RB_SORT));
                	}
                	else{
                		transaction1.add(R.id.fragment_container,getItem(RB_SORT));
                    }
                    resetImg();
                    rbs[3].setTextColor(Color.parseColor("#5e8fe1"));
                    drawable1 = getResources().getDrawable(R.drawable.p14);
                    drawable1.setBounds(0, 0,DensityUtil.dip2px(mian_user.this,35), DensityUtil.dip2px(mian_user.this,35));
                    rbs[3].setCompoundDrawables(null, drawable1, null, null);
                    break;  
                case R.id.rbTransfer:  
                	if(fragment5== null){
                		transaction1.add(R.id.fragment_container,getItem(RB_TRANSFER));
                	}
                	else{
                		transaction1.replace(R.id.fragment_container,getItem(RB_TRANSFER));
                    }
                    resetImg();
                    rbs[4].setTextColor(Color.parseColor("#5e8fe1"));
                    drawable1 = getResources().getDrawable(R.drawable.p15);
                    drawable1.setBounds(0, 0,DensityUtil.dip2px(mian_user.this,35), DensityUtil.dip2px(mian_user.this,35));
                    rbs[4].setCompoundDrawables(null, drawable1, null, null);
                    break;  
            } 
            transaction1.commit();
        }
	
    }  
    private void resetImg() {
    	rbs[0].setTextColor(Color.parseColor("#a2b0c7"));
        Drawable drawable1 = getResources().getDrawable(R.drawable.p1);
        drawable1.setBounds(0, 0,DensityUtil.dip2px(mian_user.this,35), DensityUtil.dip2px(mian_user.this,35));
        rbs[0].setCompoundDrawables(null, drawable1, null, null);
        
        rbs[1].setTextColor(Color.parseColor("#a2b0c7"));
        drawable1 = getResources().getDrawable(R.drawable.p2);
        drawable1.setBounds(0, 0, DensityUtil.dip2px(mian_user.this,35), DensityUtil.dip2px(mian_user.this,35));
        rbs[1].setCompoundDrawables(null, drawable1, null, null);
        
        rbs[2].setTextColor(Color.parseColor("#a2b0c7"));
        drawable1 = getResources().getDrawable(R.drawable.p3);
        drawable1.setBounds(0, 0, DensityUtil.dip2px(mian_user.this,35), DensityUtil.dip2px(mian_user.this,35));
        rbs[2].setCompoundDrawables(null, drawable1, null, null);
        
        rbs[3].setTextColor(Color.parseColor("#a2b0c7"));
        drawable1 = getResources().getDrawable(R.drawable.p4);
        drawable1.setBounds(0, 0, DensityUtil.dip2px(mian_user.this,35), DensityUtil.dip2px(mian_user.this,35));
        rbs[3].setCompoundDrawables(null, drawable1, null, null);
        
        rbs[4].setTextColor(Color.parseColor("#a2b0c7"));
        drawable1 = getResources().getDrawable(R.drawable.p5);
        drawable1.setBounds(0, 0, DensityUtil.dip2px(mian_user.this,35), DensityUtil.dip2px(mian_user.this,35));
        rbs[4].setCompoundDrawables(null, drawable1, null, null);	
	}
    public Fragment getItem(int i) {  
        switch (i) {  
            case RB_NEW:  
                fragment1 = new NewFragment();  
                return fragment1;  
            case RB_UPLOAD:  
                fragment2 = new send_message();  
                return fragment2;  
            case RB_FRESH:  
                fragment3 = new class_fragment();  
                return fragment3;  
            case RB_SORT:  
                fragment4 = new resource();   
                return fragment4;  
            case RB_TRANSFER:  
                fragment5 = new myInformation(); 
                return fragment5;  
        }
		return null;  
    } 
    
  //Òþ²ØËùÓÐFragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fragment1!= null)fragmentTransaction.hide(fragment1);
        if(fragment2!= null)fragmentTransaction.hide(fragment2);
        if(fragment3!= null)fragmentTransaction.hide(fragment3);
        if(fragment4!= null)fragmentTransaction.hide(fragment4);
        if(fragment5!= null)fragmentTransaction.hide(fragment5);        
    }
}
