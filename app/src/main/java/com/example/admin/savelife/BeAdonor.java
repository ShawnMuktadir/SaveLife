package com.example.admin.savelife;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BeAdonor extends AppCompatActivity {

    String[] location_names;
    Button locationBtn;
    EditText donorName,donorPhoneNumber;
    Button done_btn;
    String bloodGroup="";
    String locationSeletion="";
    DatabaseReference donorReference;
    RadioGroup bloodGroupRadioBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_be_adonor);

        //initialize firebase reference object
        donorReference = FirebaseDatabase.getInstance().getReference("DonorInformation");

        donorName = (EditText) findViewById(R.id.donorName);
        donorPhoneNumber = (EditText) findViewById(R.id.donorPhoneNumber);
        done_btn = (Button) findViewById(R.id.done_btn);
        bloodGroupRadioBtn = (RadioGroup) findViewById(R.id.bloodGoupRadioBtn);
        locationBtn = (Button) findViewById(R.id.locationSelectBtn);
        location_names = getResources().getStringArray(R.array.location_names);

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donorInformation();
            }
        });
    }

    public void locationBtn(View view) {

        AlertDialog.Builder builder =new AlertDialog.Builder(BeAdonor.this);
        builder.setTitle("Choose Your Location");
        builder.setItems(location_names, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                locationBtn.setText(location_names[i]);
                locationSeletion = location_names[i];
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
    public void donorInformation(){
        String donor_name = donorName.getText().toString().trim();
        String donor_phone_numer = donorPhoneNumber.getText().toString().trim();

        if (TextUtils.isEmpty(donor_name) || TextUtils.isEmpty(donor_phone_numer) ||
                bloodGroup.isEmpty()|| locationSeletion.isEmpty()){

            Toast.makeText(this, "Complete All Information Sir/Madam", Toast.LENGTH_SHORT).show();

        }else {

            String id = donorReference.push().getKey();
            DonorInformation information = new DonorInformation(id,donor_name,donor_phone_numer,bloodGroup,locationSeletion);
            donorReference.child(id).setValue(information);
            donorName.setText("");
            donorPhoneNumber.setText("");
            bloodGroupRadioBtn.clearCheck();
            locationBtn.setText(R.string.donor_location_btn_text);
            Toast.makeText(this, "Thank You Sir/Madam", Toast.LENGTH_SHORT).show();
        }
    }

    public void bloodSelection(View view) {
        boolean check = ((RadioButton)view).isChecked();
        switch (view.getId()){
            case R.id.aplus:
                bloodGroup = "A+";
                break;
            case R.id.aminus:
                bloodGroup = "A-";
                break;
            case R.id.bplus:
                bloodGroup = "B+";
                break;
            case R.id.bminus:
                bloodGroup = "B-";
                break;
            case R.id.oplus:
                bloodGroup = "O+";
                break;
            case R.id.ominus:
                bloodGroup = "O-";
                break;
            case R.id.abplus:
                bloodGroup = "AB+";
                break;
            case R.id.abminus:
                bloodGroup = "AB-";
                break;
            default:
                bloodGroup = "";
                break;
        }
    }
}
