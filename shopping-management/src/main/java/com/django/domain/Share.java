package com.django.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "share")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Share extends Base{

    private String id;

    private String shopId;
}
