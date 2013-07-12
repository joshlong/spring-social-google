package org.springframework.social.showcase;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;

/**
 * Sets up the show-case application to use Servlet 3 Java-centric initialization as a replacement for
 * <CODE>old-web_xml</CODE> files.
 *
 * @author Josh Long
 */
public class ShowCaseApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected void registerContextLoaderListener(ServletContext servletContext) {
		super.registerContextLoaderListener(servletContext);

		// register the H2 database
		ServletRegistration.Dynamic dynamic = servletContext.addServlet("h2Console", org.h2.server.web.WebServlet.class);
		dynamic.setLoadOnStartup(2);
		dynamic.addMapping("/admin/h2/*");
		dynamic.setInitParameter("-webAllowOthers", "true");


//		// register the remember-me functionality
//		DelegatingFilterProxy filter = new DelegatingFilterProxy(SecurityConfig.REMEMBER_ME_AUTHENTICATION_FILTER_NAME);
//		String filterName = SecurityConfig.REMEMBER_ME_AUTHENTICATION_FILTER_NAME;
//		FilterRegistration.Dynamic registration = servletContext.addFilter(filterName, filter);
//		registration.setAsyncSupported(isAsyncSupported());
//		registration.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ASYNC), false, getServletName());
//		registration.setAsyncSupported( true );
//

	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{ServiceConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{WebMvcConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

}
