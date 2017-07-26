import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

public class Utils {

    private ActivityTestRule activityTestRule;

    public Utils(ActivityTestRule activityTestRule) {
        this.activityTestRule = activityTestRule;
    }

    public void rotateScreen() {
        int orientation = InstrumentationRegistry.getTargetContext().getResources().getConfiguration().orientation;

        activityTestRule.getActivity().setRequestedOrientation(
                (orientation == Configuration.ORIENTATION_PORTRAIT) ?
                        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
