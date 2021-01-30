import com.kelvin.IUserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/consumer.xml");

        IUserService service = (IUserService) context.getBean("userService");
        System.out.println(service.getNameById("abc"));
    }
}
