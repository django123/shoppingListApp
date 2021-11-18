package com.django.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@MappedSuperclass
public class Base implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "uuid", unique = true)
    private String id;

    @Temporal(TemporalType.DATE)
    @Column(name="created_at")
    @JsonProperty(value = "createdAt")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date createdAt;

    @Temporal(TemporalType.DATE)
    @Column(name="updated_at")
    @JsonProperty(value = "updatedAt")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date updatedAt;

    @Temporal(TemporalType.DATE)
    @Column(name="deleted_at")
    @JsonProperty(value = "deletedAt")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date deletedAt;

    @Column(name = "deleted")
    @JsonProperty(value = "isDeleted")
    Boolean isDeleted = false;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Base() {
    }

    public Base(String id, Date createdAt, Date updatedAt, Date deletedAt, Boolean isDeleted) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isDeleted = isDeleted;
    }

    @PrePersist
    public void prePersist() {
        id = UUID.randomUUID().toString();
        createdAt = new Date();
        updatedAt = new Date();
        isDeleted = false;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Date();
    }

    @PreRemove
    public void preRemove() {
        deletedAt = new Date();
        isDeleted = true;
    }
}
