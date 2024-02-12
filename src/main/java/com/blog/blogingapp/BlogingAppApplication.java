package com.blog.blogingapp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.blog.blogingapp.Constants.AppConstant;
import com.blog.blogingapp.Entities.Roles;
import com.blog.blogingapp.Repositories.RoleRepo;

@SpringBootApplication
public class BlogingAppApplication implements CommandLineRunner {
	

	@Autowired
	private RoleRepo roleRepo;
	public static void main(String[] args) {
		SpringApplication.run(BlogingAppApplication.class, args);
		System.out.println("-----********hello********-----------=");
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	@Override
	public void run(String... args) throws Exception {
		// System.out.println("------------password--------" + this.passwordEncoder.encode("1234"));

		Roles role1 = new Roles();
		role1.setId(AppConstant.Admin);
		role1.setName("ROLE_ADMIN");
		Roles role2 = new Roles();
		role2.setId(AppConstant.User);
		role2.setName("ROLE_USER");

		roleRepo.save(role1);
		roleRepo.save(role2);


	}

}
