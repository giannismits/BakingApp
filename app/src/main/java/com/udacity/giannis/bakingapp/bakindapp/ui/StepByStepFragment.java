package com.udacity.giannis.bakingapp.bakindapp.ui;


import android.annotation.SuppressLint;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


import com.udacity.giannis.bakingapp.bakindapp.R;
import com.udacity.giannis.bakingapp.bakindapp.model.Steps;

import java.util.ArrayList;


public class StepByStepFragment extends Fragment{

    private VideoView simplePlayer;
    private static String TAG = "StepByStepFragment";
    private ArrayList<Steps> steps;
    private int position;
    private ImageView mNovideo;
    private TextView mDescription;


    public StepByStepFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate( R.layout.player, container, false );
        simplePlayer = (VideoView) rootView.findViewById( R.id.video_view );
       mNovideo = (ImageView) rootView.findViewById(R.id.no_video);
       mDescription = (TextView) rootView.findViewById(R.id.description_step);

        initializePlayer();


        return rootView;
    }

    private void initializePlayer(){
        String videoUrl = getSteps().get(position).getVideoURL();
            if (videoUrl.contains(".mp4")){
                simplePlayer.setVideoURI(Uri.parse( videoUrl) );
                android.widget.MediaController mediaController = new android.widget.MediaController( getContext());

                mediaController.setMediaPlayer( simplePlayer );
                simplePlayer.setMediaController( mediaController );
                mediaController.setAnchorView(simplePlayer);
                simplePlayer.requestFocus();
                simplePlayer.start();


                mediaController.setPrevNextListeners(new View.OnClickListener() {
                    public void onClick(View v) {
                    if (position <=getSteps().size()-1){
                        position++;
                        initializePlayer();
                    }else{
                        return;
                    }
                    }
                }, new View.OnClickListener() {
                    public void onClick(View v) {
                        if (position >=0){
                            position--;
                            initializePlayer();
                        }else {
                            return;
                        }
                    }
                });
            }else {
                simplePlayer.setVisibility(View.GONE);
                mNovideo.setVisibility(View.VISIBLE);
                mNovideo.setImageResource(R.drawable.novideo);
            }
            mDescription.setText(getSteps().get(getPosition()).getDescription());



    }


    private void releasePlayer(){
        simplePlayer.stopPlayback();
    }
    @Override
    public void onStop() {
        super.onStop();

        releasePlayer();
    }
    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        simplePlayer.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }


    public ArrayList<Steps> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Steps> steps) {
        this.steps = steps;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }



}
