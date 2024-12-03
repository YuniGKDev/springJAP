package jpabook.jpashop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
    //@Rollback(false)//테스트 실행 후 Transactional로 데이터가 지워진다. Rollback어너테이션을 통해서 설정을 변경할 수 있다.
    public void testMember() throws Exception {
        //given
        Member member = new Member();
        member.setUsername("yuni");

        //when
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        //then
        /*
        * assertEquals는 간단하고 직관적인 방식으로 두 값을 비교
        * assertThat은 주로 JUnit과 Hamcrest 라이브러리에서 사용하는 검증 메서드로, 테스트의 가독성을 높이고 더 유연한 조건 검증을 가능
        */
        assertThat(findMember.getId(), is(member.getId()));
        assertEquals(findMember.getUsername(), member.getUsername());

        //같은 영속성 안에서 관리하던 내용을 읽는 것(조회하지 않음)으로 동일한 내용이다.
        assertThat(findMember, is(member));
    }
}