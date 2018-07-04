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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.squareup.picasso.Picasso;
import com.udacity.giannis.bakingapp.bakindapp.R;
import com.udacity.giannis.bakingapp.bakindapp.model.Steps;

import java.util.ArrayList;


public class StepByStepFragment extends Fragment{

    private SimpleExoPlayerView simplePlayerView;
    private SimpleExoPlayer simpleExoPlayer;
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
        simplePlayerView = (SimpleExoPlayerView) rootView.findViewById( R.id.video_view );
        mNovideo = (ImageView) rootView.findViewById(R.id.no_video);
        mDescription = (TextView) rootView.findViewById(R.id.description_step);




        initializePlayer();
        return rootView;
    }

    private void initializePlayer() {
        String videoUrl = getSteps().get(position).getVideoURL();
        String thumpUrl = getSteps().get(position).getThumbnailURL();
        Uri thumpUri = Uri.parse(thumpUrl);

        if (videoUrl==null){
            simpleExoPlayer.release();
            simplePlayerView.setVisibility(View.GONE);
            mNovideo.setVisibility(View.VISIBLE);
            mNovideo.setImageResource(R.drawable.novideo);
            return;

        }else  {
            Uri videoUri = Uri.parse(videoUrl);
            playVideo(videoUri);

        } if (!thumpUrl.equals("")){
            if (thumpUrl.contains(".mp4")){
                playVideo(thumpUri);
            }else {
                simpleExoPlayer.release();
                simplePlayerView.setVisibility(View.GONE);
                mNovideo.setVisibility(View.VISIBLE);
                Picasso.get().load(thumpUrl).error(R.drawable.novideo).into(mNovideo);
            }
            return;
        }if (videoUrl.equals("") && thumpUrl.equals("")){
            simpleExoPlayer.release();
            simplePlayerView.setVisibility(View.GONE);
            mNovideo.setVisibility(View.VISIBLE);
            mNovideo.setImageResource(R.drawable.novideo);
        }
    }

    private void playVideo(Uri videoUri){
        if (!videoUri.equals("")){
            try {
                BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

                TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));

                simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);

                DefaultHttpDataSourceFactory defaultHttpDataSourceFactory = new DefaultHttpDataSourceFactory("Baking_App");

                ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

                MediaSource mediaSource = new ExtractorMediaSource(videoUri, defaultHttpDataSourceFactory, extractorsFactory, null, null);

                simplePlayerView.setPlayer(simpleExoPlayer);
                simpleExoPlayer.prepare(mediaSource);
                simpleExoPlayer.setPlayWhenReady(true);

            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        }else {
            simpleExoPlayer.release();
            simplePlayerView.setVisibility(View.GONE);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        mDescription.setText(getSteps().get(position).getDescription());
    }

    @Override
    public void onStop() {
        super.onStop();
        simpleExoPlayer.release();
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
