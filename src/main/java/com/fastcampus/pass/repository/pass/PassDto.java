package com.fastcampus.pass.repository.pass;

import java.time.LocalDateTime;

public record PassDto(
        Integer passSeq,
        String userId,

        Integer gymPeriod,
        Integer countPt,

        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static PassDto of(
            Integer passSeq,
            String userId,
            Integer gymPeriod,
            Integer countPt
    ) {
        return PassDto.of(
                passSeq,
                userId,
                gymPeriod,
                countPt,
                null,
                null,
                null,
                null
        );
    }

    public static PassDto of(
            Integer passSeq,
            String userId,
            Integer gymPeriod,
            Integer countPt,
            LocalDateTime createdAt,
            String createdBy,
            LocalDateTime modifiedAt,
            String modifiedBy
    ) {
        return new PassDto(
                passSeq,
                userId,
                gymPeriod,
                countPt,
                createdAt,
                createdBy,
                modifiedAt,
                modifiedBy
        );
    }

    public static PassDto from(PassEntity entity) {
        return new PassDto(
                entity.getPassSeq(),
                entity.getUserId(),
                entity.getGymPeriod(),
                entity.getCountPt(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
}
