package com.challenge.fastfood.enums;

import com.challenge.fastfood.exceptions.DataIntegrityException;

public enum LunchStatusEnum {

	RECEIVED("RECEIVED", "Recebido"),
	PAYMENT_APPROVED("PAYMENT_APPROVED", "Pagamento Aprovado"),
	PAYMENT_CREATE_FAILED("PAYMENT_CREATE_FAILED", "Erro Criação Pagamento"),
	PAYMENT_REJECTED("PAYMENT_REJECTED", "Pagamento Rejeitado"),
	IN_PREPARATION("IN_PREPARATION", "Em Preparação"),
	READY("READY", "Pronto"),
	FINISHED("FINISHED", "Finalizado");

	private final String code;
	private final String description;

	private LunchStatusEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static LunchStatusEnum toEnum(String code) {
		for (LunchStatusEnum status : values()) {
			if (status.getCode().equalsIgnoreCase(code)) {
				return status;
			}
		}
		throw new DataIntegrityException("Status não reconhecido: " + code);
	}

}