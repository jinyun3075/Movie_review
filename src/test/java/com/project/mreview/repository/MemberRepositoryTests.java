package com.project.mreview.repository;

import com.project.mreview.entity.member.Member;
import com.project.mreview.entity.member.MemberRepository;
import com.project.mreview.entity.review.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMembers(){
        IntStream.rangeClosed(1,100).forEach(i->{
            Member member=Member.builder()
                    .email("r"+i+"@project.com")
                    .pw("111")
                    .nickname("reviewer"+i)
                    .build();
            memberRepository.save(member);
        });
    }

    @Transactional
    @Commit
    @Test
    public void testDeleteMember(){
        Long mid = 1L;
        Member member = Member.builder().mid(mid).build();
        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(mid);
    }
}
