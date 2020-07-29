package com.moringaschool.myrestaurants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button mFindRestaurantButton;
    private EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFindRestaurantButton = (Button)findViewById(R.id.findRestaurantsButton);
        mLocationEditText = (EditText)findViewById(R.id.locationEditText);
        mFindRestaurantButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
//                Toast.makeText(MainActivity.this, "Hey Frank Lets do this", Toast.LENGTH_SHORT).show();
                String location = mLocationEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, RestaurantsActivity.class);
                intent.putExtra("location", location);
                Log.d(TAG, location);
                Toast.makeText(MainActivity.this, location, Toast.LENGTH_LONG).show();
                startActivity(intent);


            }
        });

    }
}