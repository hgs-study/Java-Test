package com.javatest.member;

import com.javatest.domain.Member;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId) ;
}
