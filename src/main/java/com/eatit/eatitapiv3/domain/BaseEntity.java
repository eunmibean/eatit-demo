package com.eatit.eatitapiv3.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Long createdAt; // TODO: LocalDateTime 으로 변경하기

    @LastModifiedDate
    @Column(name = "updated_at")
    private Long updatedAt;
}
