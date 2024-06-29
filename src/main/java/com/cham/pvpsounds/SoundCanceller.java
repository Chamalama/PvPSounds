package com.cham.pvpsounds;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.Sound;

public class SoundCanceller {

    private static final String[] blackListedSounds = new String[]{"ENTITY_PLAYER_ATTACK_SWEEP", "ENTITY_PLAYER_ATTACK_KNOCKBACK", "ENTITY_PLAYER_ATTACK_NODAMAGE", "ENTITY_PLAYER_ATTACK_STRONG", "ENTITY_PLAYER_ATTACK_CRIT"};

    private ProtocolManager protocolManager;

    public SoundCanceller() {
         protocolManager = ProtocolLibrary.getProtocolManager();

         protocolManager.addPacketListener(new PacketAdapter(PvPSounds.getSounds(), ListenerPriority.MONITOR, PacketType.Play.Server.NAMED_SOUND_EFFECT) {
             @Override
             public void onPacketSending(PacketEvent event) {
                event.setReadOnly(false);
                 PacketContainer container = event.getPacket();
                 Sound sound = container.getSoundEffects().read(0);
                 if(sound != null) {
                     String name = sound.name();
                     for(String s : blackListedSounds) {
                         if(name.equalsIgnoreCase(s)) {
                             event.setCancelled(true);
                         }
                     }
                 }
             }
         });
    }
}
