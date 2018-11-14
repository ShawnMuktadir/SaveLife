package com.example.admin.savelife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class FindBloodInHospital extends AppCompatActivity implements TextWatcher {

    EditText searchEditText;
    ListView list_of_hospital;
    String[] hospitalName;
    String[] hositalPhonenumber;
    ArrayList<SingleRowHospital> singleRowHospital_ArrayList;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_blood_in_hospital);
        SingleRowHospital singleRowHospital;

        makeObj();
        searchEditText.addTextChangedListener(this);
        //get hospital name and phone numbers
        hospitalName = getResources().getStringArray(R.array.hospital_Name);
        hositalPhonenumber = getResources().getStringArray(R.array.hospital_phone_number);

        singleRowHospital_ArrayList = new ArrayList<>();

        for (int i = 0; i < hospitalName.length; i++){
            singleRowHospital = new SingleRowHospital(hospitalName[i], hositalPhonenumber[i]);
            singleRowHospital_ArrayList.add(singleRowHospital);
        }

        customAdapter = new CustomAdapter(this,singleRowHospital_ArrayList);
        list_of_hospital.setAdapter(customAdapter);

    }

    private void makeObj() {
        searchEditText = (EditText)findViewById(R.id.searchEditText);
        list_of_hospital = (ListView)findViewById(R.id.list_of_hospital);
    }

    @Override
    public void beforeTextChanged(CharSequence sequence, int start, int count, int after) {
            this.customAdapter.getFilter().filter(sequence);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
