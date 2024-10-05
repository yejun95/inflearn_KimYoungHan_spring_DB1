package hello.jdbc.repository;

import hello.jdbc.domain.Member;

public interface MemberRepository {
    Member save(Member member);

    Member findById(String memberId);

    void update(int money, String memberId);

    void delete(String memberId);
}
