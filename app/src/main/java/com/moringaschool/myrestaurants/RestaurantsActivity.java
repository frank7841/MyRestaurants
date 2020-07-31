package com.moringaschool.myrestaurants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.AdapterView.*;

public class RestaurantsActivity extends AppCompatActivity {
    @BindView(R.id.locationTextView) TextView mLocationTextView;
    @BindView(R.id.listView) ListView mListView;
    public static final String TAG = RestaurantsActivity.class.getSimpleName();
//    private TextView mLocationTextView;
//    private ListView mListView;
    private String[] restaurants = new String[]{
            "Lilians", "Mother's Bistro",
            "Life of Pie", "Screen Door", "Luc Lac", "Sweet Basil",
            "Slappy Cakes", "Equinox", "Miss Delta's", "Andina",
            "Lardo", "Portland City Grill", "Fat Head's Brewery",
            "Chipotle", "Subway"
    };
    private String[] cuisines = new String[]{
            "Vegan Food", "Breakfast", "Fishs Dishs",
             "Scandinavian", "Coffee", "English Food",
            "Burgers", "Fast Food", "Noodle Soups", "Mexican",
            "BBQ", "Cuban", "Bar Food", "Sports Bar", "Breakfast",
            "Mexican"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
//        mListView =(ListView)findViewById(R.id.listView);
//        mLocationTextView = (TextView)findViewById(R.id.locationTextView);
        ButterKnife.bind(this);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, restaurants);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                Log.v(TAG, "In the onItemClickListener!");
                String restaurant = ((TextView)view).getText().toString();
                Toast.makeText(RestaurantsActivity.this, restaurant, Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the Restaurants near: "+ location);
        Log.d(TAG,"In the onCreate Method");
    }
}