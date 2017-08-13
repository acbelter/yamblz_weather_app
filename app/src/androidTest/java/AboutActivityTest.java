import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.mvp.view.activity.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AboutActivityTest {

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
        onView(withText(mActivityRule.getActivity().getString(R.string.about))).check(matches(isDisplayed()));
        onView(withId(R.id.icon)).check(matches(isDisplayed()));
        onView(withId(R.id.app_name_text)).check(matches(isDisplayed()));
        onView(withId(R.id.version_text)).check(matches(isDisplayed()));
        onView(withId(R.id.images_license_text)).check(matches(isDisplayed()));
    }

    @Test
    public void checkAppNameText() {
        onView(withId(R.id.app_name_text)).check(matches(withText(mActivityRule.getActivity().getString(R.string.app_name))));
    }

    @Test
    public void checkVersionText() {
        onView(withId(R.id.version_text))
                .check(matches(withText(mActivityRule.getActivity().getString(R.string.version_test))));
    }

    @Test
    public void checLicenseText() {
        onView(withId(R.id.images_license_text)).check(matches(withText(mActivityRule.getActivity().getString(R.string.weather_images_license_text))));
    }
}