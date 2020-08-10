package com.moringaschool.myrestaurants.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.moringaschool.myrestaurants.Constants;
import com.moringaschool.myrestaurants.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Shared Preference
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    @BindView(R.id.findRestaurantsButton)Button mFindRestaurantButton;
    @BindView(R.id.locationEditText) EditText mLocationEditText;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;
    public static final String TAG = MainActivity.class.getSimpleName();
//    private Button mFindRestaurantButton;
//    private EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        mFindRestaurantButton = (Button)findViewById(R.id.findRestaurantsButton);
//        mLocationEditText = (EditText)findViewById(R.id.locationEditText);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        mFindRestaurantButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
//    Toast.makeText(MainActivity.this, "Hey Frank Lets do this", Toast.LENGTH_SHORT).show();
        if( v == mFindRestaurantButton) {
            String location = mLocationEditText.getText().toString();
            //save location if empty
            if(!(location).equals("")) {
                addToSharedPreferences(location);
            }
            Intent intent = new Intent(MainActivity.this, RestaurantListActivity.class);
            intent.putExtra("location", location);
            Log.d(TAG, location);
            Toast.makeText(MainActivity.this, location, Toast.LENGTH_LONG).show();
            startActivity(intent);
        }

    }///created addTosharedPreference method
    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }
}