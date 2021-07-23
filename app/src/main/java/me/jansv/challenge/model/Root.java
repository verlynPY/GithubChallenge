package me.jansv.challenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Root{

    @SerializedName("node_id")
    @Expose
    public String node_id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("full_name")
    @Expose
    public String full_name;

    @SerializedName("private")
    @Expose
    public boolean privatee;

    @SerializedName("owner")
    @Expose
    public Owner owner;

    @SerializedName("html_url")
    @Expose
    public String html_url;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("fork")
    @Expose
    public boolean fork;

    @SerializedName("url")
    @Expose
    public String url;




    public int id;

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public boolean isPrivatee() {
        return privatee;
    }

    public void setPrivatee(boolean privatee) {
        this.privatee = privatee;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
