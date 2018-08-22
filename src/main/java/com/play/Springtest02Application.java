package com.play;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.MultipartConfigElement;

@ServletComponentScan
@SpringBootApplication
@EnableScheduling
public class Springtest02Application {
	public static void main(String[] args) {
		//args 可以换成一些启动项来配合CommadLineRunner(config文件夹下)
		SpringApplication.run(Springtest02Application.class, args);
		//SpringApplication.run(Springtest02Application.class, new String[]{">>>>>>>>>>>>>>>欢迎来到springboot学习工程<<<<<<<<<<<<<<<"});
	}
	//propertis文件中设置文件上传大小，当上传过大的文件后，页面报错ERR_CONNECTION_RESET，此异常GlobalException捕获不到
	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatEmbedded() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
		tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
			if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
				//-1 means unlimited
				((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
			}
		});
		return tomcat;
	}
	@Bean
	public MultipartConfigElement multipartConfigElement(){
		MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
		multipartConfigFactory.setMaxFileSize("10MB");//KB,MB
		multipartConfigFactory.setMaxRequestSize("15MB");
		//设置存储路径
//		multipartConfigFactory.setLocation("地址");
		return multipartConfigFactory.createMultipartConfig();
	}

}
