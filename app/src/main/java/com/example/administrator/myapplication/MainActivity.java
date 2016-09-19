package com.example.administrator.myapplication;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
static final int REQUEST_CODE =0x123;
  private   MyBroadcast myBroadcast;
  private  ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
Log.i("MAINACTIVITY", "onServiceConnected() executed");
            MyServier.MyBind myBind=(MyServier.MyBind)service;
            myBind.startDown("http://www.baidu.com");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }
    public void onClickBtn1(View view)
    {
        Intent intent=new Intent(MainActivity.this,MyActivity01.class);
        Bundle bundle=new Bundle();
        bundle.putString("age", "27");
       // intent.putExtras(bundle);
        startActivity(intent);
    }
    public void onClickBtn3(View view)
    {
        Intent intent=new Intent(MainActivity.this,MyActivity01.class);
        Bundle bundle=new Bundle();
        bundle.putString("age","27");
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode)
        {
            /*
            * Acivity返回
            * */
            case REQUEST_CODE:
                if(resultCode==RESULT_OK){
                    Bundle bundle=data.getExtras();
                    Toast.makeText(this,"request: "+bundle.getString("name"),Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(this,"default",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
    /*
    * 打开服务
    * */
    public void onClickBtn5(View view)
    {
        Intent intent=new Intent(MainActivity.this,MyServier.class);
        Bundle bundle=new Bundle();
        bundle.putString("name","ylh");
        intent.putExtras(bundle);
        startService(intent);
    }
    public void onClickBtn6(View view)
    {
        Intent intent=new Intent(MainActivity.this,MyServier.class);
        stopService(intent);
    }
    /*
    * bindService
    * */
    public void onClickBtn7(View view)
    {
        Intent intent=new Intent(MainActivity.this,MyServier.class);
        Bundle bundle=new Bundle();
        bundle.putString("name","YAOLUNHUI");
        intent.putExtras(bundle);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    public void onClickBtn8(View view)
    {
        unbindService(connection);
    }

    public void onClickBtn9(View view)
    {
        Intent intent=new Intent();
        intent.setAction("android.intent.action.MY_BROADCAST");
Bundle bundle=new Bundle();
        bundle.putString("name","YLH");
intent.putExtras(bundle);
        sendBroadcast(intent);

    }
    public void onClickBtn10(View view)
    {
/*
* 注册广播
* */
        myBroadcast=new MyBroadcast();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("android.intent.action.MY_BROADCAST");
        registerReceiver(myBroadcast, intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
unregisterReceiver(myBroadcast);
    }

   public void onClickAdd(View view)
    {
/*
* 添加数据
* */
        ContentValues values=new ContentValues();
        String name=((EditText)findViewById(R.id.EdtName)).getText().toString();
        String grade=((EditText)findViewById(R.id.EdtClass)).getText().toString();
        String age=((EditText)findViewById(R.id.EdtAge)).getText().toString();
        String sex=((EditText)findViewById(R.id.EdtSex)).getText().toString();
        if(name.isEmpty() || grade.isEmpty() || age.isEmpty() || sex.isEmpty()){
            Toast.makeText(MainActivity.this,"数据不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        values.put(StudentProvider.NAME,name);
        values.put(StudentProvider.GRADE,grade);
        values.put(StudentProvider.AGE,age);
        values.put(StudentProvider.SEX, sex);

        Uri uri=getContentResolver().insert(StudentProvider.CONTENT_URI, values);
        Toast.makeText(getBaseContext(),uri.toString(),Toast.LENGTH_SHORT).show();
    }

    public  void onClickSearch(View view)
    {
        /*
        *搜索数据、
        * */
        String name=((EditText)findViewById(R.id.EdtName)).getText().toString();
        if(name.isEmpty()){Toast.makeText(getBaseContext(),"姓名不能为空",Toast.LENGTH_SHORT).show();return;}
        Cursor c=getContentResolver().query(StudentProvider.CONTENT_URI, null, StudentProvider.NAME+"=\""+name+"\"",null, "name");

        ((EditText)findViewById(R.id.EdtClass)).setText("");
        ((EditText)findViewById(R.id.EdtAge)).setText("");
        ((EditText)findViewById(R.id.EdtSex)).setText("");

        if(c.moveToFirst())
        {
            do {

              //  ((EditText)findViewById(R.id.EdtName)).setText(c.getString(c.getColumnIndex(StudentProvider.NAME)));
                ((EditText) findViewById(R.id.EdtClass)).setText(c.getString(c.getColumnIndex(StudentProvider.GRADE)));
                ((EditText)findViewById(R.id.EdtAge)).setText(c.getString(c.getColumnIndex(StudentProvider.AGE)));
                ((EditText)findViewById(R.id.EdtSex)).setText(c.getString(c.getColumnIndex(StudentProvider.SEX)));


            }while(c.moveToNext());
        }else Toast.makeText(this,"未搜索到数据",Toast.LENGTH_SHORT).show();

    }

    public void onClickDel(View view)
    {
        /*
        * 删除数据
        * */
       // getContentResolver().delete();
    }
}
