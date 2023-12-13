/*
 id varchar(20)
 status, event varchar(10) - enum
 그 외 varchar(50)
 */

CREATE TABLE `package`
(
    `package_seq`  int         NOT NULL AUTO_INCREMENT COMMENT '패키지 순번',
    `package_name` varchar(50) NOT NULL COMMENT '패키지 이름',
    `period_gym`   int                  DEFAULT NULL COMMENT '헬쓰장 기간',
    `count_pt`     int                  DEFAULT NULL COMMENT '이용권 수, NULL인 경우 0',
    `created_at`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    `created_by`   varchar(20)          DEFAULT NULL COMMENT '최초 생성자',
    `modified_at`  timestamp            DEFAULT NULL COMMENT '수정 일시',
    `modified_by`  varchar(20)          DEFAULT NULL COMMENT '최종 수정자',
    PRIMARY KEY (`package_seq`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='패키지';

CREATE TABLE `pass`
(
    `pass_seq`    int         NOT NULL AUTO_INCREMENT COMMENT '이용권 순번',
    `user_id`     varchar(20) NOT NULL COMMENT '사용자 ID',
    `gym_period`  int                  DEFAULT NULL COMMENT '잔여 이용일 수',
    `count_pt`    int                  DEFAULT NULL COMMENT '잔여 PT 이용권 수',
    `status`      varchar(10) NOT NULL COMMENT '회원 상태 표시',
    `created_at`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    `created_by`  varchar(20)          DEFAULT NULL COMMENT '최초 생성자',
    `modified_at` timestamp            DEFAULT NULL COMMENT '수정 일시',
    `modified_by` varchar(20)          DEFAULT NULL COMMENT '최종 수정자',
    PRIMARY KEY (`pass_seq`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='이용권';

CREATE TABLE `bulk_pass`
(
    `bulk_pass_seq` int         NOT NULL AUTO_INCREMENT COMMENT '대량 이용권 순번',
    `package_seq`   int         NOT NULL COMMENT '패키지 순번',
    `user_group_id` varchar(20) NOT NULL COMMENT '사용자 그룹 ID',
    `status`        varchar(10) NOT NULL COMMENT '상태',
    `count`         int                  DEFAULT NULL COMMENT '이용권 수, NULL인 경우 무제한',
    `started_at`    timestamp   NOT NULL COMMENT '시작 일시',
    `ended_at`      timestamp            DEFAULT NULL COMMENT '종료 일시, NULL인 경우 무제한',
    `created_at`    timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    `created_by`    varchar(20)          DEFAULT NULL COMMENT '최초 생성자',
    `modified_at`   timestamp            DEFAULT NULL COMMENT '수정 일시',
    `modified_by`   varchar(20)          DEFAULT NULL COMMENT '최종 수정자',
    PRIMARY KEY (`bulk_pass_seq`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='대량 이용권, 다수의 이용자에게 이용권을 지급하기 위함';



CREATE TABLE `user`
(
    `user_id`       varchar(20)  NOT NULL COMMENT '사용자 ID',
    `user_password` varchar(100) NOT NULL COMMENT '사용자 패스워드',
    `email`         varchar(100) DEFAULT NULL COMMENT '사용자 이메일',
    `nickname`      varchar(50)  NOT NULL COMMENT '사용자 이름',
    `status`        varchar(10)  NOT NULL COMMENT '상태',
    `role_types`    varchar(50)  NOT NULL COMMENT '역할',
    `phone`         varchar(50)  DEFAULT NULL COMMENT '연락처',
    `meta`          TEXT         DEFAULT NULL COMMENT '메타 정보, JSON',
    `created_at`    timestamp    DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    `created_by`    varchar(20)  DEFAULT NULL COMMENT '최초 생성자',
    `modified_at`   timestamp    DEFAULT NULL COMMENT '수정 일시',
    `modified_by`   varchar(20)  DEFAULT NULL COMMENT '최종 수정자',
    PRIMARY KEY (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='사용자';

CREATE TABLE `user_group_mapping`
(
    `user_group_id`   varchar(20) NOT NULL COMMENT '사용자 그룹 ID',
    `user_id`         varchar(20) NOT NULL COMMENT '사용자 ID',
    `user_group_name` varchar(50) NOT NULL COMMENT '사용자 그룹 이름',
    `description`     varchar(50) NOT NULL COMMENT '설명',
    `created_at`      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    `created_by`      varchar(20)          DEFAULT NULL COMMENT '최초 생성자',
    `modified_at`     timestamp            DEFAULT NULL COMMENT '수정 일시',
    `modified_by`     varchar(20)          DEFAULT NULL COMMENT '최종 수정자',
    PRIMARY KEY (`user_group_id`, `user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='사용자 그룹 매핑';



CREATE TABLE `statistics`
(
    `statistics_seq`  int       NOT NULL AUTO_INCREMENT COMMENT '통계 순번',
    `statistics_at`   timestamp NOT NULL COMMENT '통계 일시',
    `all_count`       int       NOT NULL DEFAULT 0 COMMENT '전체 횟수',
    `attended_count`  int       NOT NULL DEFAULT 0 COMMENT '출석 횟수',
    `cancelled_count` int       NOT NULL DEFAULT 0 COMMENT '취소 횟수',
    PRIMARY KEY (`statistics_seq`),
    INDEX idx_statistics_at (`statistics_at`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='통계';
