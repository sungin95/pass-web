package com.fastcampus.pass.dto;

import com.fastcampus.pass.repository.packaze.PackageEntity;

import java.time.LocalDateTime;

public record PackageDto(
        Integer packageSeq,
        String packageName,
        Integer gymPeriod,
        Integer countPt,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy) {


    public static PackageDto of(Integer packageSeq, String packageName, Integer gymPeriod, Integer countPt) {
        return PackageDto.of(packageSeq, packageName, gymPeriod, countPt, null, null, null, null);
    }

    public static PackageDto of(Integer packageSeq, String packageName, Integer gymPeriod, Integer countPt, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new PackageDto(packageSeq, packageName, gymPeriod, countPt,  createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static PackageDto from(PackageEntity entity) {
        return new PackageDto(
                entity.getPackageSeq(),
                entity.getPackageName(),
                entity.getPeriodGym(),
                entity.getCountPt(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

}
