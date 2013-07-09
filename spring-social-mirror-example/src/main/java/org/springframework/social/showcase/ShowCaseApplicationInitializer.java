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

		// register anything else. Thus far, we've
		// registered the Spring Dispatcher Servlet and
		// the H2 Database Administration Console

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
