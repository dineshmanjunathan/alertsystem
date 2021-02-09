package com.ss.utils;

public class Constants {
	
	enum PaymentType {
		COD("Cash On Delivery"),
		EPAY("Online Payment"),
		REPURCHASE("Re Purchase");
		
		private final String type;
		
		PaymentType(String type){
			this.type = type;
		}
		
		String getName() {
			return type;
		}
		
	}

}
