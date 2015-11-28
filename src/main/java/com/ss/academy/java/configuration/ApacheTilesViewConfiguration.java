package com.ss.academy.java.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
public class ApacheTilesViewConfiguration {
	@Bean
	public TilesConfigurer tilesConfigurer() {
		new TilesConfigurer().setCompleteAutoload(true);
		new TilesConfigurer().setDefinitions("/WEB-INF/tiles.xml");
		return new TilesConfigurer();
	}

	@Bean
	public TilesViewResolver tilesViewResolver() {
		TilesViewResolver tilesViewResolver = new TilesViewResolver();
		tilesViewResolver.setOrder(1);
		return tilesViewResolver;
	}
}
