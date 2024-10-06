## 김영한님의 spring DB 1편 강의 정리
<br>
<hr>
<br>

## ✔️ jdbc directory
**학습 범위 6-1-1 - 6-1-9**
- h2 JDBC 실습
  - 등록, 조회, 수정, 삭제
<br>
<br>

**학습 범위 6-2-1 - 6-2-6**
- DataSource : 커넥션을 획득하는 방법을 추상화한 인터페이스

- 커넥션풀 사용
  - HikariDataSoruce
<br>
<br>

**학습 범위 6-3-1 - 6-3-12**
- 트랜잭션이란
  - ACID
  - 자동커밋, 수동커밋
  - lock
  - 조회 락 : `select....for update`
<br>

- 트랜잭션 처리 테스트
<br>
<br>

**학습 범위 6-4-1 - 6-4-11**
- 트랜잭션 추상화
  - PlatformTransactionManager (Service)
  - DataSourceUtils (Repository)
<br>

- 트랜잭션 템플릿 : `txTemplate.executeWithoutResult` 로직을 통해 손쉽게 트랜잭션 처리 가능
  - TransactionTemplate
<br>

- 트랜잭션 AOP 적용
  - `@Transactional` : 선언적 트랜잭션 관리
  - TransactionProxy
<br>

**학습 범위 6-5-1 - 6-5-8**
- 체크 예외, 언체크 예외

- 예외포함 stack trace
  - 예외 전환 시 기존 예외 필수적으로 포함하기 + log로 찍기
<br>

**학습 범위 6-6-1 - **
- 예외 누수 문제 해결
  - 체크 예외 -> 언체크 예외로 변환하여 throw
  - 기존 예외를 언체크 예외와 함께 던짐
  - 순수한 서비스 로직 구성이 가능
<br>


<br>
<hr>
<br>



