package com.r2.common.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.r2.common.filter.MVCUtils;

public class EncryptWrapper extends HttpServletRequestWrapper{
	
	/**
	 * 부모타입에 기본생성자없고, 
	 * 파라미터 생성자 반드시 구현할 것
	 * @param request
	 */
	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}

	/**
	 * password파라미터 요청시,
	 * 암호화된 문자열을 리터하도록
	 * 메소드 오버라이딩
	 * -> 동적로딩에의해 부모타입에서 메소드를 호출해도
	 * 자식객체의 오버라이딩된 메소드가 호출된다.
	 * 
	 */
	@Override
	public String getParameter(String name) {
		String value = "";
		if(name != null && 
		  (name.equals("memberPassword")||name.equals("newPassword"))) {
			// System.out.println("암호화전: "+super.getParameter(name));
			//암호화처리
			value = MVCUtils.getSha512(super.getParameter(name));
			// System.out.println("암호화후: "+value);
		}
		else {
			//password가 아닌 사용자입력값은 그대로 전달
			value = super.getParameter(name);
		}

		return value;
	}
	
	
	
	
	
	
	
	
}
