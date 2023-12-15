package com.fastcampus.pass.dto.request;


public record PackagePurchaseRequest(
        Integer gymPeriod,
        Integer countPt
) {
    public static PackagePurchaseRequest of(Integer gymPeriod, Integer countPt) {
        return new PackagePurchaseRequest(gymPeriod, countPt);
    }

}
