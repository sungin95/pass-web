package com.fastcampus.pass.repository.packaze; // package는 reserved word로 사용할 수 없어서 packaze를 사용합니다.

import com.fastcampus.pass.repository.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "package")
public class PackageEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 DB에 위임합니다. (AUTO_INCREMENT)
    private Integer packageSeq;

    private String packageName;

    // gym 이용 기간
    private Integer periodGym;

    // PT
    private Integer countPt;

    public PackageEntity() {

    }

    private PackageEntity(String packageName, Integer periodGym, Integer countPt) {
        this.packageName = packageName;
        this.periodGym = periodGym;
        this.countPt = countPt;
    }

    public static PackageEntity of(String packageName, Integer periodGym, Integer countPt) {
        return new PackageEntity(packageName, periodGym, countPt);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PackageEntity that)) return false;
        return Objects.equals(packageSeq, that.packageSeq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageSeq);
    }
}
