package javaway.info.spaceplayer.flow;

import flow.ClassKey;
import javaway.info.spaceplayer.R;

/**
 * Created by максим on 14.10.2017.
 */

@Screen(R.layout.view_setting)
public class SettingScreen extends ClassKey {

    private int mState;

    public SettingScreen(int state) {
        mState = state;
    }

    public int getState() {
        return mState;
    }

    public void setState(int state) {
        mState = state;
    }
}
