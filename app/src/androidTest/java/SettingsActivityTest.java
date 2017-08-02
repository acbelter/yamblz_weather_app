import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SettingsActivityTest {

    @Rule
//    public ActivityTestRule<SettingsActivity> mActivityRule =
//            new ActivityTestRule(SettingsActivity.class);

    private Utils utils;

//    @Before
//    public void initUtils() {
//        utils = new Utils(mActivityRule);
//    }

    @Test
    public void checkIfViewsExist() {
        checkViews();
        utils.rotateScreen();
        checkViews();
    }

    private void checkViews() {
//        onView(withText(mActivityRule.getActivity().getString(R.string.settings))).check(matches(isDisplayed()));
//        onView(withText(mActivityRule.getActivity().getString(R.string.weather_settings))).check(matches(isDisplayed()));
//        onView(withText(mActivityRule.getActivity().getString(R.string.weather_update_setting))).check(matches(isDisplayed()));
//        onView(withText(mActivityRule.getActivity().getString(R.string.update_interval))).check(matches(isDisplayed()));
    }
}
