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
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SettingsActivityTest {

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
        onView(withText(mActivityRule.getActivity().getString(R.string.settings))).check(matches(isDisplayed()));
        onView(withText(mActivityRule.getActivity().getString(R.string.update_interval))).check(matches(isDisplayed()));
    }
}
