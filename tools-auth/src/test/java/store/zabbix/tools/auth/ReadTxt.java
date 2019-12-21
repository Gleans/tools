package store.zabbix.tools.auth;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReadTxt {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("D:\\tools\\MobileGen\\150-Wuhan.txt"), StandardCharsets.UTF_8);
        for (String line : lines) {
            for (int i = 0; i <= 99; i++) {
                String str;
                StringBuffer phone = new StringBuffer(line);
                if (i < 10) {
                    str = "0" + i + "80";
                } else {
                    str = i + "80";
                }
                phone.replace(7, 11, str);
                System.out.println(phone);
            }
        }
    }
}
