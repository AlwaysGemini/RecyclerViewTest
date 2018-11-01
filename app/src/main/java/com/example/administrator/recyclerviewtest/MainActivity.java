package com.example.administrator.recyclerviewtest;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private List<Evaluate> evaluateList = new ArrayList<>();
    EvaluateAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bmob.initialize(this, "7ae61a69821fdba3c6b79f280caaa3d4");

        initEvaluate();

        Button button = findViewById(R.id.send);
        button.setOnClickListener(this);
    }

    private void initEvaluate(){
        BmobQuery<Evaluate> query = new BmobQuery<>();
        query.order("-createdAt");// 按照时间降序//
        query.findObjects(new FindListener<Evaluate>() {
            @Override
            public void done(List<Evaluate> object, BmobException e) {
                if(e==null){
                    evaluateList.clear();
                    evaluateList.addAll(object);
                    recyclerView = findViewById(R.id.recycler_view);
                    linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    adapter = new EvaluateAdapter(evaluateList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    private Evaluate evaluate = new Evaluate();
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send:
                EditText editText = findViewById(R.id.message_edit);
                evaluate.setEvaluate(editText.getText().toString());
                evaluate.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId, BmobException e) {
                        if(e==null){
                            Toast.makeText(MainActivity.this,"消息发送成功" ,Toast.LENGTH_LONG).show();
                            initEvaluate();
                        }else{
                            Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                        }
                    }
                });
                editText.getText().clear();
                break;
            default:
                break;
        }
    }
}
