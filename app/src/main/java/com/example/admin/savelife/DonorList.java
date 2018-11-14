package com.example.admin.savelife;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class DonorList extends ArrayAdapter<DonorInformation> {

    private Activity context;
    private List<DonorInformation> donorInformationList;

    public DonorList(Activity context, List<DonorInformation> donorInformationList) {
        super(context, R.layout.single_list_of_find_blood, donorInformationList);
        this.context = context;
        this.donorInformationList = donorInformationList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.single_list_of_find_blood,null,true);

        //initialize objects for single_list_of_blood.xml
        TextView donorName = view.findViewById(R.id.findBloodDonorNmae);
        TextView donorPhoneNumber = view.findViewById(R.id.findBloodDonorPhoneNumber);
        TextView donorBloodGroup = view.findViewById(R.id.findBloodDonorBloodGroup);
        TextView donorLocation = view.findViewById(R.id.findBloodDonorLocation);
        ImageButton phoneCallBtn = view.findViewById(R.id.phoneCallBtn);

        //now collecting information from DonorInformation class
        final DonorInformation information = donorInformationList.get(position);

        donorName.setText(information.getDonorName());
        donorPhoneNumber.setText(information.getDonorPhoneNumber());
        donorBloodGroup.setText(information.getDonorBloodGroup());
        donorLocation.setText(information.getDonorLocation());
        phoneCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone_number = information.getDonorPhoneNumber();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: "+phone_number));
                context.startActivity(intent);
            }
        });

        return view;
    }
}
