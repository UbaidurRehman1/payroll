package com.github.rshtishi.department.interceptor;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import com.github.rshtishi.department.UserContext;
import com.github.rshtishi.department.UserContextHolder;

public class UserContextInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		HttpHeaders headers = request.getHeaders();
		request.getHeaders().remove(UserContext.AUTHORIZATION);
		headers.add(UserContext.AUTHORIZATION, UserContextHolder.getContext().getAuthorization());
		headers.add(UserContext.TRACE_ID, UserContextHolder.getContext().getTraceId());
		return execution.execute(request, body);
	}

}
