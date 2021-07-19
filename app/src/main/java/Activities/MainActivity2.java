package Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.track_covid.R;

public class MainActivity2  extends AppCompatActivity {

 //   ProgressBar progressBar;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main2);

      /*  progressBar= findViewById(R.id.spin_kit);
        Sprite doubleBounce = new FoldingCube();
        progressBar.setIndeterminateDrawable(doubleBounce);*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               // progressBar.setVisibility(View.VISIBLE);
                Intent i = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(i);
                finish(); } }, 3000);

    }
}