package com.javatest;

import com.javatest.domain.Study;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

//@ExtendWith(FindSlowTestExtension.class) // 선언적 방법
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @RegisterExtension //프로그래밍적 방법
    static FindSlowTestExtension findSlowTestExtension =
            new FindSlowTestExtension(1000L);

    @Test
    @DisplayName("스터디 만들기")
    void create_new_study() throws InterruptedException {
        Thread.sleep(1005L);

        Study actual = new Study(10);

        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("assumeTrue")
    void assumeTrueTest(){
        //assumeTrue : 뒤에 테스트가 실행되지 않는다.
        String test_env = "LOCAL";
        assumeTrue("LOCAL".equalsIgnoreCase(test_env));

        // assertThat(assertj 사용)
        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("assumeTrue")
    void assumingThatTest(){
        String test_env = "LOCAL";

        //assumingThat : 앞에 조건이 만족하면 실행 로직 사용
        assumingThat("HGS".equalsIgnoreCase(test_env),()->{
            // 실행 로직
        });

        assumingThat("HGS".equalsIgnoreCase(test_env),()->{
            // 실행 로직
        });
    }


//    [START] 실행환경

    @Test
    @DisplayName("WINDOWS")
    @EnabledOnOs({OS.WINDOWS,OS.LINUX}) // 특정 OS
    @EnabledOnJre(JRE.JAVA_8) //특정 자바 환경
    @EnabledIfEnvironmentVariable(named = "TEST_ENV" ,matches = "LOCAL") //실행 환경
    void EnabledOnOsWindow(){
        System.out.println("window start");
        // assertThat(assertj 사용)
        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("mac")
    @DisabledOnOs(OS.MAC) // 맥인 경우에 disable
    void EnabledOnOsMac(){
        System.out.println("mac start");
        // assertThat(assertj 사용)
        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }
//    [END] 실행환경

//    [START] Tag

    @Test
    @DisplayName("스터디 만들기 FAST")
    @Tag("fast")
    void create_new_study_fast(){
        // assertThat(assertj 사용)
        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("스터디 만들기 SLOW")
    @Tag("slow")
    void create_new_study_slow(){
        // assertThat(assertj 사용)
        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

//    [END] Tag





}