package com.herren.project.exception;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    /* 404 NOT_FOUND : 데이터를 찾을 수 없음 */
    EMPLOYEE_NOT_FOUND(NOT_FOUND, "존재하지 않는 직원입니다."),
    SHOP_NOT_FOUND(NOT_FOUND, "존재하지 않는 샵입니다."),


    /* 409 CONFLICT : 중복된 데이터 존재 */
    DUPLICATE_SHOP_INFO(CONFLICT, "직원의 샵 정보가 이미 있습니다."),
    DUPLICATE_BIZ_INFO(CONFLICT, "사업자 번호가 중복됩니다.")
    ;

    private final HttpStatus httpStatus;
    private final String detail;

    ErrorCode(HttpStatus httpStatus, String detail) {
        this.httpStatus = httpStatus;
        this.detail = detail;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getDetail() {
        return detail;
    }
}
