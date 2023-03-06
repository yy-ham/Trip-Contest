package com.example.demo.controller.member;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.example.demo.dao.member.MemberDAO;
import com.example.demo.entity.member.Member;
import com.example.demo.entity.plan.Plan;
import com.example.demo.service.member.MemberService;
import com.example.demo.service.plan.PlanService;
import com.example.demo.vo.member.MemberVO;
import com.example.demo.vo.plan.PlanVO;
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
	private MemberService memberService;
	@Autowired
	private PlanService planService;
		//메인페이지 test
		@GetMapping("/main")
		public String mainForm() {
			return "member/main";
		}
	
		//뷰를 보여주기 위한 mapping
		@GetMapping("/join")
		public String insertForm() {
			return "member/join";
		}
		//관리자 전용 가입
		@GetMapping("/join/madeByTeamFinal")
		public String insertAdminForm() {
			return "member/join2";
		}
		//회원가입 PostMapping
		@PostMapping("/join")
		public ModelAndView insertSubmit(org.springframework.ui.Model model,com.example.demo.entity.member.Member member, MultipartHttpServletRequest mtfRequest,HttpServletRequest request) throws Exception {
			ModelAndView mav = new ModelAndView();
			String id = member.getId();
		    Optional<com.example.demo.entity.member.Member> checkId = memberService.findById(id);

			// 1. 전송받은 파일 및 파일설명 값 가져오기
			List<MultipartFile> fileList = mtfRequest.getFiles("member_img");
//			String path = request.getServletContext().getRealPath("/images");
			String path = "C:/Users/jongchen/git/FinalProject/src/main/resources/static/images";
			
			//System.out.println("path:"+path);
			
	        String fname = "";
	        
	        List<String> fnameList = new ArrayList<>();
	        //System.out.println("fileList:"+fileList);
	        for(MultipartFile uploadFile : fileList) {
	        	fname = uploadFile.getOriginalFilename();
	        	//System.out.println("orginalFname:"+fname);
	        	fnameList.add(fname);
	        	
	        	String safeFile = path + "/" +fname;
	        	//System.out.println("safeFile: "+safeFile);
	        	try {
					uploadFile.transferTo(new File(safeFile));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	        }
	        member.setMemberImg(fnameList.get(0));
	        //System.out.println("첫번째 사진 이름: "+fnameList.get(0));
	        if(fnameList.size()>1) {
	        	for(int i=1; i<fnameList.size(); i++) {
		        	com.example.demo.entity.member.Member m1 = new com.example.demo.entity.member.Member();
		        	m1.setMemberImg(fnameList.get(i));
		        	//System.out.println("fnameList:"+fnameList.get(i));
		        	memberService.insertMember(m1);
		        }
	        }else {
	        	memberService.insertMember(member);
	        }
	        Optional<com.example.demo.entity.member.Member> insertedMember=memberService.findById(id);
	        
		    if (insertedMember.isPresent()) {
		        mav.setViewName("redirect:/join_success");
		    } else {
		        mav.setViewName("redirect:/join_fail");
		    }
			return mav;
		}
		// 아이디 중복 체크 성공 뷰를 보여주는 매핑
	    @GetMapping("/checkId")
	    @ResponseBody
	    public boolean checkId(String id) {
	        Optional<com.example.demo.entity.member.Member> member = memberService.findById(id);
	        return member.isPresent();
	    }
	    // 회원가입 성공 맵핑
	    @GetMapping("/join_success")
	    public String joinSuccess() {
	    	return "member/join_success";
	    }
	    @GetMapping("/join_fail")
	    public String joinFail() {
	    	return "member/join_fail";
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
	    public ModelAndView loginSubmit(Member m, HttpSession session,RedirectAttributes redirectAttributes,HttpServletRequest request) {
	        ModelAndView mav = new ModelAndView();
	        // 아이디와 비밀번호를 확인
	        String id = m.getId();
	        String pwd = m.getPwd();
	        
	        Optional<Member> member = memberService.findById(id);
	        
	        //id와 pwd가 모두 일치한다면
	        if (member.isPresent() && member.get().getPwd().equals(pwd)) {
	            // 로그인 성공 시, 로그인한 사용자 정보를 세션에 저장
	            session.setAttribute("id", member.get().getId());
	            
	            redirectAttributes.addAttribute("id", member.get().getId());
	            redirectAttributes.addAttribute("status", true);
	            mav.setViewName("redirect:/login_success");
	        } else {
//	        	mav.setViewName("redirect:/login_fail");
	        }
	        return mav;
	    }
	 // 로그아웃을 처리하는 매핑
	    @GetMapping("/logout")
	    public ModelAndView logout(HttpSession session) {
	        ModelAndView mav = new ModelAndView();
	        // 로그아웃 시, 세션을 무효화
	        session.invalidate();
	        mav.setViewName("redirect:/login");
	        return mav;
	    }
	    // 로그인 성공 뷰를 보여주는 매핑
	    @GetMapping("/login_success")
	    public String loginSuccess(org.springframework.ui.Model model, HttpSession session) {
	    	String result = "member/id_success";
	    	model.addAttribute("id", (String)session.getAttribute("id"));
	    	return result;
	    }
	    // 로그인 실패 뷰를 보여주는 매핑
	    @GetMapping("/login_fail")
	    public String loginFail() {
	    	return "member/id_fail";
	    }
	 // 마이페이지 경로 매핑
	    @GetMapping("/myPage")
	    public String myPage(HttpSession session, org.springframework.ui.Model model) {
	    	model.addAttribute("id", (String)session.getAttribute("id"));
	    	
	    	String id =(String)session.getAttribute("id");
	    	if(id ==null) {
	    		return "redirect:/login";
	    	}else {
	    		Optional<Member> member = memberService.findById(id);
		    	model.addAttribute("member",member.get());
		    	
		    	return "member/myPage";
	    	}
	    }
	 // 회원 정보 열람하기 위해 패스워드 입력하는 mapping
	    @GetMapping("/beforeEditMyInfo")
	    public String beforeEditMyInfo(HttpSession session, org.springframework.ui.Model model) {
	        model.addAttribute("id", session.getAttribute("id"));
	        String id = (String) session.getAttribute("id");
	        if (id!=null) {
	    		return "member/beforeEditMyInfo";
	    	} else {
	    		// 로그인하지 않은 사용자의 경우 로그인 페이지로 이동
	    		return "redirect:/login";
	    	}
	    }
	    @PostMapping("/beforeEditMyInfo")
	    public String beforeEditMyInfoPost(@RequestParam String pwd, HttpSession session, org.springframework.ui.Model model) {
	        String id = (String) session.getAttribute("id");
	        Optional<Member> member = memberService.findById(id);

	        if (member.isPresent() && member.get().getPwd().equals(pwd)) {
	            return "redirect:/myinfo";
	        } else {
	            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
	            return "member/beforeEditMyInfo";
	        }
	    }

	    // 회원 정보 조회
	    @GetMapping("/myinfo")
	    public String myInfo(HttpSession session, org.springframework.ui.Model model) {
	    	String id =(String)session.getAttribute("id");
	    	if(id ==null) {
	    		return "redirect:/login";
	    	}else {
	    		Optional<Member> member = memberService.findById(id);
		    	model.addAttribute("member",member.get());
		    	
		    	return "member/myinfo";
	    	}
	    }
	    @GetMapping("/pwdSetting")
	    public String pwdSettingForm(HttpSession session, org.springframework.ui.Model model) {
	    	model.addAttribute("id", session.getAttribute("id"));
	        String id = (String) session.getAttribute("id");
	        if (id!=null) {
	    		return "member/pwdSetting";
	    	} else {
	    		// 로그인하지 않은 사용자의 경우 로그인 페이지로 이동
	    		return "redirect:/login";
	    	}
	    }
	    
	    @PostMapping("/pwdSetting")
	    public ModelAndView pwdSettingSubmit( String oldPwd, 
	                                          String newPwd, 
	                                          HttpSession session, RedirectAttributes redirectAttributes, 
	                                          HttpServletRequest request) {
	        ModelAndView mav = new ModelAndView("redirect:/main");
	        String id =(String)session.getAttribute("id");
	        Optional<Member> member = memberService.findById(id);
	        if (member.isPresent()) {
	            if (member.get().getPwd().equals(oldPwd)) {
	                member.get().setPwd(newPwd);
	                memberService.updateMember(member.get());
	                
	                redirectAttributes.addAttribute("successMsg", "비밀번호가 성공적으로 변경되었습니다.");
	                
	            } else {
	                redirectAttributes.addAttribute("errorMsg", "기존 비밀번호가 일치하지 않습니다.");
	                mav.setViewName("redirect:/pwdSetting");
	            }
	        } else {
	            redirectAttributes.addAttribute("errorMsg", "아이디가 존재하지 않습니다.");
	            mav.setViewName("redirect:/pwdSetting");
	        }
	        return mav;
	    }
	    // 정보 수정
	    @GetMapping("/myinfo/edit")
	    public String editMyInfo(HttpSession session, org.springframework.ui.Model model) {
	    // 세션에서 로그인한 사용자 정보를 가져옴
	    	String id =(String)session.getAttribute("id");
	    	Optional<Member> member = memberService.findById(id);
	    	model.addAttribute("member",member.get());
	    	if (id!=null) {
	    		return "member/edit-myinfo";
	    	} else {
	    		// 로그인하지 않은 사용자의 경우 로그인 페이지로 이동
	    		return "redirect:/login";
	    	}
	    }
	    //정보 수정 post
	    @PostMapping("/myinfo/edit")
	    public ModelAndView updateSubmit(org.springframework.ui.Model model,com.example.demo.entity.member.Member editedMember, MultipartHttpServletRequest mtfRequest,HttpServletRequest request,HttpSession session) {
	    	ModelAndView mav = new ModelAndView("redirect:/myinfo");
//	        // trip에 있는 이미지 파일명
	        String oldFname = editedMember.getMemberImg();
	        //System.out.println("oldFname:"+oldFname);
	        if(mtfRequest !=null) {
		        List<MultipartFile> fileList = mtfRequest.getFiles("member_img_edit");
		        String path = "C:/Users/jongchen/git/FinalProject/src/main/resources/static/images";
		        //System.out.println("path:"+path);
		        String fname = "";
		        String id =(String)session.getAttribute("id");
		    	Optional<com.example.demo.entity.member.Member> member = memberService.findById(id);
		    	model.addAttribute("member",member.get());
		        //파일업로드
		        List<String> fnameList = new ArrayList<>();
		        //System.out.println("fileList:"+fileList);
	//	        	// 삭제해야할 원래 있던 이미지들
		            File file = new File(path+"/"+oldFname);
		    		file.delete();
		    		//System.out.println("수정 전 이미지 삭제!");
		    		//System.out.println("수정 전 이미지파일이름");
		        	//System.out.println("fileList:"+fileList.toString());
		            for(MultipartFile uploadFile : fileList) {
		            	fname = uploadFile.getOriginalFilename();
		            	//System.out.println("orginalFname:"+fname);
		            	fnameList.add(fname);
		            	String safeFile = path + "/" +fname;
		            	//System.out.println("safeFile: "+safeFile);
		            	try {
		    				uploadFile.transferTo(new File(safeFile));
		    			} catch (IllegalStateException e) {
		    				// TODO Auto-generated catch block
		    				e.printStackTrace();
		    			} catch (IOException e) {
		    				// TODO Auto-generated catch block
		    				e.printStackTrace();
		    			}
		            }
		            editedMember.setMemberImg(fnameList.get(0));
//		            System.out.println("수정, 첫번째 사진 이름: "+fnameList.get(0));
	    }
	        memberService.updateMember(editedMember);
          return mav;
	    }
//	  나의 여행계획 정보 getmapping
	    @RequestMapping(value = "/findMyPlan", method = RequestMethod.GET)
	    public String findMyPlan(org.springframework.ui.Model model,HttpSession session) {
	    	String id =(String)session.getAttribute("id");
	    	if(id ==null) {
	    		return "redirect:/login";
	    	}else {
	    		planService.findMyPlanByMemberId(id);
		    	List<PlanVO> plan =planService.findMyPlanByMemberId(id);
		    	model.addAttribute("plan",plan);
		    	return "member/edit-myplan";
	    	}
	    }
	    //id찾기
	    @RequestMapping(value = "/findId", method = {RequestMethod.GET})
	    public String findIdForm() {
	        return "member/findIdForm";
	    } 
	    //id찾기 결과값
	    @RequestMapping(value = "/findIdResult", method = RequestMethod.GET)
	    public String findIdResult(org.springframework.ui.Model model,HttpSession session, String name, String phone) {
	    	HashMap<String, Object> map = new HashMap<>();
	    	map.put("name", name);
	    	map.put("phone", phone);
	    	MemberVO member = memberService.findByNameAndPhone(map);
	    	String id = member.getId();
	    	model.addAttribute("id", id);
	        return "member/findIdResult";
	    }
	    //pw찾기 결과값
	    @RequestMapping(value = "/findPwdResult", method = RequestMethod.GET)
	    public String findPwdResult(org.springframework.ui.Model model,HttpSession session, String id, String name, String phone) {
	    	HashMap<String, Object> map = new HashMap<>();
	    	map.put("name", name);
	    	map.put("phone", phone);
	    	map.put("id", id);
	    	MemberVO member = memberService.findByIdAndNameAndPhone(map);
	    	String pwd = member.getPwd();
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
	    public String deleteMyInfo(com.example.demo.entity.member.Member m, HttpSession session) {
	      memberService.deleteMember(m);
	      session.invalidate();
	      return "redirect:/login";
	    }
	    
}
