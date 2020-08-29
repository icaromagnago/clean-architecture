package com.demo.omni.entrypoint.rest.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.omni.entrypoint.rest.event.CreatedResourceEvent;

@Component
public class CreatedResourceListener implements ApplicationListener<CreatedResourceEvent> {

	@Override
	public void onApplicationEvent(CreatedResourceEvent event) {
		addHeaderLocation(event.getResponse(), event.getId());
	}

	private void addHeaderLocation(HttpServletResponse response, Integer id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(id).toUri();
		
		response.setHeader("Location", uri.toASCIIString());
	}

}
