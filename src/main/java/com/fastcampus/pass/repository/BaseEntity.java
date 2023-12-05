package com.fastcampus.pass.repository;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 공통 매핑 정보를 가진 Entity
 */
@Getter
@ToString
@MappedSuperclass // 상속받은 entity에서 아래 필드들을 컬럼으로 사용할 수 있습니다.
@EntityListeners(AuditingEntityListener.class) // Auditing 정보를 캡처하는 Listener입니다.
public abstract class BaseEntity {
    /** 생성일시 */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected LocalDateTime createdAt;

    /** 생성자 */
    @CreatedBy
    @Column(nullable = false, updatable = false, length = 100)
    protected String createdBy;

    /** 수정일시 */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(nullable = false)
    protected LocalDateTime modifiedAt;

    /** 수정자 */
    @LastModifiedBy
    @Column(nullable = false, length = 100)
    protected String modifiedBy;
}