package com.project.bemobi.challenge.model;

import javax.persistence.*;

@Entity
@Table(name = "URL")
public class URL {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "URL")
    private String url;

    @Column(name = "ALIAS")
    private String alias;

    @Column(name = "VIEW")
    private int view;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }
}
