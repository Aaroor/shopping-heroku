package com.shopping.heroku.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
@Service
public interface UtilService {
	public String findClientIp(HttpServletRequest request);
}
