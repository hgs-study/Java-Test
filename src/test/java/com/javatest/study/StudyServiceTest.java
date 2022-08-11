package com.javatest.study;

import com.javatest.domain.StudyStatus;
import com.javatest.domain.Member;
import com.javatest.domain.Study;
import com.javatest.member.MemberService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Testcontainers
class StudyServiceTest {

    @Mock
    MemberService memberService;

    @Autowired
    StudyRepository studyRepository;

//    mysql
//    @Container
//    static MySQLContainer mySQLContainer = new MySQLContainer().withDatabaseName("test");

    @Container
    static DockerComposeContainer mySQLContainer = new DockerComposeContainer(new File("docker-compose.yml"));

    @BeforeEach
    void beforeEach(){
        studyRepository.deleteAll();
    }

    @Test
    @DisplayName("새로운 공부 저장")
    void createNewStudy_02(){
        StudyService studyService = new StudyService(memberService,studyRepository);
        assertNotNull(studyService);

        Study study = new Study(10,"java");

        Study newStudy = studyService.createNewStudy(1L, study);

        verify(memberService, times(1)).notify(study);
        verify(memberService, never()).validate(any());
        assertEquals(newStudy.getName(), "java");
    }

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


    @Test
    void createNewStudy_with_bdd(){
        //given
        StudyService studyService = new StudyService(memberService,studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("hyun@naver.com");
        Study study = new Study(10,"java");

        //when
        studyService.createNewStudy(1L,study);

        //then
        then(memberService).should(times(1)).notify(study);
    }

    //TODO memberService 객체에 findById 메소드를 1L 값으로 호출하면 member 객체를 리턴하도록 Stubbing
    //TODO studyRepository 객체에 save 메소드를 study 객체로 호출하면 study 객체 그대로 리턴하도록 Stubbing
    @Test
    @DisplayName("Stubbig 연습문제")
    void StubbingPractice(){
        StudyService studyService = new StudyService(memberService,studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("hyun@naver.com");
        Study study = new Study(10,"java");


        studyService.createNewStudy(1L,study);

        verify(memberService, times(1)).notify(study);
    }



    @DisplayName("다른 사용자가 볼 수 있도록 스터디를 공개한다.")
    @Test
    void openStudy(){
        //given
        StudyService studyService = new StudyService(memberService,studyRepository);
        Study study = new Study(10,"java");
        assertNull(study.getOpenDateTime());

        //when
        studyService.open(study);

        //then
        assertEquals(study.getStatus(), StudyStatus.OPENED);
        assertNotNull(study.getOpenDateTime());
    }
}