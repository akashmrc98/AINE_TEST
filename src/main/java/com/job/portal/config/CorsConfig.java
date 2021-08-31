package com.job.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sun.istack.NotNull;

@Configuration
public class CorsConfig
{
	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurer()
		{
			@Override
			public void addCorsMappings(@NotNull CorsRegistry registry)
			{
				try
				{
					registry.addMapping("/**")
					    .allowedOrigins("*")
					    .allowedMethods("*")
					    .allowedHeaders("*");
				}
				catch (NullPointerException npe)
				{
					npe.printStackTrace();
				}
			}
		};
	}
}
