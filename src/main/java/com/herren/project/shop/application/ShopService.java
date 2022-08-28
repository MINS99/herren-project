package com.herren.project.shop.application;

import com.herren.project.exception.CommonException;
import com.herren.project.exception.ErrorCode;
import com.herren.project.shop.domain.Shop;
import com.herren.project.shop.domain.ShopRepository;
import com.herren.project.shop.dto.ShopCreateRequest;
import com.herren.project.shop.dto.ShopInfoResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShopService {
    private final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Transactional(readOnly = true)
    public ShopInfoResponse findShopInfo(Long shopId) {
        Shop savedShop = shopRepository.findById(shopId).orElseThrow(() -> new CommonException(ErrorCode.SHOP_NOT_FOUND));
        return ShopInfoResponse.toEntity(savedShop);
    }

    @Transactional
    public ShopInfoResponse createShopInfo(ShopCreateRequest shopCreateRequest) {
        validateShopBizNumberCheck(shopCreateRequest.getBizNumber());
        Shop savedShop = shopRepository.save(shopCreateRequest.toEntity());
        return ShopInfoResponse.toEntity(savedShop);
    }

    @Transactional(readOnly = true)
    public void validateShopBizNumberCheck(String bizNumber) {
        boolean isExistBizNumber = shopRepository.existsByBizNumber(bizNumber);
        if (isExistBizNumber) {
            throw new CommonException(ErrorCode.DUPLICATE_BIZ_INFO);
        }
    }
}
