package nixontergech.com.absonmotors;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoDisplay extends AppCompatActivity {
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_display);
        videoView = findViewById(R.id.videoView);

        Bundle bundle = getIntent().getExtras();
        String videourl = bundle.getString("url");
        String videoname = bundle.getString("videoname");
        setTitle(videoname+" video playing");
        //load video and start
        MediaController mc = new MediaController(VideoDisplay.this);
        mc.setAnchorView(videoView);
        mc.setMediaPlayer(videoView);
        Uri video = Uri.parse(videourl);
        videoView.setMediaController(mc);
        videoView.setVideoURI(video);
        videoView.start();

    }
}
