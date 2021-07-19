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

import Adapters.StateAdapter;
import Model.State_Model;


public class Track_States extends AppCompatActivity {

    EditText edt;
    RecyclerView recyclerView_state;
    SimpleArcLoader simpleArcLoader_state;

    StateAdapter stateWiseAdapter;

    ArrayList<State_Model> stateModelList;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track__states);

        recyclerView_state= findViewById(R.id.state_recycle);
        simpleArcLoader_state= findViewById(R.id.simple_arc_state);
        edt= findViewById(R.id.state_editext);
        recyclerView_state.setHasFixedSize(true);
        recyclerView_state.setLayoutManager(new LinearLayoutManager(this));
        fetchstatedata();

        getSupportActionBar().setTitle("States");

        stateModelList = new ArrayList<>();

        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Filter(editable.toString());
            }
        });

    }

    public void Filter(String text){
        ArrayList<State_Model> filteredList = new ArrayList<>();
        for (State_Model item : stateModelList){
            if(item.getState().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        stateWiseAdapter.filterlist(filteredList, text);
    }

    private void fetchstatedata() {

        simpleArcLoader_state.start();
        String url ="http://api.covidindiatracker.com/state_data.json";

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i=1; i<jsonArray.length(); i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String StateName = jsonObject.getString("state");
                                String cases = jsonObject.getString("confirmed");
                                String active = jsonObject.getString("active");
                                String recovered = jsonObject.getString("recovered");
                                String death = jsonObject.getString("deaths");
                                String today_recovered = jsonObject.getString("cChanges");
                                String today_positive = jsonObject.getString("aChanges");

                                State_Model model = new State_Model(StateName, cases,active,recovered,death,today_recovered,today_positive);
                                stateModelList.add(model);
                            }
                            stateWiseAdapter = new StateAdapter(Track_States.this, stateModelList);
                                recyclerView_state.setAdapter(stateWiseAdapter);

                                simpleArcLoader_state.stop();
                                simpleArcLoader_state.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader_state.stop();
                            simpleArcLoader_state.setVisibility(View.GONE);

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader_state.stop();
                simpleArcLoader_state.setVisibility(View.GONE);

                Toast.makeText(Track_States.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue request = Volley.newRequestQueue(this);
        request.add(stringRequest);
    }


}