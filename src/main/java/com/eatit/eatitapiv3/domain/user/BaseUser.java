package com.eatit.eatitapiv3.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "user")
public class BaseUser {

    @Id
    Long id;
}
