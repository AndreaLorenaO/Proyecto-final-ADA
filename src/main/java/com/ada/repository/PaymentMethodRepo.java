package com.ada.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ada.model.EPaymentMethods;
import com.ada.model.PaymentMethod;

public interface PaymentMethodRepo extends CrudRepository<PaymentMethod, Integer> {

	Optional<PaymentMethod> findByName(EPaymentMethods payment_method);

}
