package com.thereza.Retrofitpagination;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.thereza.Retrofitpagination.R;

public class DetailsActivity extends AppCompatActivity {

    TextView title, year, desc;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initView();
        getDeatilsData();
    }

    public void initView(){
        title =(TextView) findViewById(R.id.titleTxt);
        year =(TextView) findViewById(R.id.yearTxt);
        desc =(TextView) findViewById(R.id.descTxt);
        img =(ImageView) findViewById(R.id.imgPoster);

    }

    public  void getDeatilsData(){

        Bundle extras = getIntent().getExtras();
        String mTitle = null,myear = null,mDesc = null;


        if (extras != null) {
            mTitle = extras.getString("mMovieTitle");
            myear = extras.getString("mYear");
            mDesc = extras.getString("mMovieDesc");
        }

        Intent intent = getIntent();
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("mPosterImg");
        
        title.setText(mTitle.toString());
        year.setText(myear.toString());
        desc.setText(mDesc.toString());
        //img.setImageBitmap(bitmap);
        
    }
}
