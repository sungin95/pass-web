package com.fastcampus.pass.repository.packaze;

import com.fastcampus.pass.repository.user.UserDto;

public record PackagePurchaseRequest(String packageName,
                                     Integer addGymPeriod,
                                     Integer addPTAvailableNumberOfTimes,
                                     Integer addPTPeriod) {

    public static PackagePurchaseRequest of(String packageName, Integer addGymPeriod, Integer PTAvailableNumberOfTimes, Integer addPTPeriod) {
        return new PackagePurchaseRequest(packageName, addGymPeriod, PTAvailableNumberOfTimes, addPTPeriod);
    }

    public PackageDto toDtoGym(UserDto userDto) {
        return PackageDto.ofGym(packageName, addGymPeriod);
    }

    public PackageDto toDtoPT(UserDto userDto) {
        return PackageDto.ofPT(packageName, addPTAvailableNumberOfTimes, addPTPeriod);
    }

    public PackageDto toDtoAll(UserDto userDto) {
        return PackageDto.of(packageName, addGymPeriod, addPTAvailableNumberOfTimes, addPTPeriod);
    }

}
