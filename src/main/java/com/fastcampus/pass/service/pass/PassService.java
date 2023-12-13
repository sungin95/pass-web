package com.fastcampus.pass.service.pass;

import com.fastcampus.pass.repository.pass.PassDto;
import com.fastcampus.pass.repository.pass.PassEntity;
import com.fastcampus.pass.repository.pass.PassRepository;
import com.fastcampus.pass.repository.pass.PassStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassService {
    private final PassRepository passRepository;

    public PassService(PassRepository passRepository) {
        this.passRepository = passRepository;
    }

    public List<PassDto> getPasses(final String userId) {
        final List<PassEntity> passEntities = passRepository.findByUserId(userId);
        return passEntities.stream()
                .map(PassDto::from)
                .toList();
    }

    public void createPass(String userId) {
        passRepository.save(PassEntity.of(userId, 0, 0, PassStatus.DEACTIVATION.toString(), userId));
    }

    public void purchasePackaze(String userId, Integer gymPeriod, Integer countPt) {

        List<PassEntity> passEntities = passRepository.findByUserId(userId);

        PassEntity pass = passEntities.get(0);

        if (gymPeriod != null) {
            pass.setGymPeriod(pass.getGymPeriod() + gymPeriod);
        }
        if (countPt != null) {
            pass.setCountPt(pass.getCountPt() + countPt);
        }

        passRepository.save(pass);
    }


}
