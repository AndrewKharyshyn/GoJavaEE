import java.util.Set;

public class Companies {
    private int companyID;
    private String companyName;
    private Set<Projects> projects;

    public Set<Projects> getProjects() {
        return projects;
    }

    public void setProjects(Set<Projects> projects) {
        this.projects = projects;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Companies{" +
                "companyID=" + companyID +
                ", companyName='" + companyName + '\'' +
                ", projects=" + projects +
                '}';
    }
}
