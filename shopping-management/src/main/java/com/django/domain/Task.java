package com.django.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="task")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Task extends Base{


    @Column(name = "name")
    @NotNull
    private String name;
    private String description;
    private Boolean status;
    @ManyToOne(fetch= FetchType.EAGER, optional = false)
    @JsonIgnoreProperties("tasks")
    private Shopping shopping;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Shopping getShopping() {
        return shopping;
    }

    public void setShopping(Shopping shopping) {
        this.shopping = shopping;
    }

    public Task() {
    }

    public Task(String id, Date createdAt, Date updatedAt, Date deletedAt, Boolean isDeleted, @NotNull String name, String description, Boolean status, Shopping shopping) {
        super(id, createdAt, updatedAt, deletedAt, isDeleted);
        this.name = name;
        this.description = description;
        this.status = status;
        this.shopping = shopping;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", shopping=" + shopping +
                '}';
    }
}
