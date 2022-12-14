package com.herren.project.shop.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    boolean existsByBizNumber(String bizNumber);
}
