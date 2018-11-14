package com.example.admin.savelife;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter implements Filterable {

    private Context context;
    ArrayList<SingleRowHospital> originalArray, tempArray;
    private LayoutInflater inflater;
    CustomFilter customFilter;

    public CustomAdapter(Context context, ArrayList<SingleRowHospital> originalArray) {
        this.context = context;
        this.originalArray = originalArray;
        this.tempArray = originalArray;
    }

    @Override
    public int getCount() {
        return originalArray.size();
    }

    @Override
    public Object getItem(int position) {
        return originalArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.singlehospitalrowlist,viewGroup,false);

        TextView hospital_name = view.findViewById(R.id.hospitalName);
        TextView hospital_phone_number = view.findViewById(R.id.hospitalPhoneNumber);

        final SingleRowHospital singleRowHospital = originalArray.get(position);

        hospital_name.setText(originalArray.get(position).getName());
        hospital_phone_number.setText(originalArray.get(position).getPhoneNumber());

        ImageButton callTohospitalBtn = view.findViewById(R.id.callTohospital);
        callTohospitalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone_number = singleRowHospital.getPhoneNumber();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: " + phone_number));
                context.startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public Filter getFilter() {

        if (customFilter == null){
            customFilter = new CustomFilter();
        }
        return customFilter;
    }

    public class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            FilterResults filterResults = new FilterResults();
            if (charSequence != null && charSequence.length() >0 ){
                charSequence = charSequence.toString().trim();

                //initialize SingleRowHospital objects
                ArrayList<SingleRowHospital> singleRowHospitalArrayList = new ArrayList<>();

                for (int i = 0; i< tempArray.size(); i++ ){
                    if (tempArray.get(i).getName().toUpperCase().contains(charSequence)){
                        SingleRowHospital singleRowHospital = new SingleRowHospital(tempArray.get(i).getName(),
                                                                                    tempArray.get(i).getPhoneNumber());
                        singleRowHospitalArrayList.add(singleRowHospital);
                    }
                }
                filterResults.count = singleRowHospitalArrayList.size();
                filterResults.values = singleRowHospitalArrayList;
            }else {
                filterResults.count = tempArray.size();
                filterResults.values = tempArray;
            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            originalArray = (ArrayList<SingleRowHospital>) filterResults.values;
            notifyDataSetChanged();

        }
    }
}
