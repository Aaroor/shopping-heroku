package com.shopping.heroku.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.heroku.model.Order;
import com.shopping.heroku.model.PaymentDetails;

public interface PaymentRepository extends JpaRepository<PaymentDetails, UUID> {

}
