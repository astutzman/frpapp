package phillykeyspots.frpapp;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import phillykeyspots.frpapp.R;

/**
 * This preference activity has in its manifest declaration an intent filter for
 * the ACTION_MANAGE_NETWORK_USAGE action. This activity provides a settings UI
 * for users to specify network settings to control data usage.
 */
public class HTTPSettingsActivity extends PreferenceActivity
        implements
            OnSharedPreferenceChangeListener {

    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Loads the XML preferences file
         */
        addPreferencesFromResource(R.xml.preferences); 
    }

    @SuppressWarnings("deprecation")
	@Override
    protected void onResume() {
        super.onResume();
        /**
         * Registers a callback to be invoked whenever a user changes a preference
         */
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @SuppressWarnings("deprecation")
	@Override
    protected void onPause() {
        super.onPause();
        /**
         * Unregisters the listener set in onResume().
         */
        getPreferenceScreen()
                .getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
    /**
     * Fires when the user changes a preference
     */
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    	/**
    	 * Sets refreshDisplay to true so that when the user returns to the main
    	 * activity, the display refreshes to reflect the new settings
    	 */
        EventsActivity.refreshDisplay = true;
    }
}

