package com.hello.hellospring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hello.hellospring.common.Constants;
import com.hello.hellospring.common.bean.MemberBean;
import com.hello.hellospring.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/deleteMemberNew", method = {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> deleteMemberNew(@RequestBody MemberBean bean) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		String result = Constants.RESULT_VAL_FAIL;
		String resultMsg = "실패 하였습니다.";
		
		//TODO
		try {
			int res = memberService.deleteMember(bean);
			if(res > 0) {
				// 성공
				result = Constants.RESULT_VAL_OK;
				resultMsg = "회원정보 삭제에 성공하였습니다.";		
			}
		} catch (Exception e) {
			e.printStackTrace();
//			resultMsg = e.getMessage(); // 정보노출이 된다.
			resultMsg = "서버쪽 에러가 발생했습니다. 관리자에게 문의하세요.";
		}
		
		
		map.put(Constants.RESULT_KEY, result);
		map.put(Constants.RESULT_KEY_MSG, resultMsg);
		return map;
	}
	
	
	
}
