package com.schoolwork.analyticsguided;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = this;

        FirebaseAnalytics.getInstance(this).setCurrentScreen(this, "MainActivity", "test2");

        TextView textView = findViewById(R.id.text_view);
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
                bundle.putString(FirebaseAnalytics.Param.QUANTITY, "42");
                FirebaseAnalytics.getInstance(context).logEvent("Text_View_Clicked", bundle);
                Toast.makeText(context, "Working", Toast.LENGTH_SHORT).show();
            }
        };
        textView.setOnClickListener(listener);
    }
}
