package com.example.demo.controller.member;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.MemberDAO;
import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;
import com.ibm.wsdl.util.IOUtils;

import ch.qos.logback.core.model.Model;
import jakarta.persistence.Table;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Setter;


@Controller
@Setter
public class MemberController {
	@Autowired
	private MemberService service;
		//뷰를 보여주기 위한 mapping
		@GetMapping("/join")
		public String insertForm() {
			return "member/join";
		}
		//회원가입 PostMapping
//		@PostMapping("/join")
//		public ModelAndView insertSubmit(Member m) {
//		    ModelAndView mav = new ModelAndView("redirect:/member/login");
//		    Member member = service.insertMember(m);
//		    String id = member.getId();
//		    Optional<Member> checkId = service.findById(id);
//		    if (checkId.isPresent()) {
//		        mav.setViewName("redirect:/join_success");
//		    } else {
//		        mav.setViewName("redirect:/login_fail");
//		    }
//		    return mav;
//		}
		@PostMapping("/join")
		public ModelAndView insertSubmit(Member member, @RequestParam("member_img") MultipartFile file,HttpServletRequest request) throws Exception {
		    // 파일을 저장할 경로를 설정합니다.
		    String path = request.getServletContext().getRealPath("/src/main/resources/static/images");
//			String path = "/Users/2jonghyun/git/FinalProject/src/main/resources/static/images";

		    // 저장할 파일 이름을 설정합니다.
		    String fileName = file.getOriginalFilename();
		    // 저장할 파일 객체를 생성합니다.
		    File uploadFile = new File(path + File.separator + fileName);

		    try (OutputStream os = new FileOutputStream(uploadFile)) {
		        // MultipartFile 객체에서 InputStream을 얻어 파일을 저장합니다.
		        org.apache.commons.io.IOUtils.copy(file.getInputStream(), os);
		    }
		    // 파일 이름을 Member 객체에 저장합니다.
		    member.setMemberImg(fileName);

		    // 회원 정보를 저장합니다.
		    Member savedMember = service.insertMember(member);

		    // 모델 객체를 생성합니다.
		    ModelAndView mav = new ModelAndView();
		    // 저장된 회원 정보를 모델에 추가합니다.
		    mav.addObject("member", savedMember);
		    // 회원 가입 완료 페이지로 이동합니다.
		    mav.setViewName("redirect:/join_success");
		    return mav;
		}
		// 아이디 중복 체크 성공 뷰를 보여주는 매핑
	    @GetMapping("/checkId")
	    @ResponseBody
	    public boolean checkId(String id) {
	        Optional<Member> member = service.findById(id);
	        return member.isPresent();
	    }
	    // 회원가입 성공 맵핑
	    @GetMapping("/join_success")
	    public String joinSuccess() {
	    	return "member/join_success";
	    }
		// 아이디 중복 체크 실패 뷰를 보여주는 매핑
	    @GetMapping("/id_fail")
	    public void idFail() {
	    	
	    }
		 // 로그인 뷰를 보여주는 매핑
	    @GetMapping("/login")
	    public String loginForm() {
	    	return "member/login";
	    }

	    // 로그인을 처리하는 매핑
	    @PostMapping("/login")
	    public ModelAndView loginSubmit(Member m, HttpSession session) {
	        ModelAndView mav = new ModelAndView();
	        // 아이디와 비밀번호를 확인
	        String id = m.getId();
	        String pwd = m.getPwd();

	        Optional<Member> member = service.findById(id);
	        //id와 pwd가 모두 일치한다면
	        if (member.isPresent() && member.get().getPwd().equals(pwd)) {
	            // 로그인 성공 시, 로그인한 사용자 정보를 세션에 저장
	            session.setAttribute("loggedInUser", member.get());
	            mav.setViewName("redirect:/login_success");
	        } else {
	            mav.setViewName("redirect:/login_fail");
	        }
	        return mav;
	    }


	    // 로그인 성공 뷰를 보여주는 매핑
	    @GetMapping("/login_success")
	    public String loginSuccess() {
	    	return "member/id_success";
	    }

