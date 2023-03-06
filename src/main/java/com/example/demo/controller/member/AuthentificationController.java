package com.example.demo.controller.member;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.youiwe.webservice.BitSms;
import lombok.Setter;

@RestController
@Setter
public class AuthentificationController {
	
	@GetMapping("/sendAuthCode")
	@ResponseBody
	public String auth(String phone) {
		String code = "";
		Random r = new Random();
		code += r.nextInt(10);
		code += r.nextInt(10);
		code += r.nextInt(10);
		code += r.nextInt(10);
		code += r.nextInt(10);
		code += r.nextInt(10);
		BitSms sms = new BitSms();
		sms.sendMsg("01025598279", phone, "인증코드는 "+code+" 입니다.");
		
		return code;
	}
}
