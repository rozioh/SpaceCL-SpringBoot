package com.hello.hellospring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hello.hellospring.common.Constants;
import com.hello.hellospring.common.bean.BoardBean;
import com.hello.hellospring.common.bean.MemberBean;
import com.hello.hellospring.service.BoardService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/insertBoard", method = {RequestMethod.POST},
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody // json으로 반환하겠다.
	public Map<String, Object> insertBoard(@RequestBody BoardBean bean, HttpSession session) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String result = Constants.RESULT_VAL_FAIL;
		String resultMsg = "게시글 등록에 실패 하였습니다.";
		
		// 세션값을 들고온다.
		MemberBean memberBean = (MemberBean) session.getAttribute(Constants.KEY_SESSION_MEMBER_BEAN);
		if(memberBean != null) {
			// 토큰값 안의 memberNo 값을 가져와서 insert 정보에 넣어준다.
			bean.setMemberNo( memberBean.getMemberNo() );
		}
		
		try {
			int res = boardService.insertBoard(bean);
			if(res > 0) {
				// 성공
				result = Constants.RESULT_VAL_OK;
				resultMsg = "게시글 등록에 성공하였습니다.";
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMsg = "서버쪽 에러가 발생했습니다. 관리자에게 문의하세요.";
		}
		
		map.put(Constants.RESULT_KEY, result);
		map.put(Constants.RESULT_KEY_MSG, resultMsg);

		return map;
	}

}
