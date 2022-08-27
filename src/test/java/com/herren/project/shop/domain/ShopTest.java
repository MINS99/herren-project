package com.herren.project.shop.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.herren.project.employees.domain.Employee;
import com.herren.project.employees.domain.EmployeeStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class ShopTest {

    @ParameterizedTest(name = "샵의 상태가 {0}으로 변경된다")
    @EnumSource(value = ShopStatus.class, names = {"NORMAL", "WAITING", "DELETE"})
    void changeShopStatus(ShopStatus expected) {
        Shop shop = new Shop("헤어샵", "12345", "010-1234-1234", "kakaoid", ShopStatus.WAITING);

        shop.changeShopStatus(expected);

        assertThat(shop.getShopStatus()).isEqualTo(expected);
    }

    @Test
    @DisplayName("샵의 상태가 삭제로 변경되면 해당 샵의 사업자번호, 연락처, 카카오톡 아이디를 삭제한다")
    void changeShopStatusThenChangeShopInfo() {
        Shop shop = new Shop("헤어샵", "12345", "010-1234-1234", "kakaoid", ShopStatus.WAITING);

        shop.changeShopStatus(ShopStatus.DELETE);

        assertAll(
                () -> assertThat(shop.getShopStatus()).isEqualTo(ShopStatus.DELETE),
                () -> assertThat(shop.getBizNumber()).isNull(),
                () -> assertThat(shop.getPhoneNumber()).isNull(),
                () -> assertThat(shop.getKakaoId()).isNull()
        );
    }

    @Test
    @DisplayName("직원이 샵 정보를 등록하면 샵에 직원 정보가 등록된다")
    void joinShop() {
        Shop shop = new Shop("헤어샵", "12345", "010-1234-1234", "kakaoid", ShopStatus.WAITING);
        Employee employee = new Employee("직원A", "010-1234-1234", "kakao@kakao.com", EmployeeStatus.NORMAL);

        employee.joinShop(shop);

        assertThat(shop.getStaff()).isNotNull();
    }

    @Test
    @DisplayName("샵에 등록되어 있던 직원이 샵 정보를 삭제하면 샵의 직원 정보가 삭제된다")
    void resignShop() {
        Shop shop = new Shop("헤어샵", "12345", "010-1234-1234", "kakaoid", ShopStatus.WAITING);
        Employee employee = new Employee("직원A", "010-1234-1234", "kakao@kakao.com", EmployeeStatus.NORMAL);
        employee.joinShop(shop);

        employee.resignShop();

        assertThat(shop.getStaff()).isEmpty();
    }
}
