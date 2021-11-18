package com.django.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="task")
@AllArgsConstructor
@NoArgsConstructor
@Data
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
}
