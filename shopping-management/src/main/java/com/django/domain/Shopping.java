package com.django.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.hibernate.annotations.Cache;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shopping")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Shopping extends Base{


    private String name;
    private String comment;
    private Boolean statut;
    private Boolean archived;
    private Boolean shared;
    private String saverName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(mappedBy = "shopping", cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    @OnDelete(action= OnDeleteAction.NO_ACTION)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Task>tasks = new HashSet<>();

    public Shopping() {
    }

    public Shopping(String id, Date createdAt, Date updatedAt, Date deletedAt, Boolean isDeleted, String name, String comment, Boolean statut, Boolean archived, Boolean shared, String saverName, Date date, Set<Task> tasks) {
        super(id, createdAt, updatedAt, deletedAt, isDeleted);
        this.name = name;
        this.comment = comment;
        this.statut = statut;
        this.archived = archived;
        this.shared = shared;
        this.saverName = saverName;
        this.date = date;
        this.tasks = tasks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getStatut() {
        return statut;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public Boolean getShared() {
        return shared;
    }

    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    public String getSaverName() {
        return saverName;
    }

    public void setSaverName(String saverName) {
        this.saverName = saverName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }


    @Override
    public String toString() {
        return "Shopping{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", statut=" + statut +
                ", archived=" + archived +
                ", shared=" + shared +
                ", saverName='" + saverName + '\'' +
                ", date=" + date +
                ", tasks=" + tasks +
                '}';
    }
}
