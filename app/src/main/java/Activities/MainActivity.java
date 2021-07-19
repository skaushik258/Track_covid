package Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.track_covid.R;

public class MainActivity extends AppCompatActivity {

    ImageView simpleimageview ;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       //simpleimageview = findViewById(R.id.simpleview);
        //simpleimageview.setScaleType(ImageView.ScaleType.FIT_XY);

    }

    public void toindia(View view) {
        Intent i = new Intent(MainActivity.this, track_india.class);
        startActivity(i);
    }

    public void tonews(View view) {
        Intent i = new Intent(MainActivity.this, helplines.class);
        startActivity(i);
    }

    public void toworld(View view) {
        Intent i = new Intent(MainActivity.this, track_world.class);
        startActivity(i);
    }

    public void towho(View view) {
       Intent i= new Intent(MainActivity.this, who_live.class);
       startActivity(i);
    }
}
