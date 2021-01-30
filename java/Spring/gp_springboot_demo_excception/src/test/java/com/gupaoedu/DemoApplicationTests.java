package com.gupaoedu;

import com.gupaoedu.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private IUserService userService;

	@Test
	void contextLoads() {
		System.out.println(userService.query());
	}

}
