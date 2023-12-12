package com.fastcampus.pass.repository.pass;

import com.fastcampus.pass.repository.BaseEntity;
import com.fastcampus.pass.repository.packaze.PackageEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "pass")
public class PassEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 DB에 위임합니다. (AUTO_INCREMENT)
    private Integer passSeq;

    private String userId;

    private Integer gymPeriod;
    private Integer countPt;

    protected PassEntity() {
    }

    private PassEntity(String userId,Integer gymPeriod, Integer countPt) {
        this.userId = userId;
        this.gymPeriod = gymPeriod;
        this.countPt = countPt;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassEntity that)) return false;
        return Objects.equals(passSeq, that.passSeq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passSeq);
    }

//    public static PassEntity of() {
//        return new PassEntity()
//    }
}