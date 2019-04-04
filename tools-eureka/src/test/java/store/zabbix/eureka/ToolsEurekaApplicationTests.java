package store.zabbix.eureka;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


//这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文。
@RunWith(SpringJUnit4ClassRunner.class)
//这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下
@SpringBootTest
@WebAppConfiguration
public class ToolsEurekaApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    StringEncryptor encryptor;

    @Test
    public void getPass() {
        String name = encryptor.encrypt("admin");
        String password = encryptor.decrypt("qT21Cy9WI8WgxMT1EdJH25Ny9FpcrpTc");
        System.out.println(name + "----------------");
        System.out.println(password + "----------------");
        Assert.assertTrue(name.length() > 0);
        Assert.assertTrue(password.length() > 0);

    }
}

