package com.backOffice_srv;

import com.backOffice_srv.modele.Agent;
import com.backOffice_srv.service.impl.AgentServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BackOfficeApplication {

	public static void main(String[] args) {

		//SpringApplication.run(BackOfficeApplication.class, args);
		//SpringApplication.run(AgentApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(BackOfficeApplication.class, args);

		// Get the AgentService bean from the application context
		AgentServiceImpl agentService = context.getBean(AgentServiceImpl.class);

		 //Create a new Solde object for John Doe
		//Solde soldeJohn = new Solde(null, null, LocalDateTime.now(), 100000.00);

		// Create a new Agent object for John Doe
		Agent newAgent = new Agent(null, null, "Mr", "John", "Doe", "johndoe@example.com", "password123", "1980-01-01", "ID12345678", true, "1234567890", "123 Main St", "Sample City", "12345", "Sample Country");

		// Save the agent
		agentService.createAgent(newAgent);
	}


}
