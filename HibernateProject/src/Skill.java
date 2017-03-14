import java.util.Set;

public class Skill {
    private int skillID;
    private String skill;
    private Set<Developer> developers;

    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }

    public Skill() {
    }

    public int getSkillID() {
        return skillID;
    }

    public void setSkillID(int skillID) {
        this.skillID = skillID;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "skillID=" + skillID +
                ", skill='" + skill + '\'' +
                ", developers=" + developers +
                '}';
    }
}
