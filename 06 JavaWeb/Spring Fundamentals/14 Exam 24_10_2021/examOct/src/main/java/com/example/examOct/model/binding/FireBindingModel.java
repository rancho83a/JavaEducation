package com.example.examOct.model.binding;

public class FireBindingModel {
    private Long attackerId;
    private String defenderName;

    public FireBindingModel() {
    }

    public Long getAttackerId() {
        return attackerId;
    }

    public FireBindingModel setAttackerId(Long attackerId) {
        this.attackerId = attackerId;
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
