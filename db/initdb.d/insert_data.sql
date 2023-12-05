INSERT INTO package (package_name, count, period, created_at, created_by,
                     modified_by)
VALUES ('Starter PT 10회', 10, 60, '2022-08-01 00:00:00', 'A1000000',
        'A1000000'),
       ('Starter PT 20회', 20, 120, '2022-08-01 00:00:00', 'A1000000',
        'A1000000'),
       ('Starter PT 30회', 30, 180, '2022-08-01 00:00:00', 'A1000000',
        'A1000000'),
       ('무료 이벤트 필라테스 1회', 1, NULL, '2022-08-01 00:00:00', 'A1000000',
        'A1000000'),
       ('바디 챌린지 PT 4주', NULL, 28, '2022-08-01 00:00:00', 'A1000000',
        'A1000000'),
       ('바디 챌린지 PT 8주', NULL, 48, '2022-08-01 00:00:00', 'A1000000',
        'A1000000'),
       ('인바디 상담', NULL, NULL, '2022-08-01 00:00:00', 'A1000000',
        'A1000000');

INSERT INTO `user` (user_id, user_password, user_name, status, role_types, phone, meta, created_at, created_by,
                    modified_by)
VALUES ('A1000000', '1234', '우영우', 'ACTIVE', 'ADMIN', '01011112222', NULL, '2022-08-01 00:00:00', 'A1000000',
        'A1000000'),
       ('A1000001', '1234', '최수연', 'ACTIVE', 'USER', '01033334444', NULL, '2022-08-01 00:00:00', 'A1000000',
        'A1000000'),
       ('A1000002', '1234', '이준호', 'INACTIVE', 'USER', '01055556666', NULL, '2022-08-01 00:00:00', 'A1000000',
        'A1000000'),
       ('B1000010', '1234', '권민우', 'ACTIVE', 'USER', '01077778888', NULL, '2022-08-01 00:00:00', 'A1000000',
        'A1000000'),
       ('B1000011', '1234', '동그라미', 'INACTIVE', 'USER', '01088889999', NULL, '2022-08-01 00:00:00', 'A1000000',
        'A1000000'),
       ('B2000000', '1234', '한선영', 'ACTIVE', 'USER', '01099990000', NULL, '2022-08-01 00:00:00', 'A1000000',
        'A1000000'),
       ('B2000001', '1234', '태수미', 'ACTIVE', 'USER', '01000001111', NULL, '2022-08-01 00:00:00', 'A1000000',
        'A1000000');

INSERT INTO user_group_mapping (user_group_id, user_id, user_group_name, description, created_at, created_by,
                                modified_by)
VALUES ('HANBADA', 'A1000000', '한바다', '한바다 임직원 그룹', '2022-08-01 00:00:00', 'A1000000',
        'A1000000'),
       ('HANBADA', 'A1000001', '한바다', '한바다 임직원 그룹', '2022-08-01 00:00:00', 'A1000000',
        'A1000000'),
       ('HANBADA', 'A1000002', '한바다', '한바다 임직원 그룹', '2022-08-01 00:00:00', 'A1000000',
        'A1000000'),
       ('HANBADA', 'B1000010', '한바다', '한바다 임직원 그룹', '2022-08-01 00:00:00', 'A1000000',
        'A1000000'),
       ('HANBADA', 'B2000000', '한바다', '한바다 임직원 그룹', '2022-08-01 00:00:00', 'A1000000',
        'A1000000'),
       ('TAESAN', 'B2000001', '태산', '태산 임직원 그룹', '2022-08-01 00:00:00', 'A1000000',
        'A1000000');
