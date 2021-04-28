package com.ada.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Payment_methods")
public class PaymentMethod {

	@Id
	@Column(name = "id_payment_method")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentMethodId;

	@Enumerated(EnumType.STRING)
	@Column(length = 20, name = "payment_method")
	private EPaymentMethods name;

	public PaymentMethod() {
	}

	public int getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(int paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public EPaymentMethods getName() {
		return name;
	}

	public void setName(EPaymentMethods name) {
		this.name = name;
	}

	public String toString() {
		return "id_payment_method" + paymentMethodId + ", payment_method:" + name;

	}

}
