package com.example.admin.savelife;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class AboutTheApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_the_app);
    }

    public void AboutTheAppBtn(View view) {
        switch (view.getId()){
            case R.id.sendFeedback:

                Intent intent = new Intent(getApplicationContext(),SendFeedback.class);
                startActivity(intent);
                break;

            case R.id.shareTheApp:

                Intent shareintent= new Intent(Intent.ACTION_SEND);
                shareintent.setType("text/plain");
                String subject = "My new Android App";
                String text = "Try my new application : ";
                String applink = " ";
                shareintent.putExtra(Intent.EXTRA_SUBJECT,subject);
                shareintent.putExtra(Intent.EXTRA_SUBJECT,text);
                shareintent.putExtra("Try this application: ",applink);
                startActivity(Intent.createChooser(shareintent,"Share Via "));
                break;

            case R.id.appDeveloper:

                AlertDialog.Builder builder = new AlertDialog.Builder(AboutTheApp.this);
                View v = LayoutInflater.from(AboutTheApp.this).inflate(R.layout.appdeveloper,null);
                builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                ImageButton facebook = v.findViewById(R.id.facebook);
                facebook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent facebookIntent = OpenFacebook(AboutTheApp.this);
                        startActivity(facebookIntent);
                    }
                });

                ImageButton twitter = v.findViewById(R.id.twitter);
                twitter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent twitterIntent = OpenTwitter(AboutTheApp.this);
                        startActivity(twitterIntent);
                    }
                });

                ImageButton gmail = v.findViewById(R.id.gmail);
                gmail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto","shawn.muktadir03@gmail.com", null));
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, "");
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "About Blood App");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                        startActivity(Intent.createChooser(emailIntent, "Send email..."));
                    }
                });
                builder.setView(v);
                builder.show();

                break;

                default:

                    Toast.makeText(this, "Thank You Sir/Madam", Toast.LENGTH_SHORT).show();

        }
    }
    public static Intent OpenFacebook(Context context){

        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana",0);
            return new Intent(Intent.ACTION_VIEW,Uri.parse("https://facebook.com/muktadirshawn"));
        } catch (PackageManager.NameNotFoundException e) {

            return new Intent(Intent.ACTION_VIEW,Uri.parse("https://facebook.com/muktadirshawn"));
        }

    }
    public static Intent OpenTwitter(Context context){

        try {
            context.getPackageManager().getPackageInfo("com.twitter.android",0);
            return new Intent(Intent.ACTION_VIEW,Uri.parse("https://twitter.com/shawnmuktadir"));
        } catch (PackageManager.NameNotFoundException e) {

            return new Intent(Intent.ACTION_VIEW,Uri.parse("https://twitter.com/shawnmuktadir"));
        }

    }
}






