package nixontergech.com.absonmotors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class BronchureDisplay extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bronchure_display);
        imageView = findViewById(R.id.imageView);
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("url");
        String broncurename = bundle.getString("broncurename");
        setTitle(broncurename+" Bronchure");

        Glide.with(BronchureDisplay.this)
                .load(url)
                .into(imageView);

    }
}
