package com.keiis.hanjul.organazation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.keiis.hanjul.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hojun on 2017-05-21.
 */

public class CompetitionSummaryFragment extends Fragment {

    private int position;

    private  Bitmap bitmap = null;

    private ImageView imageView;
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);

        //1~4
        position = args.getInt("position")+1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_competiton_summary,container,false);

        imageView = (ImageView)view.findViewById(R.id.image);

        //이미지 표시
        String imageUrl = "http://www.keiis.co.kr/hanjulApp/hanjul/image/guideline_2016_13_"+position+".png";

        setBitmap(imageUrl);

        return view;
    }

    //url 이미지 bitmap으로 변환
    private void setBitmap(final String imageUrl){
        Thread thread = new Thread(){
            @Override
            public void run() {
                try{
                    URL url = new URL(imageUrl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.connect();

                    InputStream is = httpURLConnection.getInputStream();

                    bitmap = BitmapFactory.decodeStream(is);
                }catch (Exception e){
                    e.getStackTrace();
                }
            }
        };
        thread.start();
        try{
            thread.join();

            imageView.setImageBitmap(bitmap);
        }catch (Exception e){
            e.getStackTrace();
        }
    }
}
