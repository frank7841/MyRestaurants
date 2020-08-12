package com.moringaschool.myrestaurants.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.myrestaurants.Constants;
import com.moringaschool.myrestaurants.R;
import com.moringaschool.myrestaurants.adapters.RestaurantListAdapter;
import com.moringaschool.myrestaurants.models.Business;
import com.moringaschool.myrestaurants.network.YelpApi;
import com.moringaschool.myrestaurants.network.YelpClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantListActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    private String mRecentAddress;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    private RestaurantListAdapter mAdapter;
    public static final String TAG = RestaurantListActivity.class.getSimpleName();
//    private TextView mLocationTextView;
//    private ListView mListView;
    public List<Business> restaurants;
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_restaurants);
    ButterKnife.bind(this);

    mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
    String washington="washington";
    Call<YelpBusinessesSearchResponse> call;
    YelpApi client = YelpClient.getClient();
    Intent intent = getIntent();
    String location = intent.getStringExtra("location");

    if (mRecentAddress != null) {
        //if there is, we pass it in our api
        call = client.getRestaurants(mRecentAddress, "restaurants");
    } else {
        //otherwise we get it from the user input directly
        call = client.getRestaurants(location, "restaurants");
    }






//    Call<YelpBusinessesSearchResponse> call = client.getRestaurants(location, "restaurants");

    call.enqueue(new Callback<YelpBusinessesSearchResponse>() {

        @Override
        public void onResponse(Call<YelpBusinessesSearchResponse> call, Response<YelpBusinessesSearchResponse> response) {
            hideProgressBar();

            if (response.isSuccessful()) {
                restaurants = response.body().getBusinesses();
                mAdapter = new RestaurantListAdapter(RestaurantListActivity.this, restaurants);
                mRecyclerView.setAdapter(mAdapter);
                RecyclerView.LayoutManager layoutManager =
                        new LinearLayoutManager(RestaurantListActivity.this);
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setHasFixedSize(true);

                showRestaurants();
            } else {
                showUnsuccessfulMessage();
            }
        }
        @Override
        public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {
            hideProgressBar();
               showFailureMessage();
        }

    });


}

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showRestaurants() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}