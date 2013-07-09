package org.springframework.social.showcase;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.ServletContext;




public class ShowCaseSecurityApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	/**
	 * Instruct Spring Security to use the {@link org.springframework.web.servlet.DispatcherServlet}'s
	 * {@link org.springframework.web.context.WebApplicationContext} to find the springSecurityFilterChain.
	 */
	@Override
	protected String getDispatcherWebApplicationContextSuffix() {
		return AbstractDispatcherServletInitializer.DEFAULT_SERVLET_NAME;
	}

	/**
	 * Insert the following filters before Spring Security. Be careful when
	 * inserting filters before Spring Security!
	 */
	@Override
	protected void afterSpringSecurityFilterChain(ServletContext servletContext) {
		insertFilters(servletContext, new HiddenHttpMethodFilter(), new MultipartFilter() );
	}

	/**
	 * Register the {@link org.springframework.security.web.session.HttpSessionEventPublisher}
	 */
	@Override
	protected boolean enableHttpSessionEventPublisher() {
		return true;
	}
}
