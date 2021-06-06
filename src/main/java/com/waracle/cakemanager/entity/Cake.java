package com.waracle.cakemanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Cake implements Serializable {

    private static final long serialVersionUID = -1798070786993154676L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false)
    @JsonIgnore
    private Long id;
    @Column(name = "TITLE", unique = false, nullable = false, length = 100)
    private String title;
    @Column(name = "DESCRIPTION", unique = false, nullable = false, length = 100)
    private String description;
    @Column(name = "IMAGE_URL", unique = false, nullable = false, length = 100)
    private String imageUrl;

    public Cake() {
    }

    public Cake(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return "/cake-photos/" + id + "/" + imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return this.id;
    }
}
