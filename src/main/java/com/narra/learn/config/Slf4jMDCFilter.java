package com.narra.learn.config;

import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class Slf4jMDCFilter extends OncePerRequestFilter {

	private String mdcTokenKey;
	private String requestHeader;

	public Slf4jMDCFilter(String b, String c) {
		mdcTokenKey = b;
		requestHeader = c;
	}

	public Slf4jMDCFilter() {

	}

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain chain) throws java.io.IOException, ServletException {
		try {
			final String token;
			if (!StringUtils.isEmpty(requestHeader) && !StringUtils.isEmpty(request.getHeader(requestHeader))) {
				token = request.getHeader(requestHeader);
			} else {
				token = UUID.randomUUID().toString().toUpperCase().replace("-", "");
			}

			MDC.put(mdcTokenKey, request.getSession().getId());

			Object name = request.getSession().getAttribute("Name");
			if (StringUtils.isEmpty(name)) {
				MDC.put("Name", request.getParameter("name"));
			} else {
				MDC.put("Name", name.toString());
			}

			MDC.put("requestId", token);

			chain.doFilter(request, response);
		} finally {
			MDC.remove(mdcTokenKey);
		}
	}
}
