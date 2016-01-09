package com.delapan3gp.boxfight;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by fausta on 09/01/16.
 */
public class ArenaSettingsLab {

    private static ArenaSettingsLab sArenaLab;
    private Context mAppContext;
    private ArrayList<ArenaSettings> mArenas;
    private ArenaSettings arena;

    private void setArenaLab(Integer ip){
        arena = new ArenaSettings();
        arena.setImagePath(ip);
        mArenas.add(arena);
    }

    private ArenaSettingsLab(Context c){
        mAppContext = c;
        mArenas = new ArrayList<>();
        setArenaLab(R.drawable.bathroom_arena);
        setArenaLab(R.drawable.hangingcloth_arena);
        setArenaLab(R.drawable.lampminion_arena);
        setArenaLab(R.drawable.winter_arena);
    }

    public static ArenaSettingsLab get(Context c){
        if (sArenaLab == null){
            sArenaLab = new ArenaSettingsLab(c.getApplicationContext());
        }
        return sArenaLab;
    }

    public ArrayList<ArenaSettings> getArenas(){
        return mArenas;
    }

    public ArenaSettings getArena(UUID id){
        for (ArenaSettings a : mArenas){
            if (a.getId().equals(id)){
                return a;
            }
        }
        return null;
    }

}
