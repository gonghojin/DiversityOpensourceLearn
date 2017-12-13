package com.websystique.springmvc.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.websystique.springmvc")
public class AppConfig extends WebMvcConfigurerAdapter {

	/*
	 * ContentNegotiationManager 환경설정
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.TEXT_HTML);
	}
	
	/*
	 * ContentNegotiatingViewResolver
	 */
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager){
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);
		
		//모든 가능한 view resolvers를 정의
		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
		resolvers.add(jaxb2MarshallingXmlViewResolver());
        resolvers.add(jsonViewResolver());
        resolvers.add(jspViewResolver());
        resolvers.add(pdfViewResolver());
        resolvers.add(excelViewResolver());
        
        resolver.setViewResolvers(resolvers);
        return resolver;
	}
	
	
	@Bean
	public ViewResolver jaxb2MarshallingXmlViewResolver() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(Pizza.class);
		return new Jaxb2MarshallingXmlViewResolver(marshaller);
	}
	
	 /*
     * Configure View resolver to provide JSON output using JACKSON library to
     * convert object in JSON format.
     */
    @Bean
    public ViewResolver jsonViewResolver() {
        return new JsonViewResolver();
    }
 
    /*
     * Configure View resolver to provide PDF output using lowagie pdf library to
     * generate PDF output for an object content
     */
    @Bean
    public ViewResolver pdfViewResolver() {
        return new PdfViewResolver();
    }
 
    /*
     * Configure View resolver to provide XLS output using Apache POI library to
     * generate XLS output for an object content
     */
    @Bean
    public ViewResolver excelViewResolver() {
        return new ExcelViewResolver();
    }
 
    /*
     * Configure View resolver to provide HTML output This is the default format
     * in absence of any type suffix.
     */
    @Bean
    public ViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

	
}
