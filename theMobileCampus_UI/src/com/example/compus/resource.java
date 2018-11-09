package com.example.compus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.bean.Resource;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class resource extends Fragment{
	private LinearLayout all;
	private String http_url="http://192.168.31.202:8080/theMobileCampusSystem/";
	private Resource filename[]=new Resource[10];
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.resources, container, false); 
    	all=(LinearLayout)view.findViewById(R.id.all);
    	
    	List<Map<String,Resource>> resources=(List<Map<String, Resource>>) getActivity().getIntent().getSerializableExtra("resource");
	    for(int i=0;i<resources.size();i++)
        {
	    	Resource coresource=new Resource();
	    	Map map=(Map)resources.get(i);
	    	coresource.setTID((String) map.get("TID"));
	    	coresource.setDown((Integer) map.get("down"));
	    	coresource.setRname((String) map.get("Rname"));
	    	coresource.setRID((Integer)map.get("RID"));
	    	coresource.setFile((String) map.get("file"));
	    	setResource(coresource,i);
        }
    	return view;
	}
	private void setResource(Resource resource,int i){
		//上传者
		LinearLayout.LayoutParams t1_ln1=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
    	TextView t1=new TextView(getActivity());
    	t1.setTextSize(12);
    	t1.setText("上传者："+resource.getTID());
    	t1.setTextColor(Color.parseColor("#727d8c"));
    	//下载量
    	LinearLayout.LayoutParams t2_ln1=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
    	TextView t2=new TextView(getActivity());
    	t2_ln1.setMargins(DensityUtil.dip2px(getActivity(),15), 0, 0, 0);
    	t2.setTextSize(12);
    	t2.setText("下载量："+resource.getDown());
    	t2.setTextColor(Color.parseColor("#727d8c"));
    	//上传+下载布局
    	LinearLayout.LayoutParams ln1_ln2=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    	LinearLayout ln1=new LinearLayout(getActivity());
    	ln1.setOrientation(0);
    	ln1_ln2.setMargins(0, DensityUtil.dip2px(getActivity(),8), 0, 0);
    	ln1.addView(t1, t1_ln1);
    	ln1.addView(t2, t2_ln1);
    	//文件名
    	LinearLayout.LayoutParams t3_ln2=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
    	TextView t3=new TextView(getActivity());
    	t3.setTextSize(16);
    	t3.setText(resource.getRname());
    	t3.setTextColor(Color.parseColor("#000000"));
    	//文件名+布局1
    	LinearLayout.LayoutParams ln2_big=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    	LinearLayout ln2=new LinearLayout(getActivity());
    	ln2.setOrientation(1);
    	ln2_big.setMargins(DensityUtil.dip2px(getActivity(),20),0 , 0, 0);
    	ln2.addView(t3, t3_ln2);
    	ln2.addView(ln1, ln1_ln2);
    	//文件图标
    	LinearLayout.LayoutParams i1_big=new LinearLayout.LayoutParams(DensityUtil.dip2px(getActivity(),40),DensityUtil.dip2px(getActivity(),40));
    	ImageView i1=new ImageView(getActivity());
    	i1.setBackgroundColor(Color.parseColor("#ffffff"));
    	i1.setBackgroundResource(R.drawable.txt);
    	i1.setScaleType(ScaleType.FIT_CENTER);
    	//文件图标+布局2
    	LinearLayout.LayoutParams big_all=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    	LinearLayout big=new LinearLayout(getActivity());
    	big.setOrientation(0);
    	big.setPadding(DensityUtil.dip2px(getActivity(),10),DensityUtil.dip2px(getActivity(),10) , 0, 0);
    	big.addView(i1, i1_big);
    	big.addView(ln2, ln2_big);
    	all.addView(big, big_all);
    	
    	big.setTag(i); 
    	filename[i]=resource;
    	big.setOnClickListener(new OnClickListener() { 
	    	@Override
        	public void onClick(View v) {
	    		final int tag=(Integer) v.getTag();
	    		new AlertDialog.Builder(getActivity())
	    		.setIcon(R.drawable.down)
	    		.setTitle("下载")
	    		.setMessage("是否下载该文件？")
	    		.setPositiveButton("确定",new DialogInterface.OnClickListener(){
	    			 public void onClick(DialogInterface dialog, int which){
	    				    String urlStr=http_url+"resource/"+filename[tag].getFile();  
	    		            String path="file";  
	    		            String fileName=filename[tag].getFile();  
	    		            OutputStream output=null;
	    		            try { 
	    		            	URL url=new URL(urlStr);  
	    		                HttpURLConnection conn=(HttpURLConnection)url.openConnection();
	    		                String SDCard=Environment.getExternalStorageDirectory()+"";  
	    		                String pathName=SDCard+"/"+path+"/"+fileName;//文件存储路径  
	    		                  
	    		                File file=new File(pathName);  
	    		                InputStream input=conn.getInputStream();  
	    		                if(file.exists()){  
	    		                	Toast toast = Toast.makeText(getActivity(),"文件已存在！",Toast.LENGTH_SHORT);                  
		    		                toast.show();   
		    		                return;
	    		                }
	    		                else{  
	    		                    String dir=SDCard+"/"+path;  
	    		                    new File(dir).mkdir();//新建文件夹  
	    		                    file.createNewFile();//新建文件  
	    		                    output=new FileOutputStream(file);  
	    		                    //读取大文件  
	    		                    byte[] buffer=new byte[4*1024];  
	    		                    while(input.read(buffer)!=-1){  
	    		                        output.write(buffer);  
	    		                    }  
	    		                    output.flush();
	    		                    HttpClient client = new DefaultHttpClient();
	    		            	    HttpPost httpPost = new HttpPost(http_url+"downLoad?RID="+filename[tag].getRID());
	    		            	    HttpResponse httpResponse = client.execute(httpPost);
	    		                    Toast toast = Toast.makeText(getActivity(),"下载完成，请去本地文件夹查看！",Toast.LENGTH_SHORT);                  
		    		                toast.show();
		    		                output.close(); 
	    		                }    
	    		            } 
	    		            catch (Exception e) {  
	    		                e.printStackTrace();  
	    		            } 
	                    }
	    		})
	    		.setNeutralButton("取消", new DialogInterface.OnClickListener(){
	    			public void onClick(DialogInterface dialog, int which)
                    {
                        
                    }
	    		})
	    		.show();
	    	}
    	});
    	//分割线
    	LinearLayout.LayoutParams line_all=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,DensityUtil.dip2px(getActivity(),1));
    	View line=new View(getActivity());
    	line_all.setMargins(0, DensityUtil.dip2px(getActivity(),13),0,0);
    	line.setBackgroundColor(Color.parseColor("#b5becd"));
    	all.addView(line, line_all);     
	}
	

}
