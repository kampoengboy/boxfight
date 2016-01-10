package com.delapan3gp.boxfight;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by fausta on 09/01/16.
 */
public class ArenaSettings implements Serializable{
    private UUID idArena;
    private Integer imagePath;
    private Integer positon;

    public ArenaSettings(){
        idArena = UUID.randomUUID();
    }

    public UUID getId(){
        return idArena;
    }

    public void setImagePath(Integer ip){
        imagePath = ip;
    }

    public Integer getImagePath(){
        return imagePath;
    }

    public void setPositionActive(int pos){
        positon = pos;
    }

    public Integer getPositonActive(){
        return positon;
    }
}
