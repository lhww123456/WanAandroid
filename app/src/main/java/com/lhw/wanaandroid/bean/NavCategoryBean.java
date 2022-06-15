package com.lhw.wanaandroid.bean;

import java.util.List;

public class NavCategoryBean {
    private List<ArticleDetail> articles;
    private int cid;
    private String name;

    public List<ArticleDetail> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleDetail> articleDetails) {
        this.articles = articleDetails;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NavCategoryBean{" +
                "articles=" + articles +
                ", cid=" + cid +
                ", name='" + name + '\'' +
                '}';
    }
}
