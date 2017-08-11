import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.mvp.view.activity.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule(MainActivity.class);

    private Utils utils;

    @Before
    public void initUtils() {
        utils = new Utils(activityTestRule);
    }

    @Test
    public void checkIfViewsExist() {
        checkViews();
        utils.rotateScreen();
        checkViews();
    }

    private void checkViews() {
        onView(withText(activityTestRule.getActivity().getString(R.string.search_title))).check(matches(isDisplayed()));
        onView(withId(R.id.etSearch)).check(matches(isDisplayed()));
    }

    @Test
    public void checkKeyboardDispayed() {
        closeSoftKeyboard();
        checkViews();
    }

}
