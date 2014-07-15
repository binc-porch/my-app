package com.mycompany.app;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.mycompany.app.resources.HelloWorldResource1;
import com.mycompany.app.health.TemplateHealthCheck;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
	public static void main(String[] args) throws Exception {
		new HelloWorldApplication().run(args);
	}

	@Override
	public String getName() {
		return "hello-world";
	}

	@Override
	public void initialize(Bootstrap<HelloWorldConfiguration> bootdtrap) {

	}

	@Override
	public void run(HelloWorldConfiguration configuration, Environment environment) {
	   final HelloWorldResource1 resource = new HelloWorldResource1(
			configuration.getTemplate(),
			configuration.getDefaultName()
	   );
       final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());

       environment.healthChecks().register("template", healthCheck);       
       environment.jersey().register(resource);
	}
}
