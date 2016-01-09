package com.delapan3gp.boxfight;

import android.support.v4.app.Fragment;

public class GameSettingsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new GameSettingsFragment();
    }

}
