package com.moringaschool.myrestaurants;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.moringaschool.myrestaurants.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityInstrumentationTest {
    @Rule
    public ActivityTestRule<MainActivity>activityActivityTestRule=new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validateEditText(){
        onView(withId(R.id.locationEditText)).perform(typeText("Namawanga"))
                    .check(matches(withText("Namawanga")));
    }
    @Test
    public void locationIsSentToRestaurantsActivity() {
        String location = "Namawanga";
        onView(withId(R.id.locationEditText)).perform(typeText(location)).perform(closeSoftKeyboard());
        try {
            Thread.sleep(250);
        }
        catch (InterruptedException e){
            System.out.println("got Interrupted");
        }
        onView(withId(R.id.findRestaurantsButton)).perform(click());
        onView(withId(R.id.locationTextView)).check(matches
                (withText("Here are all the Restaurants near: " + location)));
    }
}
