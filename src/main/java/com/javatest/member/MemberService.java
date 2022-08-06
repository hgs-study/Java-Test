package com.javatest.member;

import com.javatest.domain.Member;
import com.javatest.domain.Study;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId) ;

    void validate(Long memberId);

    void notify(Study newStudy);

    void notify(Member member);
}
