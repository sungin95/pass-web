package com.fastcampus.pass.service;

import com.fastcampus.pass.repository.pass.PassEntity;
import com.fastcampus.pass.repository.pass.PassRepository;
import com.fastcampus.pass.repository.pass.PassStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Transactional
@Service
public class PassService {
    private final PassRepository passRepository;

    public PassService(PassRepository passRepository) {
        this.passRepository = passRepository;
    }

    public PassEntity getPass(final String userId) {

        final PassEntity passEntitiy = passRepository.findByUserId(userId).get(0);

        if (passEntitiy != null) {
            return passEntitiy;
        } else {
            throw new EntityNotFoundException("패스 엔터티가 null입니다. userId를 다시 확인해 주세요.");
        }
        
    }

    public void createPass(String userId) {
        passRepository.save(PassEntity.of(userId, 0, 0, PassStatus.DEACTIVATION.toString(), userId));
    }

    public void purchasePackaze(String userId, Integer gymPeriod, Integer countPt) {

        PassEntity pass = getPass(userId);

        if (gymPeriod != null) {
            pass.setGymPeriod(pass.getGymPeriod() + gymPeriod);
            pass.setStatus(PassStatus.ACTIVATION.toString());
        }
        if (countPt != null) {
            pass.setCountPt(pass.getCountPt() + countPt);
        }

        passRepository.save(pass);
    }

    public void sendPass(String loginUserId, String userId) {
        PassEntity loginUserPass = getPass(loginUserId);
        PassEntity userPass = getPass(userId);

        userPass.setGymPeriod(userPass.getGymPeriod() + loginUserPass.getGymPeriod());
        userPass.setCountPt(userPass.getCountPt() + loginUserPass.getCountPt());

        loginUserPass.setGymPeriod(0);
        loginUserPass.setCountPt(0);

        userPass.setStatus(PassStatus.ACTIVATION.toString());
        loginUserPass.setStatus(PassStatus.DEACTIVATION.toString());

    }


}
