package com.fastcampus.pass.repository.pass;

import com.fastcampus.pass.repository.packaze.PackageEntity;

import java.time.LocalDateTime;

public record PassDto(
        Integer passSeq,
        Integer packageSeq,
        String userId,
        PassStatus status,
        Integer remainingCount,
        LocalDateTime startedAt,
        LocalDateTime endedAt,
        LocalDateTime expiredAt,
        PackageEntity packageEntity,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static PassDto of(
            Integer passSeq,
            Integer packageSeq,
            String userId,
            PassStatus status,
            Integer remainingCount,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            LocalDateTime expiredAt,
            PackageEntity packageEntity
    ) {
        return PassDto.of(
                passSeq,
                packageSeq,
                userId,
                status,
                remainingCount,
                startedAt,
                endedAt,
                expiredAt,
                packageEntity,
                null,
                null,
                null,
                null
        );
    }

    public static PassDto of(
            Integer passSeq,
            Integer packageSeq,
            String userId,
            PassStatus status,
            Integer remainingCount,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            LocalDateTime expiredAt,
            PackageEntity packageEntity,
            LocalDateTime createdAt,
            String createdBy,
            LocalDateTime modifiedAt,
            String modifiedBy
    ) {
        return new PassDto(
                passSeq,
                packageSeq,
                userId,
                status,
                remainingCount,
                startedAt,
                endedAt,
                expiredAt,
                packageEntity,
                createdAt,
                createdBy,
                modifiedAt,
                modifiedBy
        );
    }

    public static PassDto from(PassEntity entity) {
        return new PassDto(
                entity.getPassSeq(),
                entity.getPackageSeq(),
                entity.getUserId(),
                entity.getStatus(),
                entity.getRemainingCount(),
                entity.getStartedAt(),
                entity.getEndedAt(),
                entity.getExpiredAt(),
                entity.getPackageEntity(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
}
