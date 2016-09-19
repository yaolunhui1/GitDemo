package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/9/6.
 */
public class MyActivity01 extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null) Toast.makeText(this,bundle.getString("age"),Toast.LENGTH_SHORT).show();
    }
    public void onClickBtn2(View view)
    {
   finish();

    }
    public void onClickBtn4(View view)
    {
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putString("name","ylh");
        intent.putExtras(bundle);
       setResult(RESULT_OK, intent);
       finish();
    }
}
