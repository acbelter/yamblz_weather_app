import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.ui.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTestTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    private Utils utils;

    @Before
    public void initUtils() {
        utils = new Utils(mActivityRule);
    }

    @Test
    public void checkIfViewsExist() {
        checkViews();
        utils.rotateScreen();
        checkViews();
    }

    private void checkViews() {
        if (!utils.isNetworkAvailable())
            return;
        onView(withText(mActivityRule.getActivity().getString(R.string.weather))).check(matches(isDisplayed()));
        onView(withId(R.id.swipe_refresh_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.temperature_text)).check(matches(isDisplayed()));
        onView(withId(R.id.units_text)).check(matches(isDisplayed()));
        onView(withId(R.id.location_text)).check(matches(isDisplayed()));
        onView(withId(R.id.weather_image)).check(matches(isDisplayed()));
        onView(withId(R.id.weather_view)).check(matches(isDisplayed()));
    }

    @Test
    public void checkOpenCloseAboutActivity() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withText(mActivityRule.getActivity().getString(R.string.about))).perform(click());
        pressBack();
        onView(withId(R.id.swipe_refresh_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void checkOpenCloseSettingsActivity() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withText(mActivityRule.getActivity().getString(R.string.settings))).perform(click());
        pressBack();
        onView(withId(R.id.swipe_refresh_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void checkOpenCloseSearchActivity() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.etSearchOnHeader)).perform(click());
        closeSoftKeyboard();
        pressBack();
        onView(withId(R.id.swipe_refresh_layout)).check(matches(isDisplayed()));
    }
}

