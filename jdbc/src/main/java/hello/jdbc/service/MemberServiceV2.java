package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 트랜잭션 - 파라미터 연동, 풀을 고려한 종료
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {

    private final DataSource dataSource;
    private final MemberRepositoryV2 memberRepository;

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        Connection con = dataSource.getConnection();
        try {
            //트랜잭션 시작
            con.setAutoCommit(false);
            bizLogic(con, fromId, money, toId);

            //성공 시 커밋
            con.commit();
        } catch (Exception e) {
            //실패 시 롤백
            con.rollback();
            throw new IllegalStateException(e);
        } finally {
            release(con);
        }
    }

    private void bizLogic(Connection con, String fromId, int money, String toId) throws SQLException {
        Member fromMember = memberRepository.findById(con, fromId);
        Member toMember = memberRepository.findById(con, toId);

        memberRepository.update(fromMember.getMoney() - money, fromId);
        validation(toMember);
        memberRepository.update(toMember.getMoney() + money, toId);
    }

    private static void release(Connection con) {
        if (con != null) {
            try {
                //false로 풀로 보내면, 해당 세션은 false로 유지되어 있다.
                //풀이 아닌 경우는 커밋모드 변경 불필요
                con.setAutoCommit(true);
                con.close();
            } catch (Exception e) {
                log.info("error", e);
            }
        }
    }

    private static void validation(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("이체중 예외 발생");
        }
    }
}
