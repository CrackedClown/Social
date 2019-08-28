package com.finalassignment.social.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "role")
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int roleId;

    @Column(name = "role")
    String role;
}
