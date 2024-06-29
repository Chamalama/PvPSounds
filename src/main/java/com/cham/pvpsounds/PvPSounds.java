package com.cham.pvpsounds;

import org.bukkit.plugin.java.JavaPlugin;

public final class PvPSounds extends JavaPlugin {

    public static PvPSounds sounds;

    @Override
    public void onEnable() {
        sounds = this;
        new SoundCanceller();
    }

    @Override
    public void onDisable() {

    }

    public static PvPSounds getSounds() {
        return sounds;
    }
}
