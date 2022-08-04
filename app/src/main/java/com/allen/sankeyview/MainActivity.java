package com.allen.sankeyview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.allen.sankeyview.model.SanKeyModel;
import com.allen.sankeyview.view.SanKeyGroup;
import com.allen.sankeyview.view.SanKeyView;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SanKeyView sanKeyView;
    private SanKeyGroup sanKeyGroup;
    private SanKeyModel mode0;
    private SanKeyModel mode1;
    private SanKeyModel mode2;
    private Button btn0;
    private Button btn1;
    private Button btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    private void initData() {



    }

    private void initView() {
        sanKeyView = findViewById(R.id.san_key_view);
        sanKeyGroup = findViewById(R.id.san_key_group);
        btn0 = findViewById(R.id.data0);
        btn1 = findViewById(R.id.data1);
        btn2 = findViewById(R.id.data2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.data0:
                Gson gson0 = new Gson();
                mode0 = gson0.fromJson(data0, SanKeyModel.class);
                sanKeyGroup.setData(mode0);
                break;
            case R.id.data1:

                Gson gson1 = new Gson();
                mode1 = gson1.fromJson(data1, SanKeyModel.class);
                sanKeyGroup.setData(mode1);
                break;
            case R.id.data2:
                Gson gson2 = new Gson();
                mode2 = gson2.fromJson(data2, SanKeyModel.class);
                sanKeyGroup.setData(mode2);
                break;


        }
    }

    private String data1 = "{\"nodes\":[{\"name\": \"a\"}, {\"name\": \"a1\"}, {\"name\": \"a2\"}, {\"name\": \"a3\"}, {\"name\": \"a4\"}]," +
            "\"links\": [{\"source\": \"a\",\"target\": \"a1\",\"value\": 50}," +
            " {\"source\": \"a\",\"target\": \"a2\",\"value\": 20}," +
            " {\"source\": \"a\",\"target\": \"a3\",value: 20}, " +
            "{\"source\": \"a\",\"target\": \"a4\",\"value\":10}]}";

    private String data2 = "{\"nodes\":[{\"name\": \"a\"}, {\"name\": \"a1\"}, {\"name\": \"a2\"}, {\"name\": \"a3\"}, {\"name\": \"a4\"}, {\"name\": \"a5\"}, {\"name\": \"a6\"}, {\"name\": \"a7\"}, {\"name\": \"a8\"}]," +
            "\"links\": [{\"source\": \"a\",\"target\": \"a1\",\"value\": 40}," +
            " {\"source\": \"a\",\"target\": \"a2\",\"value\": 30}," +
            " {\"source\": \"a\",\"target\": \"a3\",value: 20}, " +
            " {\"source\": \"a\",\"target\": \"a4\",value: 1}, " +
            " {\"source\": \"a\",\"target\": \"a5\",value: 2}, " +
            " {\"source\": \"a\",\"target\": \"a6\",value: 5}, " +
            " {\"source\": \"a\",\"target\": \"a7\",value: 4}, " +
            "{\"source\": \"a\",\"target\": \"a8\",\"value\":1}]}";

    private String data0 = "{\"nodes\":[{\"name\": \"a\"}, {\"name\": \"a1\"}, {\"name\": \"a2\"}, {\"name\": \"a3\"}, {\"name\": \"a4\"}, {\"name\": \"a5\"}, {\"name\": \"a6\"}, {\"name\": \"a7\"}, {\"name\": \"a8\"}, {\"name\": \"a9\"}, {\"name\": \"a10\"}, {\"name\": \"a11\"}, {\"name\": \"a12\"}]," +
            "\"links\": [{\"source\": \"a\",\"target\": \"a1\",\"value\": 20}," +
            " {\"source\": \"a\",\"target\": \"a2\",\"value\": 30}," +
            " {\"source\": \"a\",\"target\": \"a3\",value: 20}, " +
            " {\"source\": \"a\",\"target\": \"a4\",value: 3}, " +
            " {\"source\": \"a\",\"target\": \"a5\",value: 2}, " +
            " {\"source\": \"a\",\"target\": \"a6\",value: 5}, " +
            " {\"source\": \"a\",\"target\": \"a7\",value: 4}, " +
            " {\"source\": \"a\",\"target\": \"a8\",value: 4}, " +
            " {\"source\": \"a\",\"target\": \"a9\",value: 4}, " +
            " {\"source\": \"a\",\"target\": \"a10\",value: 4}, " +
            " {\"source\": \"a\",\"target\": \"a11\",value: 3}, " +
            "{\"source\": \"a\",\"target\": \"a12\",\"value\":1}]}";


}
