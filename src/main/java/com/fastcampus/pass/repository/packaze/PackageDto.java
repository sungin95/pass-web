package com.fastcampus.pass.repository.packaze;

import java.time.LocalDateTime;

public record PackageDto(
        String packageName,
        Integer gymPeriod,
        Integer count,
        Integer period,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy) {
    public static PackageDto ofGym(String packageName,Integer gymPeriod) {
        return PackageDto.of(packageName,gymPeriod, null, null);
    }

    public static PackageDto ofPT(String packageName,Integer count, Integer period) {
        return PackageDto.of(packageName,null, count, period);
    }

    public static PackageDto of(String packageName,Integer gymPeriod, Integer count, Integer period) {
        return PackageDto.of(packageName,gymPeriod, count, period, null, null, null, null);
    }

    public static PackageDto of(String packageName,Integer gymPeriod, Integer count, Integer period, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new PackageDto(packageName,gymPeriod, count, period, createdAt, createdBy, modifiedAt, modifiedBy);
    }


    public static PackageDto from(PackageEntity entity) {
        return new PackageDto(
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

    public PackageEntity toEntity(String packageName, Integer periodGym, Integer count, Integer period) {
        return PackageEntity.of(packageName, periodGym, count, period);
    }
}
