package com.example.santoshlokhande.facedetectionandroid.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.santoshlokhande.facedetectionandroid.R;

public class CroppedPictureActiviyt extends Activity {

    private ImageView iv;
    private Button retake;
    Bitmap bitmapFrontCam;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cropped_picture);

        iv = (ImageView) findViewById(R.id.imageView1);
        retake= (Button) findViewById(R.id.retake);

        try {
            bitmapFrontCam = (Bitmap) getIntent()
                    .getParcelableExtra("BitmapImage");



        } catch (Exception e) {
        }
        iv.setImageBitmap(bitmapFrontCam);

        retake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

}
