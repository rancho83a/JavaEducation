package app.skills;

import java.util.HashMap;
import java.util.Map;

public class Invoker {
    private Map<String, ExecuteSkill> skills;

    public Invoker(){
        init();
    }
    public void castSpell(String clazz) {
        skills.get(clazz).execute();
    }
    private void init(){
        this.skills = new HashMap<>();
        skills.put("rouge", new Ambush());
        skills.put("mage", new FireBall());
        skills.put("warlock", new Drainlife());

    }
}
