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

public class track_world extends AppCompatActivity {

    TextView  world_cases, world_Active, world_recovered, world_deaths,
            world_Today_deaths, world_Today_positive, world_Today_recovered;

    SimpleArcLoader simpleArcLoader_world;
    ScrollView scrollView_world;
    PieChart pieChart_world;
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_world);

        getSupportActionBar().setTitle("WORLD");


        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        world_cases = findViewById(R.id.world_Total_Cases);
        world_Active = findViewById(R.id.world_Active);
        world_recovered = findViewById(R.id.world_Recovered);
        world_deaths = findViewById(R.id.world_Death);
        world_Today_deaths = findViewById(R.id.world_Today_Death);
        world_Today_positive = findViewById(R.id.world_Today_Positive);
        world_Today_recovered = findViewById(R.id.world_Today_Recovered);

        simpleArcLoader_world = findViewById(R.id.loader_world);
        scrollView_world = findViewById(R.id.scrollstats_world);
        pieChart_world =findViewById(R.id.piechart_world);
            worldfetchdata();
    }

    private void worldfetchdata() {

        String url = "https://corona.lmao.ninja/v2/all/";
        simpleArcLoader_world.start();

        StringRequest  request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    world_cases.setText(jsonObject.getString("cases"));
                    world_Active.setText(jsonObject.getString("active"));
                    world_recovered.setText(jsonObject.getString("recovered"));
                    world_deaths.setText(jsonObject.getString("deaths"));
                    world_Today_deaths.setText(jsonObject.getString("todayDeaths"));
                    world_Today_positive.setText(jsonObject.getString("todayCases"));
                    world_Today_recovered.setText(jsonObject.getString("todayRecovered"));

                    pieChart_world.addPieSlice(new PieModel("Cases",Integer.parseInt(world_cases.getText().toString()), Color.parseColor("#FFA726")));
                    pieChart_world.addPieSlice(new PieModel("Recovered",Integer.parseInt(world_recovered.getText().toString()), Color.parseColor("#66BB6A")));
                    pieChart_world.addPieSlice(new PieModel("Death",Integer.parseInt(world_deaths.getText().toString()), Color.parseColor("#EF5350")));
                    pieChart_world.addPieSlice(new PieModel("Active",Integer.parseInt(world_Active.getText().toString()), Color.parseColor("#29B6F6")));
                    pieChart_world.startAnimation();

                    simpleArcLoader_world.stop();
                    simpleArcLoader_world.setVisibility(View.GONE);
                    scrollView_world.setVisibility(View.VISIBLE);

                } catch (JSONException e) {
                    e.printStackTrace();

                    simpleArcLoader_world.stop();
                    simpleArcLoader_world.setVisibility(View.GONE);
                    scrollView_world.setVisibility(View.VISIBLE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                simpleArcLoader_world.stop();
                simpleArcLoader_world.setVisibility(View.GONE);
                scrollView_world.setVisibility(View.VISIBLE);

                Toast.makeText(track_world.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== android.R.id.home);
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void track_countries(View view) {
        Intent i = new Intent(track_world.this, Track_Countries.class);
        startActivity(i);
        finish();
    }
}
