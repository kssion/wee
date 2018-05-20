package cc.nsurl.wee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class App {
    private String sid;
    private String name;
    private String path;
    private int type;
    private String repertory;
    @JsonIgnore
    private int state;

    App() {

    }

    public App(String sid, String name, String path, int type, String repertory) {
        this.sid = sid;
        this.name = name;
        this.path = path;
        this.type = type;
        this.repertory = repertory;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getType() {
        return type;
    }

    @JsonIgnore
    public String getTypeName() {
        switch (type) {
            case 0: return "git";
            case 1: return "svn";
            default: return "unknown";
        }
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRepertory() {
        return repertory;
    }

    public void setRepertory(String repertory) {
        this.repertory = repertory;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
