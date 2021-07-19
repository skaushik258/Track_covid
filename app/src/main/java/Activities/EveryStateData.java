package Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.track_covid.R;

import java.text.NumberFormat;

import static Activities.Constant.STATE_ACTIVE;
import static Activities.Constant.STATE_CONFIRMED;
import static Activities.Constant.STATE_CONFIRMED_NEW;
import static Activities.Constant.STATE_DEATH;
import static Activities.Constant.STATE_DEATH_NEW;
import static Activities.Constant.STATE_NAME;
import static Activities.Constant.STATE_RECOVERED;
import static Activities.Constant.STATE_RECOVERED_NEW;

public class EveryStateData extends AppCompatActivity{


    TextView state, confirmed, confirmed_new, active, active_new, death, death_new,
            recovered, recovered_new, lastupdatedate, tv_dist;

    String str_stateName, str_confirmed, str_confirmed_new, str_active, str_active_new, str_death, str_death_new,
            str_recovered, str_recovered_new, str_lastupdatedate;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_state_data);

        //Fetching data which is passed from previous activity into this activity
      GetIntent();


        //Setting up the title of actionbar as State name
        getSupportActionBar().setTitle(str_stateName);

        //Initialise all textviews
        Init();

       LoadData();

    }

    private void Init() {
        state = findViewById(R.id.states_info);
        confirmed = findViewById(R.id.total_cases);
        confirmed_new = findViewById(R.id.Today_Positive);
        active = findViewById(R.id.Active);
        active_new = findViewById(R.id.Today_Positive);
        recovered = findViewById(R.id.Recovered);
      recovered_new = findViewById(R.id.Today_recovered);
        death = findViewById(R.id.Death);
        death_new = findViewById(R.id.Today_Death);
    }

    private void LoadData() {
        // all view are string and this we get integer value so we just convert value into string format in this load state data method();
        state.setText(str_stateName);
        confirmed.setText(NumberFormat.getInstance().format(Integer.parseInt(str_confirmed)));
        confirmed_new.setText("+"+NumberFormat.getInstance().format(Integer.parseInt(str_confirmed_new)));

        active.setText(NumberFormat.getInstance().format(Integer.parseInt(str_active)));

        // in this we subtract the confirmed cases from recovered so that we will get the active_new cases;
        int int_active_new = Integer.parseInt(str_confirmed_new)
                - (Integer.parseInt(str_recovered_new) + Integer.parseInt(str_death_new));
        active_new.setText("+"+NumberFormat.getInstance().format(int_active_new<0 ? 0 : int_active_new));

        death.setText(NumberFormat.getInstance().format(Integer.parseInt(str_death)));
        death_new.setText("+"+NumberFormat.getInstance().format(Integer.parseInt(str_death_new)));

        recovered.setText(NumberFormat.getInstance().format(Integer.parseInt(str_recovered)));
        recovered_new.setText("+"+NumberFormat.getInstance().format(Integer.parseInt(str_recovered_new)));
    }

    private void GetIntent() {
        Intent intent = getIntent();
        str_stateName = intent.getStringExtra(STATE_NAME);
        str_confirmed = intent.getStringExtra(STATE_CONFIRMED);
        str_confirmed_new = intent.getStringExtra(STATE_CONFIRMED_NEW);
        str_active = intent.getStringExtra(STATE_ACTIVE);
        str_death = intent.getStringExtra(STATE_DEATH);
        str_death_new = intent.getStringExtra(STATE_DEATH_NEW);
        str_recovered = intent.getStringExtra(STATE_RECOVERED);
        str_recovered_new = intent.getStringExtra(STATE_RECOVERED_NEW);
        //   str_lastupdatedate = intent.getStringExtra(STATE_LAST_UPDATE);
    }

}