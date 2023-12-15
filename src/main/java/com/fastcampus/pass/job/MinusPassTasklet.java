package com.fastcampus.pass.job;

import com.fastcampus.pass.repository.pass.PassEntity;
import com.fastcampus.pass.repository.pass.PassRepository;
import com.fastcampus.pass.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class MinusPassTasklet implements Tasklet {
    private final PassRepository passRepository;

    public MinusPassTasklet(PassRepository passRepository, UserRepository userRepository) {
        this.passRepository = passRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        final List<PassEntity> passEntityList = passRepository.findByStatus("ACTIVATION");
        List<PassEntity> passEntities = new ArrayList<>();

        for (PassEntity pass : passEntityList ) {
            Integer passPeriod = pass.getGymPeriod();
            if (passPeriod > 1) {
                pass.setGymPeriod(passPeriod -1);
            } else {
                pass.setGymPeriod(0);
                pass.setStatus("DEACTIVATION");
            }
            passEntities.add(pass);
        }
        int count = passEntities.size();
        passRepository.saveAll(passEntities);

        log.info("MinusPassTasklet - execute: 이용권 {}건 이용일 하루 차감 완료, 날짜={}", count, LocalDateTime.now());
        return RepeatStatus.FINISHED;
    }
}
