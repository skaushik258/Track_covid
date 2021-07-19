package Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.track_covid.R;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class track_india extends AppCompatActivity {
    TextView india_cases , india_active, india_recovered, india_deaths,
            india_todayrecovered, india_todaypositive, india_todaydeaths;
    SimpleArcLoader simpleArcLoader_track;
    ScrollView scrollView_track;
    PieChart pieChart_track;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_india);

        Intent intent = getIntent();
        getSupportActionBar().setTitle("INDIA");


        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        india_cases = findViewById(R.id.india_Total_Cases);
        india_active = findViewById(R.id.india_Active);
        india_recovered = findViewById(R.id.india_Recovered);
        india_deaths = findViewById(R.id.india_Death);
        india_todayrecovered = findViewById(R.id.india_Today_Recovered);
        india_todaypositive = findViewById(R.id.india_Today_Positive);
        india_todaydeaths = findViewById(R.id.india_Today_Death);

        simpleArcLoader_track = findViewById(R.id.loader_track);
        scrollView_track = findViewById(R.id.scrollstats_track);
        pieChart_track = findViewById(R.id.piechart_track);

        fetchdata();
    }

    private void fetchdata() {
        String url = "https://corona.lmao.ninja/v2/countries/India/ ";
        simpleArcLoader_track.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject =new JSONObject(response.toString());
                    india_cases.setText(jsonObject.getString("cases"));
                    india_active.setText(jsonObject.getString("active"));
                    india_recovered.setText(jsonObject.getString("recovered"));
                    india_deaths.setText(jsonObject.getString("deaths"));
                    india_todayrecovered.setText(jsonObject.getString("todayRecovered"));
                    india_todaypositive.setText(jsonObject.getString("todayCases"));
                    india_todaydeaths.setText(jsonObject.getString("todayDeaths"));

                    pieChart_track.addPieSlice(new PieModel("Cases",Integer.parseInt(india_cases.getText().toString()), Color.parseColor("#FFA726")));
                    pieChart_track.addPieSlice(new PieModel("Recovered",Integer.parseInt(india_active.getText().toString()), Color.parseColor("#66BB6A")));
                    pieChart_track.addPieSlice(new PieModel("Death",Integer.parseInt(india_deaths.getText().toString()), Color.parseColor("#EF5350")));
                    pieChart_track.addPieSlice(new PieModel("Active",Integer.parseInt(india_active.getText().toString()), Color.parseColor("#29B6F6")));
                    pieChart_track.startAnimation();

                    simpleArcLoader_track.stop();
                    simpleArcLoader_track.setVisibility(View.GONE);
                    scrollView_track.setVisibility(View.VISIBLE);
                }


                 catch (JSONException e) {
                    e.printStackTrace();

                     simpleArcLoader_track.stop();
                     simpleArcLoader_track.setVisibility(View.GONE);
                     scrollView_track.setVisibility(View.VISIBLE);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                simpleArcLoader_track.stop();
                simpleArcLoader_track.setVisibility(View.GONE);
                scrollView_track.setVisibility(View.VISIBLE);

                Toast.makeText(track_india.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
    });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId()== android.R.id.home);
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void track_states(View view) {
        Intent i = new Intent(track_india.this, Track_States.class);
        startActivity(i);
        finish();
    }
}
