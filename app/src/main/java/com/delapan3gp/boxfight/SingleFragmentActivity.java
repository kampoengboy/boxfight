package com.delapan3gp.boxfight;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public abstract class SingleFragmentActivity extends FragmentActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        if (fragment == null){
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }

    @Override
    public void onBackPressed(){
        if (createFragment().getClass() == ArenaFragment.class){
            //when user press back suddenly
            ArenaFragment.timer.cancel();
            super.onBackPressed();
        }
        else if(createFragment().getClass() == HomeFragment.class){
            FragmentManager fm = getSupportFragmentManager();
            DialogExitFragment dialog = new DialogExitFragment();
            dialog.show(fm, null);
        }
        else{ //Back From GameSettings
            if (GameSettingsFragment.firstTimerRadioBtn.isChecked()){
                ArenaSettingsLab.get(SingleFragmentActivity.this).setTimerSelected(20000);
                ArenaSettingsLab.get(SingleFragmentActivity.this).setCurrentTimerPos(0);

            }
            else if (GameSettingsFragment.secondTimerRadioBtn.isChecked()){
                ArenaSettingsLab.get(SingleFragmentActivity.this).setTimerSelected(40000);
                ArenaSettingsLab.get(SingleFragmentActivity.this).setCurrentTimerPos(1);
            }
            else if (GameSettingsFragment.thirdTimerRadioBtn.isChecked()){
                ArenaSettingsLab.get(SingleFragmentActivity.this).setTimerSelected(80000);
                ArenaSettingsLab.get(SingleFragmentActivity.this).setCurrentTimerPos(2);
            }
            else{
                ArenaSettingsLab.get(SingleFragmentActivity.this).setTimerSelected(0);
                ArenaSettingsLab.get(SingleFragmentActivity.this).setCurrentTimerPos(3);
            }
            super.onBackPressed();
        }
    }
}
