package Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.track_covid.R;

import java.util.ArrayList;

import Activities.EveryStateData;
import Model.State_Model;

import static Activities.Constant.STATE_ACTIVE;
import static Activities.Constant.STATE_CONFIRMED;
import static Activities.Constant.STATE_CONFIRMED_NEW;
import static Activities.Constant.STATE_DEATH;
import static Activities.Constant.STATE_DEATH_NEW;
import static Activities.Constant.STATE_NAME;
import static Activities.Constant.STATE_RECOVERED;
import static Activities.Constant.STATE_RECOVERED_NEW;


public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder> {

        public ArrayList<State_Model> stateModelList;
        public Context context;
        String searchtext ="";


    public StateAdapter( Context context, ArrayList<State_Model> stateModelList)
    {
        this.context = context;
        this.stateModelList = stateModelList;
    }

    public void filterlist(ArrayList<State_Model> filterlist, String text)
    {
        stateModelList = filterlist;
        this.searchtext= text;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(context).inflate(R.layout.custom_state, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        final State_Model model = stateModelList.get(position);

        String States_name = model.getState();
        holder.textView.setText(model.getState());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                State_Model clickedItem = stateModelList.get(position);

                Intent intent = new Intent(context, EveryStateData.class);

                intent.putExtra(STATE_NAME, clickedItem.getState());
                intent.putExtra(STATE_CONFIRMED, clickedItem.getState_cases());
                intent.putExtra(STATE_CONFIRMED_NEW, clickedItem.getState_Today_positive());
                intent.putExtra(STATE_ACTIVE, clickedItem.getState_Active());
                intent.putExtra(STATE_DEATH, clickedItem.getState_Death());
                intent.putExtra(STATE_DEATH_NEW, clickedItem.getState_Active());
                intent.putExtra(STATE_RECOVERED, clickedItem.getState_Recovered());
                intent.putExtra(STATE_RECOVERED_NEW, clickedItem.getState_Today_Recovered());
                // intent.putExtra(STATE_LAST_UPDATE, clickedItem.getLastupdate());

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return stateModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView= itemView.findViewById(R.id.state_Manner);
            relativeLayout= itemView.findViewById(R.id.state_layout);
        }
    }
}
