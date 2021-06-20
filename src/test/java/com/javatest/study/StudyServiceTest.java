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
import static org.mockito.Mockito.*;

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


        when(memberService.findById(any()))
                .thenReturn(Optional.of(member))   // 첫 번째 호출되면 객체 리턴
                .thenThrow(new RuntimeException()) // 두 번째 호출되면 예외처리
                .thenReturn(Optional.empty());     // 세 번째 호출되면 빈 옵셔널

        // 첫 번째 호출
        Optional<Member> byId = memberService.findById(1L);
        assertEquals("hyun@naver.com",byId.get().getEmail());

        // 두 번째 호출
        assertThrows(RuntimeException.class, ()->{
            memberService.findById(2L);
        });

        // 세 번째 호출
        assertEquals(Optional.empty() , memberService.findById(3L));


    }
}