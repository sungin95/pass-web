package com.fastcampus.pass.repository.packaze;

import java.time.LocalDateTime;

public record PackageDto(
        String packageName,
        Integer gymPeriod,
        Integer timesPT,
        Integer periodPT,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy) {
    public static PackageDto ofGym(String packageName,Integer gymPeriod) {
        return PackageDto.of(packageName,gymPeriod, null, null);
    }

    public static PackageDto ofPT(String packageName,Integer timesPT, Integer periodPT) {
        return PackageDto.of(packageName,null, timesPT, periodPT);
    }

    public static PackageDto of(String packageName,Integer gymPeriod, Integer timesPT, Integer periodPT) {
        return PackageDto.of(packageName,gymPeriod, timesPT, periodPT, null, null, null, null);
    }

    public static PackageDto of(String packageName,Integer gymPeriod, Integer timesPT, Integer periodPT, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new PackageDto(packageName,gymPeriod, timesPT, periodPT, createdAt, createdBy, modifiedAt, modifiedBy);
    }


    public static PackageDto from(PackageEntity entity) {
        return new PackageDto(
                entity.getPackageName(),
                entity.getPeriodGym(),
                entity.getCountPT(),
                entity.getPeriodPT(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public PackageEntity toEntity(String packageName, Integer periodGym, Integer countPT, Integer periodPT) {
        return PackageEntity.of(packageName, periodGym, countPT, periodPT);
    }
}
