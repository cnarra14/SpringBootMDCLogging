package com.narra.learn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.narra.learn.config.Slf4jMDCFilter;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LeranExampleController {

	@Autowired
	LearnService learnService;

	@Autowired
	Slf4jMDCFilter slf4jMDCFilter;

	@GetMapping("/")
	public String leran(HttpServletRequest request,
			@RequestParam(value = "name", defaultValue = "Chiranjeevi") String name) {

		HttpSession session = request.getSession();
		if (null == session.getAttribute("Name")) {
			session.setAttribute("Name", name);
		}

		log.info("Value :{}", name);

		learnService.log(name);

		return "Thank you " + name + "!!";
	}

	@GetMapping("/invalidate")
	public void inavlidateSession(HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.invalidate();

		log.info("Session Removed");
	}

}
