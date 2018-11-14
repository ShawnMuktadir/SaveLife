package com.example.admin.savelife;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    CardView findBlood,findBloodInHospital,benefitDonor,beAdonor,post_facebook,aboutTheApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeObj();

        //set the cardview's onClickListener
        findBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FindBloodInApp.class);
                startActivity(intent);
            }
        });

        findBloodInHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FindBloodInHospital.class);
                startActivity(intent);
            }
        });

        benefitDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BenefitForAdonor.class);
                startActivity(intent);
            }
        });

        beAdonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,BeAdonor.class);
                startActivity(intent);
            }
        });

        post_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebookIntent = openFacebook(getApplicationContext());
                startActivity(facebookIntent);
            }
        });

        aboutTheApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AboutTheApp.class);
                startActivity(intent);
            }
        });




    }

    private void makeObj() {
        findBlood = (CardView)findViewById(R.id.findBlood);
        findBloodInHospital = (CardView)findViewById(R.id.findBloodInHospital);
        benefitDonor = (CardView)findViewById(R.id.benefitDonor);
        beAdonor = (CardView)findViewById(R.id.beAdonor);
        post_facebook = (CardView)findViewById(R.id.post_facebook);
        aboutTheApp = (CardView)findViewById(R.id.aboutTheApp);
    }

    //onBackPressed activity
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("EXIT");
        builder.setMessage("Are you sure ? ");
        builder.setIcon(R.drawable.exit);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    //launch facebook application activity
    public static Intent openFacebook(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://group/29886728382"));
        } catch (Exception e){

            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/?stype=lo&jlou=AfdUfB9enQJHWesJ41EspOuYGEPXJE6Y-4iif7TNOX-bJG7KoKyA7zFsMFgGaqhxVkLkk63Dmrrev0wUZj8D130iwsVk141fou833rH7WC1ovQ&smuh=21251&lh=Ac-TReKOb8XzjFyH"));
        }
    }
}
