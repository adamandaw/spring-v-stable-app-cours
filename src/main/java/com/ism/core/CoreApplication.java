package com.ism.core;

import com.ism.core.services.SessionCoursService;
import jdk.dynalink.linker.support.Guards;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplication.Running;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import static org.junit.Assert.assertThat;

@SpringBootApplication
@EntityScan(basePackages = "package com.ism.core")
public class CoreApplication{


	public static void main(String[] args) {
		System.out.println("Test");
		SpringApplication.run(CoreApplication.class, args);
	}




}

