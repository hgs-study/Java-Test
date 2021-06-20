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
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {



    // StudyService가  memberService와 StudyRepository를 의존 받는데 둘 다 구현체가 없는
    // 인터페이스라서 Mocking이 필요하다.
    @Test
    void createStudyService(@Mock MemberService memberService,
                            @Mock StudyRepository studyRepository){

        StudyService studyService = new StudyService(memberService,studyRepository);

        assertNotNull(studyService);
    }
}