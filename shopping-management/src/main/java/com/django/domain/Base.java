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

@AllArgsConstructor
@NoArgsConstructor
@Data
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
