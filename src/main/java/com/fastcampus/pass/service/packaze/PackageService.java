package com.fastcampus.pass.service.packaze;

import com.fastcampus.pass.dto.PackageDto;
import com.fastcampus.pass.repository.packaze.PackageEntity;
import com.fastcampus.pass.repository.packaze.PackageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackageService {
    private final PackageRepository packageRepository;

    public PackageService(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    public List<PackageDto> getAllPackages() {
        List<PackageEntity> packageEntities = packageRepository.findAllByOrderByPackageName();

        return packageEntities.stream()
                .map(PackageDto::from)
                .collect(Collectors.toList());

    }
}
