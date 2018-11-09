package com.example.compus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;


//import org.json.JSONArray;
//import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bean.News;
import com.example.bean.Resource;
import com.example.bean.Students;
import com.example.bean.Teachers;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class MainActivity extends Activity {
	 private Button button1;
	 private EditText editText1;
	 private EditText editText2;
	 private RadioGroup linearLayout2;
	 private String http_url="http://192.168.31.202:8080/theMobileCampusSystem/";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		        .detectDiskReads()
		        .detectDiskWrites()
		        .detectNetwork()   // or .detectAll() for all detectable problems
		        .penaltyLog()
		        .build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
		        .detectLeakedSqlLiteObjects()
		        .detectLeakedClosableObjects()
		        .penaltyLog()
		        .penaltyDeath()
		        .build());
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.index);
		
		button1 = (Button)findViewById(R.id.button1);
		editText1 = (EditText)findViewById(R.id.editText1);
		editText2 = (EditText)findViewById(R.id.editText2);
		linearLayout2 = (RadioGroup)findViewById(R.id.linearLayout2);                                                                                                                                                                                                                                                                                                                                                                                                                                                                 

		button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,password,job = "";

                name = editText1.getText().toString();
                password = editText2.getText().toString();

                //遍历RadioGroup找出被选中的单选按钮
                for(int i = 0;i < linearLayout2.getChildCount();i++)
                {
                    RadioButton rd = (RadioButton)linearLayout2.getChildAt(i);
                    if(rd.isChecked())
                    {
                        if(rd.getText().toString().equals("学生"))
                        	job="student";
                        else if(rd.getText().toString().equals("教师"))
                        	job="teacher";
                        break;
                    }
                }
                
                login(name,password,job);

            }
        });
	}
	private boolean login(String id, String password,String job)  { 
		
		HttpClient client = new DefaultHttpClient();
	    HttpPost httpPost = new HttpPost(http_url+"loginAndroid.action");
	    List<NameValuePair> params = new ArrayList<NameValuePair>(); 
	    params.add(new BasicNameValuePair("loginname", id));
	    params.add(new BasicNameValuePair("password", password)); 
	    params.add(new BasicNameValuePair("usertype", job)); 
	    try {

            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse httpResponse = client.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity(); 
            
            StringBuilder builder = new StringBuilder();  
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));  
              
            String s = null;  
            while ((s = bufferedReader.readLine()) != null)  
            {  
                builder.append(s);  
            }  
              
            JSONObject json = new JSONObject(builder.toString());

            Intent it = new Intent(MainActivity.this,mian_user.class);

        	//新建Bundle对象,并把数据写入
            Bundle bd = new Bundle();
            bd.putString("job",job);
            
            if(json.getString("success").equals("student"))
            {
            	Students student = new Students();
            	
                JSONObject jsonObject = json.getJSONObject("loginUser");  
                // 将拿到的对象set到一个person对象中  
                student.setSID(jsonObject.getString("SID"));
                student.setName(jsonObject.getString("name"));
                student.setPassword(jsonObject.getString("password"));
                student.setEmail(jsonObject.getString("email"));
                student.setPhone(jsonObject.getString("phone"));
                student.setSex(jsonObject.getString("sex"));
                student.setSclass(jsonObject.getString("Sclass"));
                
                //传递电话数据
                JSONArray phoneObject = JSONArray.fromObject(json.get("phones").toString());
                List<HashMap<String,String>> cophones= (List<HashMap<String,String>>) JSONArray.toCollection(phoneObject,HashMap.class);
                it.putExtra("cophones", (Serializable)cophones);
                
                //传递新闻数据
                JSONArray newsObject = JSONArray.fromObject(json.get("news").toString());
 				List<Map<String,News>> conews= (List<Map<String,News>>) JSONArray.toCollection(newsObject,Map.class);
                it.putExtra("conews", (Serializable)conews);
                
                //传递课程表数据
                JSONArray coursesObject = JSONArray.fromObject(json.get("allCourses").toString());
 				List<Map<String,News>> courses= (List<Map<String,News>>) JSONArray.toCollection(coursesObject,Map.class);
                it.putExtra("courses", (Serializable)courses);
                
                //传递今日课程数据
                JSONArray todaycoursesObject = JSONArray.fromObject(json.get("todayCourses").toString());
 				List<Map<String,News>> todaycourses= (List<Map<String,News>>) JSONArray.toCollection(todaycoursesObject,Map.class);
                it.putExtra("todaycourses", (Serializable)todaycourses);
                
                //传递共享资源数据
                JSONArray resourceObject = JSONArray.fromObject(json.get("resource").toString());
 				List<Map<String,Resource>> resource= (List<Map<String,Resource>>) JSONArray.toCollection(resourceObject,Map.class);
                it.putExtra("resource", (Serializable)resource);
                
                //传递登录用户数据
                it.putExtra("loginUser",new Gson().toJson(student));
                it.putExtras(bd);
                startActivity(it);//账号密码正确，跳转到主界面 
                finish();
                return true;
            }
            else if(json.getString("success").equals("teacher"))
            {
            	Teachers teacher = new Teachers();
                JSONObject jsonObject = json.getJSONObject("loginUser");  
                
                teacher.setTID(jsonObject.getString("TID"));
                teacher.setName(jsonObject.getString("name"));
                teacher.setPassword(jsonObject.getString("password"));
                teacher.setEmail(jsonObject.getString("email"));
                teacher.setPhone(jsonObject.getString("phone"));
                teacher.setSex(jsonObject.getString("sex"));
                teacher.setAffiliation(jsonObject.getString("affiliation"));
                teacher.setTitle(jsonObject.getString("title"));
                
              //传递电话数据
                JSONArray phoneObject = JSONArray.fromObject(json.get("phones").toString());
                List<HashMap<String,String>> cophones= (List<HashMap<String,String>>) JSONArray.toCollection(phoneObject,HashMap.class);
                it.putExtra("cophones", (Serializable)cophones);
                
                //传递新闻数据
                JSONArray newsObject = JSONArray.fromObject(json.get("news").toString());
 				List<Map<String,News>> conews= (List<Map<String,News>>) JSONArray.toCollection(newsObject,Map.class);
                it.putExtra("conews", (Serializable)conews);
                
                //传递课程表数据
                JSONArray coursesObject = JSONArray.fromObject(json.get("allCourses").toString());
 				List<Map<String,News>> courses= (List<Map<String,News>>) JSONArray.toCollection(coursesObject,Map.class);
                it.putExtra("courses", (Serializable)courses);
                
              //传递共享资源数据
                JSONArray resourceObject = JSONArray.fromObject(json.get("resource").toString());
 				List<Map<String,Resource>> resource= (List<Map<String,Resource>>) JSONArray.toCollection(resourceObject,Map.class);
                it.putExtra("resource", (Serializable)resource);
                
                //传递今日课程数据
                JSONArray todaycoursesObject = JSONArray.fromObject(json.get("todayCourses").toString());
 				List<Map<String,News>> todaycourses= (List<Map<String,News>>) JSONArray.toCollection(todaycoursesObject,Map.class);
                it.putExtra("todaycourses", (Serializable)todaycourses);
                
                it.putExtra("loginUser",new Gson().toJson(teacher));
                it.putExtras(bd);
                startActivity(it);//账号密码正确，跳转到主界面 
                finish();
                return true;
            }  
            else if(json.getString("success").equals("failed"))
            {
                Toast toast = Toast.makeText(MainActivity.this, "用户名或密码错误！",Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }  
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
		return false;	   
    }

}
