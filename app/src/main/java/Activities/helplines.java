package Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.track_covid.R;

public class helplines extends AppCompatActivity {
ListView itemview;
String[] helplineitem = {"Andaman & Nicobar-\t\t\t03192-232102","Andhra Pradesh-\t\t\t0866-2410978","Arunachal Pradesh-\t\t\t9436055743","Assam-\t\t\t6913347770",
        "Bihar-\t\t\t104","Chandigarh-\t\t\t9779558282\n" ,"Chhattisgarh-\t\t\t104\n" , "Dadra Nagar Haveli-\t\t\t104\n" , "Delhi-\t\t\t011-22307145\n" , "Goa-\t\t\t104\n" ,
        "Gujarat-\t\t\t104\n" , "Haryana-\t\t\t8558893911\n" , "Himachal Pradesh-\t\t\t104\n" , "Jammu-\t\t\t01912520982\n" , "Jharkhand-\t\t\t104\n" ,
        "Karnataka-\t\t\t104\n" , "Kashmir-\t\t\t01942440283\n" , "Kerala-\t\t\t0471-2552056\n" ,"Ladakh-\t\t\t01982256462\n" ,
        "Lakshadweep-\t\t\t104\n" , "Madhya Pradesh-\t\t\t104\n" , "Maharashtra-\t\t\t020-26127394\n" , "Manipur-\t\t\t3852411668\n" , "Meghalaya-\t\t\t108\n" , "Mizoram-\t\t\t102\n" ,
        "Nagaland-\t\t\t7005539653\n" , "Odisha-\t\t\t9439994859\n" , "Puducherry-\t\t\t104\n" , "Punjab-\t\t\t104\n" , "Rajasthan\t\t\t0141-2225624\n" , "Sikkim-\t\t\t104\n" ,
        "Tamil Nadu-\t\t\t044-29510500\n" , "Telangana-\t\t\t104\n" , "Tripura-\t\t\tt0381-2315879\n" , "Uttarakhand-\t\t\t104\n" , "Uttar Pradesh-\t\t\t18001805145\n" ,
        "West Bengal-\t\t1800313444222, 03323412600" };
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helplines);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setTitle("HELPLINE");
        Intent intent = getIntent();
        itemview= (ListView) findViewById(R.id.listview_helpline);
        ArrayAdapter<String> adapter  = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,helplineitem);
        itemview.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== android.R.id.home);
        finish();
        return super.onOptionsItemSelected(item);
    }
}
