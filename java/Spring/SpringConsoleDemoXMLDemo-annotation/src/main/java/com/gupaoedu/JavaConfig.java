package com.gupaoedu;

import com.gupaoedu.controller.UserController;
import com.gupaoedu.dao.IUserDao;
import com.gupaoedu.dao.impl.UserDaoImpl;
import com.gupaoedu.service.IUserService;
import com.gupaoedu.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
//@ComponentScan(basePackages={"com.gupaoedu"})
//@ComponentScan(value={"com.gupaoedu.controller"}, useDefaultFilters = false
//        , includeFilters = {@ComponentScan.Filter(Controller.class)})
@ComponentScans({
        @ComponentScan(value={"com.gupaoedu.controller"}, useDefaultFilters = false
        , includeFilters = {@ComponentScan.Filter(Controller.class)})
        ,@ComponentScan(value={"com.gupaoedu.dao","com.gupaoedu.service"}, useDefaultFilters = true
                , excludeFilters = {@ComponentScan.Filter(Controller.class)})
})
/**
 * if not specify scan path(basePackages), default is to scan current and sub directories
 */
public class JavaConfig {

}
