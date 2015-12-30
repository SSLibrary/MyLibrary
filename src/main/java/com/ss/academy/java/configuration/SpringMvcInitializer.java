package com.ss.academy.java.configuration;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { HibernateConfiguration.class, SecurityConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebAppConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	protected Filter[] getServletFilters() {
			Filter[] filters;
		    CharacterEncodingFilter encFilter;
		    HiddenHttpMethodFilter httpMethodFilter = new HiddenHttpMethodFilter();
		    encFilter = new CharacterEncodingFilter();
		    encFilter.setEncoding("UTF-8");
		    encFilter.setForceEncoding(true);

		    filters = new Filter[] {httpMethodFilter, encFilter};
		    return filters;
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.setInitParameter("defaultHtmlEscape", "true");
		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter", new CharacterEncodingFilter());
	      encodingFilter.setInitParameter("encoding", "UTF-8");
	      encodingFilter.setInitParameter("forceEncoding", "true");
	      encodingFilter.addMappingForUrlPatterns(null, true, "/*");
	}

}