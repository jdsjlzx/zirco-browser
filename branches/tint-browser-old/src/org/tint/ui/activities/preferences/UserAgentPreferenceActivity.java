package org.tint.ui.activities.preferences;

import org.tint.R;
import org.tint.utils.Constants;

import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * Spinner activity allowing to choose an user-agent.
 */
public class UserAgentPreferenceActivity extends BaseSpinnerCustomPreferenceActivity {

	@Override
	protected int getSpinnerPromptId() {		
		return R.string.UserAgentPreferenceActivity_PromptTitle;
	}

	@Override
	protected int getSpinnerValuesArrayId() {		
		return R.array.UserAgentValues;
	}

	@Override
	protected void onOk() {
		Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
    	editor.putString(Constants.PREFERENCES_BROWSER_USER_AGENT, mCustomEditText.getText().toString());
    	editor.commit();
	}

	@Override
	protected void onSpinnerItemSelected(int position) {
		switch (position) {
		case 0: mCustomEditText.setEnabled(false); mCustomEditText.setText(Constants.USER_AGENT_DEFAULT); break;
		case 1: mCustomEditText.setEnabled(false); mCustomEditText.setText(Constants.USER_AGENT_DESKTOP); break;
		case 2: {
			mCustomEditText.setEnabled(true);
			
			if ((mCustomEditText.getText().toString().equals(Constants.USER_AGENT_DEFAULT)) ||
					(mCustomEditText.getText().toString().equals(Constants.USER_AGENT_DESKTOP))) {					
				mCustomEditText.setText(null);
			}
			break;
		}
		default: mCustomEditText.setEnabled(false); mCustomEditText.setText(Constants.USER_AGENT_DEFAULT); break;
		}
	}

	@Override
	protected void setSpinnerValueFromPreferences() {
		String currentUserAgent = PreferenceManager.getDefaultSharedPreferences(this).getString(Constants.PREFERENCES_BROWSER_USER_AGENT, Constants.USER_AGENT_DEFAULT);
		
		if (currentUserAgent.equals(Constants.USER_AGENT_DEFAULT)) {
			mSpinner.setSelection(0);
			mCustomEditText.setEnabled(false);
			mCustomEditText.setText(Constants.USER_AGENT_DEFAULT);
		} else if (currentUserAgent.equals(Constants.USER_AGENT_DESKTOP)) {
			mSpinner.setSelection(1);
			mCustomEditText.setEnabled(false);
			mCustomEditText.setText(Constants.USER_AGENT_DESKTOP);
		} else {
			mSpinner.setSelection(2);
			mCustomEditText.setEnabled(true);
			mCustomEditText.setText(currentUserAgent);					
		}
	}

}
