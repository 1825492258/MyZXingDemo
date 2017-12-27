package com.item.zxing.demo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.item.zxing.demo.zxing.app.CaptureActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = findViewById(R.id.tv_hello);
        findViewById(R.id.btn_one).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_one:
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent,1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode == Activity.RESULT_OK){
                    String code = data.getStringExtra("SCAN_RESULT");
                    mView.setText(code);
                }
                break;
        }
    }
}
