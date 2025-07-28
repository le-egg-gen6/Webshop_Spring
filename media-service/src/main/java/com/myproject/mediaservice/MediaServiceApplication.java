package com.myproject.mediaservice;

import com.myproject.commonlibs.config.CorsConfig;
import com.myproject.commonlibs.config.MessageSourceConfig;
import com.myproject.commonlibs.config.ServiceUrlConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({CorsConfig.class, ServiceUrlConfig.class, MessageSourceConfig.class})
public class MediaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediaServiceApplication.class, args);
	}

}
