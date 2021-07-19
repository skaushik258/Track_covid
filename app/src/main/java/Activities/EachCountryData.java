package Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.track_covid.R;

import java.text.NumberFormat;

import static Activities.Constant.COUNTRY_ACTIVE;
import static Activities.Constant.COUNTRY_CONFIRMED;
import static Activities.Constant.COUNTRY_DECEASED;
import static Activities.Constant.COUNTRY_NAME;
import static Activities.Constant.COUNTRY_NEW_CONFIRMED;
import static Activities.Constant.COUNTRY_NEW_DECEASED;
import static Activities.Constant.COUNTRY_RECOVERED;

public class EachCountryData extends AppCompatActivity {

    TextView  tv_country_name, tv_cases, tv_active, tv_death, tv_recovered,
            today_death, today_recovered, today_positive;

    String country_name, country_cases, country_active, country_recovered,
            country_death, country_today_death, country_today_recovered, country_today_positive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_country_data);

        //Setting up the title of actionbar as State name
        getSupportActionBar().setTitle(country_name);


        tv_country_name= findViewById(R.id.country_info);
        tv_cases = findViewById(R.id.country_total_cases);
        tv_death = findViewById(R.id.country_Death);
        tv_recovered = findViewById(R.id.country_Recovered);
        tv_active = findViewById(R.id.country_Active);
        today_death = findViewById(R.id.country_Today_Death);
        today_recovered = findViewById(R.id.country_Today_recovered);
       today_positive = findViewById(R.id.country_Today_Positive);


        GetIntent();
        Loaddata();

    }

    private void Loaddata() {

        tv_country_name.setText(country_name);
        tv_cases.setText(NumberFormat.getInstance().format(Integer.parseInt(country_cases)));
        tv_death.setText(NumberFormat.getInstance().format(Integer.parseInt(country_death)));

        tv_active.setText(NumberFormat.getInstance().format(Integer.parseInt(country_active)));
                /*int int_active_new = Integer.parseInt(str_confirmed_new)
                        - (Integer.parseInt(str_recovered_new) + Integer.parseInt(str_death_new));
                tv_active_new.setText("+"+NumberFormat.getInstance().format(int_active_new<0 ? 0 : int_active_new));*/
        tv_recovered.setText(country_recovered);
        today_death.setText(NumberFormat.getInstance().format(Integer.parseInt(country_today_death)));
        today_positive.setText(NumberFormat.getInstance().format(Integer.parseInt(country_today_positive)));

        today_recovered.setText(NumberFormat.getInstance().format(Integer.parseInt(country_today_recovered)));
        //tv_recovered_new.setText("+"+NumberFormat.getInstance().format(Integer.parseInt(str_recovered_new)));
       // tv_recovered_new.setText("N/A");
    }


    private void GetIntent() {
        Intent intent = getIntent();
        country_name= intent.getStringExtra(COUNTRY_NAME);
        country_cases= intent.getStringExtra(COUNTRY_CONFIRMED);
        country_active= intent.getStringExtra(COUNTRY_ACTIVE);
        country_death= intent.getStringExtra(COUNTRY_DECEASED);
        country_recovered= intent.getStringExtra(COUNTRY_RECOVERED);
        country_today_death= intent.getStringExtra(COUNTRY_NEW_DECEASED);
        country_today_recovered= intent.getStringExtra(COUNTRY_RECOVERED);
        country_today_positive= intent.getStringExtra(COUNTRY_NEW_CONFIRMED);

    }
}