package com.shopping.heroku.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.heroku.model.BillingDetails;



@Repository
public interface BillingRepository extends JpaRepository<BillingDetails, UUID> {

}
