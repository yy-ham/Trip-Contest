package com.example.demo.test;

import java.util.Optional;

import org.assertj.core.api.Assertions;

import com.example.demo.entity.member.*;
import com.example.demo.service.member.MemberService;

public class Test {

	@org.junit.jupiter.api.Test
	public void test() {
		MemberService service = new MemberService();
//		Optional<Member> member=service.findByNameAndPhone("a", "3");
//		Assertions.assertThat(member).isPresent();
	}
}