	    // 로그인 실패 뷰를 보여주는 매핑
	    @GetMapping("/login_fail")
	    public String loginFail() {
	    	return "member/id_fail";
	    }
	    // 회원 정보 조회
	    @GetMapping("/myinfo")
	    public String myInfo(HttpSession session, org.springframework.ui.Model model) {
	        // 세션에서 로그인한 사용자 정보를 가져옴
	        Member loggedInUser = (Member) session.getAttribute("loggedInUser");
	        if (loggedInUser != null) {
	            model.addAttribute("member", loggedInUser);
	            
	            return "member/myinfo";
	        } else {
	            // 로그인하지 않은 사용자의 경우 로그인 페이지로 이동
	            return "redirect:/login";
	        }
	    }
	    
	    // 정보 수정
	    @GetMapping("/myinfo/edit")
	    public String editMyInfo(HttpSession session, org.springframework.ui.Model model) {
	    // 세션에서 로그인한 사용자 정보를 가져옴
	    	Member loggedInUser = (Member) session.getAttribute("loggedInUser");
	    	if (loggedInUser != null) {
	    		model.addAttribute("member", loggedInUser);
	    		return "member/edit-myinfo";
	    	} else {
	    		// 로그인하지 않은 사용자의 경우 로그인 페이지로 이동
	    		return "redirect:/login";
	    	}
	    }
	 // 정보 수정
	    @PostMapping("/myinfo/edit")
	    public String editMyInfoSubmit(Member editedMember, HttpSession session) {
	    	// 세션에서 로그인한 사용자 정보를 가져옴
	    	Member loggedInUser = (Member) session.getAttribute("loggedInUser");
	    	if (loggedInUser != null) {
		    	// 로그인한 사용자 정보를 수정함
		    	loggedInUser.setName(editedMember.getName());
	            loggedInUser.setAddr(editedMember.getAddr());
	            loggedInUser.setPhone(editedMember.getPhone());
	            loggedInUser.setMail(editedMember.getMail());
	            loggedInUser.setGender(editedMember.getGender());
	            loggedInUser.setGrade(editedMember.getGrade());
	            loggedInUser.setMemberImg(editedMember.getMemberImg());
	            // 수정된 정보를 데이터베이스에 저장함
	            service.updateMember(loggedInUser);
	            return "redirect:/myinfo";
	    		} 
	    else {
	    // 로그인하지 않은 사용자의 경우 로그인 페이지로 이동
	    	return "redirect:/login";
	    	}
	    }
	    //id찾기
	    @RequestMapping(value = "/findId", method = {RequestMethod.GET})
	    public String findIdForm() {
	        return "member/findIdForm";
	    }
	    
	    //id찾기 결과값
	    @RequestMapping(value = "/findIdResult", method = RequestMethod.GET)
	    public String findIdResult(org.springframework.ui.Model model,HttpSession session) {
	    	String id = (String)session.getAttribute("id");
	    	model.addAttribute("id", id);
	        return "member/findIdResult";
	    }
	    //pw찾기 결과값
	    @RequestMapping(value = "/findPwdResult", method = RequestMethod.GET)
	    public String findPwdResult(org.springframework.ui.Model model,HttpSession session) {
	    	String pwd = (String)session.getAttribute("pwd");
	    	model.addAttribute("pwd", pwd);
	        return "member/findPwdResult";
	    }
	    //인증 성공을 나타내는 ajax
	    @RequestMapping("/authSuccess")
	    public @ResponseBody String authSuccess(@RequestParam("inputCode") String inputCode, HttpSession session) {
	        String code = (String)session.getAttribute("code");
	        if (inputCode.equals(code)) {
	            session.removeAttribute("code");
	            return "success";
	        } else {
	            return "failure";
	        }
	    }
	    //pw찾기
	    @RequestMapping("/findPwd")
	    public String findPwdForm() {
	        return "member/findPwdForm";
	    }
	    
	    // 회원 탈퇴
	    @PostMapping("/myinfo/delete")
	    public String deleteMyInfo(Member m, HttpSession session) {
	      service.deleteMember(m);
	      session.invalidate();
	      return "redirect:/login";
	    }
	    
}
