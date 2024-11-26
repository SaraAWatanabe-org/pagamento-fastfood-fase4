package com.challenge.fastfood.enums;

public enum PaymentStatusEnum {

	PENDING(1),
	APPROVED(2),
	REJECTED(3),
	CANCELLED(4),
	REFUNDED(5),
	;

	private Integer code;

	private PaymentStatusEnum(Integer code) {
		this.code = code;
	}

	public static PaymentStatusEnum toEnum(Integer code) {
		if (code == null) {
			return null;
		}

		for (PaymentStatusEnum x : PaymentStatusEnum.values()) {
			if (x.getCode().equals(code)) {
				return x;
			}
		}

		throw new RuntimeException("Payment Status code invalid. Code: " + code);
	}

	public Integer getCode() {
		return code;
	}

}
