package store.zabbix.auth;

import cn.hutool.core.codec.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToolsAuthServiceApplicationTests {

    @Test
    public void contextLoads() {
        String a = Base64.encode("admin");
        System.out.println(a);
    }

}
