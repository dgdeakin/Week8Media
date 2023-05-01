package com.application.week8media;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    ExoPlayer exoPlayer;
    PlayerView playerView;
    Button button;

    private Boolean playWhenReady = true;
    private int currentItem = 0;
    private long playbackPosition = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(MainActivity.this, WebViewActivity.class));

        playerView = findViewById(R.id.playerView);
        button = findViewById(R.id.button);

        exoPlayer = new ExoPlayer.Builder(this).build();
        playerView.setPlayer(exoPlayer);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(button.getText().toString() == "Play"){

                    MediaItem mediaItem = MediaItem.fromUri(getString(R.string.media_url_mp3));
                    MediaItem mediaItem1 = MediaItem.fromUri(getString(R.string.media_url_mp4));
                    exoPlayer.setMediaItem(mediaItem);
                    exoPlayer.addMediaItem(mediaItem1);
                    exoPlayer.prepare();
                    exoPlayer.play();

                    button.setText("Stop");


                    exoPlayer.setPlayWhenReady(playWhenReady);
                    exoPlayer.seekTo(currentItem, playbackPosition);
                    exoPlayer.prepare();
                }
                else {

                    exoPlayer.stop();
                    playbackPosition = exoPlayer.getCurrentPosition();
                    currentItem = exoPlayer.getCurrentMediaItemIndex();
                    playWhenReady = exoPlayer.getPlayWhenReady();

                    button.setText("Play");

                }


            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        exoPlayer.setPlayWhenReady(playWhenReady);
//        exoPlayer.seekTo(currentItem, playbackPosition);
//        exoPlayer.prepare();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        playbackPosition = exoPlayer.getCurrentPosition();
//        currentItem = exoPlayer.getCurrentMediaItemIndex();
//        playWhenReady = exoPlayer.getPlayWhenReady();
//
//    }

    //    @Override
//    protected void onStop() {
//        super.onPause();
//        playbackPosition = exoPlayer.getCurrentPosition();
//        currentItem = exoPlayer.currentMediaItemIndex
//        playWhenReady = exoPlayer.playWhenReady
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        exoPlayer.release();
    }
}