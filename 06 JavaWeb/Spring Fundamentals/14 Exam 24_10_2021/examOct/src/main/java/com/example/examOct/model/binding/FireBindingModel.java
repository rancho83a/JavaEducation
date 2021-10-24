package com.example.examOct.model.binding;

public class FireBindingModel {
    private String attackerName;
    private String defenderName;

    public FireBindingModel() {
    }

    public String getAttackerName() {
        return attackerName;
    }

    public FireBindingModel setAttackerName(String attackerName) {
        this.attackerName = attackerName;
        return this;
    }

    public String getDefenderName() {
        return defenderName;
    }

    public FireBindingModel setDefenderName(String defenderName) {
        this.defenderName = defenderName;
        return this;
    }
}
