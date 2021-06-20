package com.javatest.study;

import com.javatest.domain.Member;
import com.javatest.domain.Study;
import com.javatest.member.MemberService;
import com.javatest.study.StudyRepository;
import com.javatest.study.StudyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {



    // StudyService가  memberService와 StudyRepository를 의존 받는데 둘 다 구현체가 없는
    // 인터페이스라서 Mocking이 필요하다.
    @Test
    void createNewStudy(@Mock MemberService memberService,
                        @Mock StudyRepository studyRepository){

        StudyService studyService = new StudyService(memberService,studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("hyun@naver.com");

//        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(memberService.findById(any())).thenReturn(Optional.of(member)); //any() 를 사용할 경우 아무 파라미터

        Study study = new Study(10,"java");
//        Optional<Member> findById = memberService.findById(1L);

        assertEquals("hyun@naver.com",memberService.findById(1L).get().getEmail());
        assertEquals("hyun@naver.com",memberService.findById(2L).get().getEmail());
        assertEquals("hyun@naver.com",memberService.findById(3L).get().getEmail());

//        studyService.createNewStudy(1L,study);



    }
}