package com.challenge.fastfood.enums;

import com.challenge.fastfood.exceptions.DataIntegrityException;

public enum PaymentProviderEnum {

	MERCADO_PAGO("MERCADO_PAGO"),
	MOCK_PAYMENT("MOCK_PAYMENT"),
	;

	private String code;

	private PaymentProviderEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static PaymentProviderEnum toEnum(String code) {
		for (PaymentProviderEnum type : values()) {
			if (type.getCode().equalsIgnoreCase(code)) {
				return type;
			}
		}
		throw new DataIntegrityException("Tipo de pagamento não reconhecido: " + code);
	}

}
