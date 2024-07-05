package com.hello.hellospring.common.bean;

import java.util.List;

import lombok.Data;

@Data
public class MemberTestBean {
	
	private String name;
	private int age;
	private boolean isStudent;
	// 객체
	private AddressBean address;
	private List<String> languages;
}
