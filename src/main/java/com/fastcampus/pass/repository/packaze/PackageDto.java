package com.fastcampus.pass.repository.packaze;

import java.time.LocalDateTime;

public record PackageDto(
        Integer packageSeq,
        String packageName,
        Integer gymPeriod,
        Integer count,
        Integer period,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy) {


    public static PackageDto of(Integer packageSeq, String packageName, Integer gymPeriod, Integer count, Integer period) {
        return PackageDto.of(packageSeq, packageName, gymPeriod, count, period, null, null, null, null);
    }

    public static PackageDto of(Integer packageSeq, String packageName, Integer gymPeriod, Integer count, Integer period, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new PackageDto(packageSeq, packageName, gymPeriod, count, period, createdAt, createdBy, modifiedAt, modifiedBy);
    }


    public static PackageDto from(PackageEntity entity) {
        return new PackageDto(
                entity.getPackageSeq(),
                entity.getPackageName(),
                entity.getPeriodGym(),
                entity.getCount(),
                entity.getPeriod(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
}
