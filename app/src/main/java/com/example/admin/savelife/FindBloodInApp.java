package com.example.admin.savelife;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FindBloodInApp extends AppCompatActivity {

    Button bloodFilter,locationFilter,filter;
    ListView listView;
    DatabaseReference databaseReference;
    List<DonorInformation> donorInformations;
    ImageButton phoneCallBtn;
    String[] bloodGroupName;
    String bloodSelectionBtn = "";
    String[] locationsSelectionName;
    String locationSelectionBtn="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_blood_in_app);
        bloodFilter = (Button)findViewById(R.id.bloodFilter);
//        locationFilter = (Button)findViewById(R.id.locationFilter);
        filter = (Button)findViewById(R.id.filter);
        listView = (ListView)findViewById(R.id.findBloodListView);

        //initialize donar information object
        donorInformations = new ArrayList<>();

        //initialize DatabaseReferene object
        databaseReference = FirebaseDatabase.getInstance().getReference("DonorInformation");

        //get bloodgroup and location array
        bloodGroupName = getResources().getStringArray(R.array.bloodGroupName);
        locationsSelectionName = getResources().getStringArray(R.array.location_names);



    }

    @Override
    protected void onStart() {
        super.onStart();

        //set databaseReference er addValueEventListener
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                donorInformations.clear();
                for (DataSnapshot donor : dataSnapshot.getChildren()){
                    DonorInformation donorInformation =  donor.getValue(DonorInformation.class);
                    donorInformations.add(donorInformation);
                }
                DonorList donorListAdapter = new DonorList(FindBloodInApp.this,donorInformations);
                listView.setAdapter(donorListAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(),"Cancelled!!!",Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void selectBloodgroup(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(FindBloodInApp.this);
        builder.setTitle("Choose Blood Group");
        builder.setItems(bloodGroupName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                bloodFilter.setText(bloodGroupName[i]);
                bloodSelectionBtn = bloodGroupName[i];
            }
        });
        builder.setCancelable(false);

        builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void selectLocationBtn(View view) {

        AlertDialog.Builder builder =new AlertDialog.Builder(FindBloodInApp.this);
        builder.setTitle("Choose Your Location");
        builder.setItems(locationsSelectionName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                locationFilter.setText(locationsSelectionName[i]);
                locationSelectionBtn = locationsSelectionName[i];
            }
        });

        builder.setCancelable(false);

        builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void filterBtn(final View view) {

        final ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                donorInformations.clear();
                for (DataSnapshot donor: dataSnapshot.getChildren()){
                    DonorInformation information = donor.getValue(DonorInformation.class);
                    donorInformations.add(information);
                }
                DonorList donorAdapter = new DonorList(FindBloodInApp.this,donorInformations);
                listView.setAdapter(donorAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(),"Cancelled!!!",Toast.LENGTH_SHORT).show();

            }
        };

        final String buttonText = bloodFilter.getText().toString();

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Query query = databaseReference.orderByChild("donorBloodGroup").equalTo(buttonText);
                query.addListenerForSingleValueEvent(valueEventListener);

            }
        });



//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                donorInformations.clear();
//                for (DataSnapshot donor: dataSnapshot.getChildren()){
//                    DonorInformation information = donor.getValue(DonorInformation.class);
//                    donorInformations.add(information);
//                }
//                DonorList donorAdapter = new DonorList(FindBloodInApp.this,donorInformations);
//                listView.setAdapter(donorAdapter);
//                Toast.makeText(getApplicationContext(),"Successfully Filtered!!!",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(getApplicationContext(),"Cancelled!!!",Toast.LENGTH_SHORT).show();
//            }
//        });
    }




}


