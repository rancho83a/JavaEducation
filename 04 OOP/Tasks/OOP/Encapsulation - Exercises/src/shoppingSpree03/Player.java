package shoppingSpree03;

public class Player {
    private String name;
    private int endurance;
    private int sprint;
    private int dribble;
    private int passing;
    private int shooting;

    public Player(String name, int endurance, int sprint, int dribble, int passing, int shooting) {
        this.setName(name);
        this.setEndurance(endurance);
        this.setSprint(sprint);
        this.setDribble(dribble);
        this.setPassing(passing);
        this.setShooting(shooting);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        ValidatorData.nameValidator(name);
        this.name = name.trim();
    }

    private void setEndurance(int endurance) {
        ValidatorData.statValidator(endurance,"Endurance");
        this.endurance = endurance;
    }

    private void setSprint(int sprint) {
        ValidatorData.statValidator(sprint,"Sprint");
        this.sprint = sprint;
    }

    private void setDribble(int dribble) {
        ValidatorData.statValidator(dribble,"Dribble");
        this.dribble = dribble;
    }

    private void setPassing(int passing) {
        ValidatorData.statValidator(passing,"Passing");
        this.passing = passing;
    }

    private void setShooting(int shooting) {
        ValidatorData.statValidator(shooting,"Shooting");
        this.shooting = shooting;
    }

    public double overallSkillLevel(){

        return (this.endurance+this.dribble+this.passing+this.shooting+this.sprint) / 5.00;
    }
}
