package jpabook.jpashop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
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
    }
}