package ru.brenlike.custombossapi.api;

import org.bukkit.OfflinePlayer;

public class MatchRecord {
    private final OfflinePlayer killer;
    private final String boss;
    private final double damage;

    public MatchRecord(OfflinePlayer p_4800_, String p_4801_, double p_4802_) {
        this.killer = p_4800_;
        this.boss = p_4801_;
        this.damage = p_4802_;
    }

    /**
     * Returns boss killer
     * @return killer
     */
    public OfflinePlayer killer() {
        return killer;
    }

    /**
     * Returns boss key
     * @return key
     */
    public String boss() {
        return boss;
    }

    /**
     * Returns recorded damage
     * @return damage
     */
    public double damage() {
        return damage;
    }
}
