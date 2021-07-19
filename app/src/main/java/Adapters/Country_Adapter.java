package Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.track_covid.R;

import java.util.ArrayList;

import Activities.EachCountryData;
import Model.Country_Model;

import static Activities.Constant.COUNTRY_ACTIVE;
import static Activities.Constant.COUNTRY_CONFIRMED;
import static Activities.Constant.COUNTRY_DECEASED;
import static Activities.Constant.COUNTRY_FLAGURL;
import static Activities.Constant.COUNTRY_NAME;
import static Activities.Constant.COUNTRY_NEW_CONFIRMED;
import static Activities.Constant.COUNTRY_NEW_DECEASED;
import static Activities.Constant.COUNTRY_RECOVERED;


public class Country_Adapter extends RecyclerView.Adapter<Country_Adapter.ViewHolder>{

    Context context;
    String searchText;
    ArrayList<Country_Model> countrywiselist;


    public Country_Adapter(Context context, ArrayList<Country_Model> countrywiselist) {
        this.context = context;
        this.countrywiselist = countrywiselist;
    }


    public void filterList(ArrayList<Country_Model> filteredList, String text) {
            countrywiselist = filteredList;
        this.searchText = text;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custemitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Country_Model currentitem = countrywiselist.get(position);
        String countryName = currentitem.getCountry();
        String countryFlag = currentitem.getFlag();
        holder.country_name.setText(countryName);
        Glide.with(context).load(countryFlag).diskCacheStrategy(DiskCacheStrategy.DATA).into(holder.imageflag);
        /*Glide is use to load url image into the UI that why we use it
          diskCasheStrategy:- if you go to the page for the first time it will take loading time to load show you after that it will show you on th UI
          second time it will not take time to load image because it will store the data(meand flag) into the cache of your phone that we used
           diskcashestrategy
        */

        holder.linear_country_manner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Country_Model clickedItem = countrywiselist.get(position);
                Intent perCountryIntent = new Intent(context, EachCountryData.class);

                perCountryIntent.putExtra(COUNTRY_NAME, clickedItem.getCountry());
                perCountryIntent.putExtra(COUNTRY_CONFIRMED, clickedItem.getCases());
                perCountryIntent.putExtra(COUNTRY_ACTIVE, clickedItem.getActive());
                perCountryIntent.putExtra(COUNTRY_RECOVERED, clickedItem.getRecovered());
                perCountryIntent.putExtra(COUNTRY_DECEASED, clickedItem.getDeath());
                perCountryIntent.putExtra(COUNTRY_NEW_CONFIRMED, clickedItem.getToday_positive());
                perCountryIntent.putExtra(COUNTRY_NEW_DECEASED, clickedItem.getToday_death());
                perCountryIntent.putExtra(COUNTRY_FLAGURL, clickedItem.getFlag());

                context.startActivity(perCountryIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countrywiselist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView country_name;
        ImageView imageflag;
        LinearLayout linear_country_manner;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linear_country_manner= itemView.findViewById(R.id.linear_countrywise);
            imageflag= itemView.findViewById(R.id.imageflag);
            country_name= itemView.findViewById(R.id.tvCountriesName);

        }
    }
}
