package com.gupaoedu;

import com.gupaoedu.service.IUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class NormalTest {

    @Test
    public void normalTest(){
        Assertions.assertEquals(2,1);
    }

}
