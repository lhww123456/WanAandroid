package com.lhw.wanaandroid.bean;

import java.io.Serializable;
import java.util.List;

public class TreeData implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Children> children;

    private int courseId;

    private int id;

    private String name;

    private boolean userControlSetTop;

    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUserControlSetTop() {
        return userControlSetTop;
    }

    public void setUserControlSetTop(boolean userControlSetTop) {
        this.userControlSetTop = userControlSetTop;
    }

    @Override
    public String toString() {
        return "TreeData{" +
                "children=" + children +
                ", courseId=" + courseId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", userControlSetTop=" + userControlSetTop +
                '}';
    }
}
