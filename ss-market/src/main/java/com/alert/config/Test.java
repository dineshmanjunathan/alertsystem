package com.alert.config;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		ArrayList<String> skipList = new ArrayList<>();
		skipList.add("/img");
		skipList.add("/fonts");
		skipList.add("/js");
		skipList.add("/css");
		String uri = "/img/avigo/aa";
		String uriArray[] = uri.split("/");
		System.out.println(uriArray[1]);
		
		if(skipList.contains("/img")) {
			System.out.println("Yes");
		} else {
			System.out.println("NO");
		}

	}

}
