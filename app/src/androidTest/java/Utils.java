import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

class Utils {

    private final ActivityTestRule activityTestRule;

    Utils(ActivityTestRule activityTestRule) {
        this.activityTestRule = activityTestRule;
    }

    void rotateScreen() {
        int orientation = InstrumentationRegistry.getTargetContext().getResources().getConfiguration().orientation;

        activityTestRule.getActivity().setRequestedOrientation(
                (orientation == Configuration.ORIENTATION_PORTRAIT) ?
                        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager = (ConnectivityManager) activityTestRule.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetworkInfo != null && activeNetworkInfo.isConnected());
    }
}
