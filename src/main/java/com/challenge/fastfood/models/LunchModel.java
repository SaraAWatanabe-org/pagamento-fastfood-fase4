package com.challenge.fastfood.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.challenge.fastfood.enums.LunchStatusEnum;

import lombok.Data;

@Data
@Document(collection = "lunches")
public class LunchModel {

	@Id
	private Long id;

	private String cpf;

	private String status;
	
	private String clientEmail;
	
	private BigDecimal value;

	@DBRef
	private List<PaymentModel> payments = new ArrayList<PaymentModel>();

	@CreatedDate
	private LocalDateTime creationDate;

	@LastModifiedDate
	private LocalDateTime lastUpdateDate;

	public LunchStatusEnum getStatus() {
		return LunchStatusEnum.toEnum(this.status);
	}

	public void setStatus(LunchStatusEnum status) {
		this.status = status.getCode();
	}

}
