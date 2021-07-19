package Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.track_covid.R;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapters.Country_Adapter;
import Model.Country_Model;

public class Track_Countries extends AppCompatActivity {

    RecyclerView recyclerView_country;
    ArrayList<Country_Model> countryModelArrayList;
    Country_Adapter country_adapter;
    EditText edt_country;

    SimpleArcLoader simpleArcLoader_country;

    String country_name ,country_cases, country_active, country_death, country_recovered, country_today_postive,
            country_today_death, country_today_recovered;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track__countries);


        recyclerView_country= findViewById(R.id.country_recycle);

        edt_country= findViewById(R.id.country_edittext);

        recyclerView_country.setHasFixedSize(true);

        recyclerView_country.setLayoutManager(new LinearLayoutManager(this));

        countryModelArrayList = new ArrayList<>();
        country_adapter = new Country_Adapter(Track_Countries.this, countryModelArrayList);
        recyclerView_country.setAdapter(country_adapter);
        simpleArcLoader_country= findViewById(R.id.country_loader);


      getSupportActionBar().setTitle("Countries");

        FetchCountryWisedata();

        //Search
        edt_country.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Filter(s.toString());
            }
        });

    }
    private void Filter(String text) {
        ArrayList<Country_Model> filteredList = new ArrayList<>();
        for (Country_Model item : countryModelArrayList) {
            if (item.getCountry().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        country_adapter.filterList(filteredList, text);
    }
    private void FetchCountryWisedata() {

        String url="https://corona.lmao.ninja/v2/countries";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i=0; i<jsonArray.length(); i++)
                    {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        country_name= jsonObject.getString("country");
                        country_cases= jsonObject.getString("cases");
                        country_active= jsonObject.getString("active");
                        country_death= jsonObject.getString("deaths");
                        country_recovered= jsonObject.getString("recovered");
                        country_today_postive= jsonObject.getString("todayCases");
                        country_today_death= jsonObject.getString("todayDeaths");
                        country_today_recovered= jsonObject.getString("todayRecovered");

                        JSONObject flagObject = jsonObject.getJSONObject("countryInfo");
                        String flagurl= flagObject.getString("flag");


                        Country_Model model = new Country_Model(country_name, country_cases, country_active, country_death, country_recovered, country_today_death,
                                country_today_recovered, country_today_postive, flagurl );
                        countryModelArrayList.add(model);
                    }

                    country_adapter = new Country_Adapter(Track_Countries.this, countryModelArrayList);
                    recyclerView_country.setAdapter(country_adapter);

                    simpleArcLoader_country.stop();
                    simpleArcLoader_country.setVisibility(View.GONE);



                } catch (JSONException e) {
                    e.printStackTrace();

                    simpleArcLoader_country.stop();
                    simpleArcLoader_country.setVisibility(View.GONE);
                }
            }
        },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader_country.stop();
                simpleArcLoader_country.setVisibility(View.GONE);
                Toast.makeText(Track_Countries.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue =  Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

}