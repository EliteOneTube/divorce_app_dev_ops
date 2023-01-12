package gr.dit.hua.divorce.templates;


public class DivorceInfo {

    private Integer numberOfChildren;

    private Integer childSupport;

    private Boolean restoreName;

    private String lawyer1;

    private String lawyer2;

    private String spouse1;

    private String spouse2;

    private String notary;

    public DivorceInfo() {
    }

    public DivorceInfo(Integer numberOfChildren, Integer childSupport, Boolean restoreName, String lawyer1, String lawyer2, String spouse1, String spouse2, String notary) {
        this.numberOfChildren = numberOfChildren;
        this.childSupport = childSupport;
        this.restoreName = restoreName;
        this.lawyer1 = lawyer1;
        this.lawyer2 = lawyer2;
        this.spouse1 = spouse1;
        this.spouse2 = spouse2;
        this.notary = notary;
    }

    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(Integer numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public Integer getChildSupport() {
        return childSupport;
    }

    public void setChildSupport(Integer childSupport) {
        this.childSupport = childSupport;
    }

    public Boolean getRestoreName() {
        return restoreName;
    }

    public void setRestoreName(Boolean restoreName) {
        this.restoreName = restoreName;
    }

    public String getLawyer1() {
        return lawyer1;
    }

    public void setLawyer1(String lawyer1) {
        this.lawyer1 = lawyer1;
    }

    public String getLawyer2() {
        return lawyer2;
    }

    public void setLawyer2(String lawyer2) {
        this.lawyer2 = lawyer2;
    }

    public String getSpouse1() {
        return spouse1;
    }

    public void setSpouse1(String spouse1) {
        this.spouse1 = spouse1;
    }

    public String getSpouse2() {
        return spouse2;
    }

    public void setSpouse2(String spouse2) {
        this.spouse2 = spouse2;
    }

    public String getNotary() {
        return notary;
    }

    public void setNotary(String notary) {
        this.notary = notary;
    }
}
