package com.item.zxing.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvHello;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvHello = findViewById(R.id.tv_hello);
        img = findViewById(R.id.img);
        // 开始默认二维码扫描界面
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_three).setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 处理二维码扫描的结果
        if (requestCode == 22) {
            // 处理扫描结果
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    tvHello.setText(result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    tvHello.setText("解析二维码失败");
                }
            }
        }
    }
    private Bitmap bitmap;
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_one:
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent, 22);
                break;
            case R.id.btn_two:
                Intent intent1 = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent1, 22);
                break;
            case R.id.btn_three:

                bitmap = CodeUtils.createImage("无总额及",90,90, BitmapFactory.decodeResource(getResources(),
                        R.mipmap.ic_launcher));
                img.setImageBitmap(bitmap);
                break;
        }
    }
}
