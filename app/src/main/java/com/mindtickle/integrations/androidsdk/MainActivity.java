package com.mindtickle.integrations.androidsdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mindtickle.integrations.android_sdk.MindTickle;
import com.mindtickle.integrations.android_sdk.exceptions.MindTickleNotInitializedException;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MindTickle.initialize(getApplicationContext(),"rachit.idea08.mindtickle.com","cc6ce537a8295708cbece6b715a242ee8d4d482222cdd4e12975b4792e4443a71a72135a152972506fe55777c34c4332");
        try {
            MindTickle.setUserEmail("rachit.agarwal@mindtickle.com");
        } catch (JSONException | MindTickleNotInitializedException e) {
            e.printStackTrace();
        }
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    MindTickle.openMindTickle();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
