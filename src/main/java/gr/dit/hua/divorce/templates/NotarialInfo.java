package gr.dit.hua.divorce.templates;

public class NotarialInfo {
    private String notarialActionId;

    private Integer id;

    public NotarialInfo() {
    }

    public NotarialInfo(String notarialActionId, Integer id) {
        this.notarialActionId = notarialActionId;
        this.id = id;
    }

    public String getNotarialActionId() {
        return notarialActionId;
    }

    public void setNotarialActionId(String notarialActionId) {
        this.notarialActionId = notarialActionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
