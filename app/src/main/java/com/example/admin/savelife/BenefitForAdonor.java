package com.example.admin.savelife;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class BenefitForAdonor extends AppCompatActivity {

    ImageButton pathaoBtn,uberBtn,shohozBtn,obhaiBtn;
    String appPackageNamePathao = "com.pathao.user";
    String appPackageNameUber = "com.ubercab";
    String appPackageNameShohoz = "com.shohoz.rides";
    String appPackageNameObhai = "com.obhai&hl=en";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benefit_for_adonor);

        pathaoBtn = (ImageButton)findViewById(R.id.pathaoBtn);
        pathaoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLaunchAnotherAppPathao();
            }
        });
        
        uberBtn = (ImageButton)findViewById(R.id.uberBtn);
        uberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLaunchAnotherAppUber();
            }
        });

        shohozBtn = (ImageButton)findViewById(R.id.shohozBtn);
        shohozBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLaunchAnotherAppShohoz();
            }
        });

        obhaiBtn = (ImageButton)findViewById(R.id.obhaiBtn);
        obhaiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLaunchAnotherAppObhai();
            }
        });
    }

    //for pathao service
    private void onLaunchAnotherAppPathao() {

        Intent intent = getPackageManager().getLaunchIntentForPackage(appPackageNamePathao);
        if (intent != null){
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else {
            onGoToAnotherInAppStore(intent, appPackageNamePathao);
        }
    }

    //for uber service
    private void onLaunchAnotherAppUber() {

        Intent intent = getPackageManager().getLaunchIntentForPackage(appPackageNameUber);
        if (intent != null){
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else {
            onGoToAnotherInAppStore(intent, appPackageNameUber);
        }

    }

    //for shohoz service
    private void onLaunchAnotherAppShohoz() {

        Intent intent = getPackageManager().getLaunchIntentForPackage(appPackageNameShohoz);
        if (intent != null){
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else {
            onGoToAnotherInAppStore(intent, appPackageNameShohoz);
        }

    }

    //for obhai service
    private void onLaunchAnotherAppObhai() {

        Intent intent = getPackageManager().getLaunchIntentForPackage(appPackageNameObhai);
        if (intent != null){
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else {
            onGoToAnotherInAppStore(intent, appPackageNameObhai);
        }
    }

    public void onGoToAnotherInAppStore(Intent intent, String appPackageName) {

        try {

            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id=" + appPackageName));
            startActivity(intent);

        } catch (android.content.ActivityNotFoundException anfe) {

            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName));
            startActivity(intent);

        }

    }
}
