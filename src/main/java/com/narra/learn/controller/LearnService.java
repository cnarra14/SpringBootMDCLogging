package com.narra.learn.controller;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LearnService {

	public void log(String str) {
		log.info("LearnService ==>>"+str);
	}
}
