package com.herren.project.shop.application;

import com.herren.project.employees.domain.Employee;
import com.herren.project.employees.domain.EmployeeRepository;
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
    private final EmployeeRepository employeeRepository;

    public ShopService(ShopRepository shopRepository, EmployeeRepository employeeRepository) {
        this.shopRepository = shopRepository;
        this.employeeRepository = employeeRepository;
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

    @Transactional
    public void deleteShopInfo(Long shopId) {
        shopRepository.deleteById(shopId);
        employeeRepository.findAllByShopId(shopId).forEach(Employee::resignShop);
    }

    @Transactional(readOnly = true)
    public void validateShopBizNumberCheck(String bizNumber) {
        boolean isExistBizNumber = shopRepository.existsByBizNumber(bizNumber);
        if (isExistBizNumber) {
            throw new CommonException(ErrorCode.DUPLICATE_BIZ_INFO);
        }
    }
}
