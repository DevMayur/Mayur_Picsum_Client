package com.mayurkakade.mayur_picsum_client.models;

public class Model {
    String id;
    String authorName;
    String imgUrl;

    public Model() {
    }

    public Model(String id, String authorName, String imgUrl) {
        this.id = id;
        this.authorName = authorName;
        this.imgUrl = imgUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
