package main.java.core.beans.servlet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan
public class ServletConfig {

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	/*-
		@Autowired
		@Bean
		public ServletContextTemplateResolver dispatcherServlet(DispatcherServlet dispatcherServlet, ServletContext context) {
			ArrayList<HandlerMapping> mappings = (ArrayList<HandlerMapping>) dispatcherServlet.getHandlerMappings();
			for(HandlerMapping mapping : mappings) {
				mapping.getHandler(request)
			}
			ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(context);
	
		}
		*//*-
			//Thymeleaf prefix/suffix must be configured in ServletContextTemplateResolver
			@Bean
			@Autowired
			public ServletContextTemplateResolver servletContextTemplateResolver(ServletContext context) {
			ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(context);
			resolver.setPrefix("/templates/");
			resolver.setSuffix(".html");
			return resolver;
			}
			/*-
			@Bean
			public DefaultTemplateResolverConfiguration defaultTemplateResolverConfiguration(DefaultTemplateResolverConfiguration configuration) {
			
			}
			
			@Bean
			public ThymeleafViewResolver thymeleafViewResolver(ThymeleafViewResolver resolver) {
			resolver.
			}
			*/
}
