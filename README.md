## 헤렌 자바 개발자 사전 과제 - 샵/직원 관리
## 프로젝트 구성
+ Project: Gradle Project
+ Language: Java11
+ Spring Boot: 2.4.5
+ Packaging: Jar
+ Dependencies : Spring Web, Spring Validation, Spring Data JPA, H2 Database, JUnit5, Rest Assured

## DB table
- 샵 정보
    - [필수, unique]고유 아이디(번호)
    - [필수]상호명
    - [필수, unique]사업자번호
    - [필수]연락처
    - [선택]카카오톡 아이디
- 직원 정보
    - [필수, unique]고유 아이디(번호)
    - [필수]이름
    - [필수, unique]연락처
    - [선택]카카오톡 아이디

## 기능 요구사항
- 샵의 상태를 추가합니다
    - 상태 : 정상, 등록대기, 삭제
    - 상태가 "삭제" 로 바뀌면 해당 샵의 사업자번호, 연락처, 카카오톡 아이디를 삭제합니다
    - 다른 상태값은 변경 시 제약이 없습니다
- 직원의 상태를 추가합니다
    - 상태 : 정상, 삭제
    - 상태가 "삭제" 로 바뀌면 아래와 같이 변경되어야 합니다
        - 해당 직원이 등록된 샵에서 제외됩니다
        - 해당 직원의 이름, 연락처, 카카오톡 아이디를 삭제합니다
- 샵 별로 직원이 등록됩니다
    - 직원이 없는 샵도 가능합니다
    - 한명의 직원은 하나의 샵에만 등록되어야 합니다
- 아래 API 를 작성해주세요
    - 샵 조회
        - 고유 아이디로 하나의 샵 정보만 조회
        - 이때 샵에 등록된 직원 정보는 리스트 형태로 조회됨
    - 샵 삭제
        - 고유 아이디를 전달받아서 샵과 관련된 정보를 삭제
    - 샵 등록
        - 필수, 선택 정보를 받아서 샵을 새로 등록
        - 등록 시 상태값을 등록대기로 등록. 다만 어드민에서 승인 후 상태값이 정상으로 바뀌는 부분은 구현하지 않아도 됨
        - 사업자번호가 중복되면 409 에러 발생
    - 직원 등록
        - 필수, 선택 정보를 받아서 직원을 새로 등록
    - 샵에 직원 등록
        - 샵 고유 아이디, 직원 고유아이디를 전달받아 샵에 직원정보를 등록
        - 샵, 직원 정보가 없다면 404 에러 발생
        - 직원이 특정 샵에 이미 등록되어 있다면 409 에러 발생. 즉, 직원은 하나의 샵에만 등록될 수 있음
