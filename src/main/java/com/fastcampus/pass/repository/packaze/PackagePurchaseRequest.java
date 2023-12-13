package com.fastcampus.pass.repository.packaze;


public record PackagePurchaseRequest(
        Integer gymPeriod,
        Integer countPt
) {
    public static PackagePurchaseRequest of(Integer gymPeriod, Integer countPt) {
        return new PackagePurchaseRequest(gymPeriod, countPt);
    }

}
