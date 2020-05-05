package com.shopping.heroku.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.heroku.model.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
	Role findByRole(String role);
}
