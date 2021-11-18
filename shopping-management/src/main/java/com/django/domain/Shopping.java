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
@AllArgsConstructor
@NoArgsConstructor
@Data
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
}
