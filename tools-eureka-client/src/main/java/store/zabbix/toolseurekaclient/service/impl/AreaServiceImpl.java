package store.zabbix.toolseurekaclient.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.zabbix.toolseurekaclient.beans.Common;
import store.zabbix.toolseurekaclient.dao.AreaMapper;
import store.zabbix.toolseurekaclient.model.Area;
import store.zabbix.toolseurekaclient.service.AreaService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AreaServiceImpl implements AreaService {
    private final AreaMapper areaMapper;
    /**
     * 递归便利获取地区的信息
     *
     * @author yuyu
     */
    public String basicUrl = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/";

    public StringBuffer json = null;

    @Autowired
    public AreaServiceImpl(AreaMapper areaMapper) {
        this.areaMapper = areaMapper;
    }

    @Override
    public List<Area> getAreaAll(Map<String,Integer> map) {
        PageHelper.startPage(map.get("pageNo"),map.get("pageSize"));
//        List<Area> list =
        return areaMapper.getAreaAll();
    }



    @Override
    public String getAddress(HttpServletRequest request, HttpServletResponse response, int id) throws Exception {
        try {
            response.setHeader("Content-Type", "application/xml; charset=UTF-8");//编码
            response.setHeader("Access-Control-Allow-Origin", "*");//跨域问题

            String url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/index.html";

            String data = Common.sendGet2(url);

            json =new StringBuffer("");

            Pattern pattern = Pattern.compile("\\d+\\.html'>(\\D+)</a>");
            Matcher matcher = pattern.matcher(data);

            //对应的省市地区的id
//            int Id = Integer.parseInt(request.getParameter("id"));
            if (id > 30 || id < 0) {
                throw new Exception("id错误，应取1-31");
            }
            //便利省市地区
            int i = 0;
            while (matcher.find()) {
                if (i == id) {
                    String info = matcher.group();
//                    String aUrl = basicUrl + info.replaceAll(".>.*", "");
                    String aUrl = basicUrl + info.replaceAll(".>.*", "");
                    String aData = info.replaceAll("\\w|\\.|<|>|/|'", "");
                    System.out.println(aUrl);
                    System.out.println(aData);
                    json.append("{\"").append(aData).append("\":[").append(getInfo(aUrl)).append("]}");
                }
                i++;
            }
//            将跑完的数据保存到txt文件
//            Common.contentToTxt("C:/Users/fuanc/Desktop/" + id + ".txt", json.replaceAll(",]", "]"));
            return json.toString();
           /* response.setContentType("text/html");
            request.setCharacterEncoding("UTF-8");//乱码问题
            PrintWriter out = response.getWriter();
            out.println("已写入C:/Users/fuanc/Desktop/" + id + ".txt");
            out.flush();
            out.close();*/

        } catch (Exception e) {
            return json.append("错误：").append(e.getMessage()).toString();
            //发生错误的时候输出错误
           /* e.printStackTrace();
            response.setContentType("text/html");
            request.setCharacterEncoding("UTF-8");//乱码问题
            PrintWriter out = response.getWriter();
            out.println("错误：" + e.getMessage());
            out.flush();
            out.close();*/
        }
    }


    /**
     * 根据url获取对应的页面信息
     *
     * @param url
     * @return
     * @throws Exception
     */
    public String getInfo(String url) throws Exception {

        StringBuffer json = new StringBuffer("");
        String data = Common.sendGet2(url);

        //请求出错的我时候
        int y = 0;
        while ("".equals(data) || null == data) {

            if (y == 10) {
                break;
            }
            data = Common.sendGet2(url);
            y++;

        }

        if ("".equals(data) || null == data) {

            throw new Exception("未请求到数据");

        }
        //取得对应区域的数据
        Pattern pattern = Pattern.compile("<tr class='[a-z]*'>.+?</tr>");
        Matcher matcher = pattern.matcher(data);

        int x = 0;
        while (matcher.find()) {
            if (x == 0) {
                x++;
                continue;
            }
            String info = matcher.group();
            //获得正确的url
            String status = url.replaceAll("\\d+\\.html", "");
            String shh = getDataByRegex(info, "\\d+/\\d+.html");
            //匹配到url
            String aUrl = status + shh;
            //匹配Id
            String aId = getDataByRegex(info, "\\d{12}");
            //匹配中文
            String aData = getDataByRegex(info, "[\u4e00-\u9fa5]+");
            //打印匹配信息
			System.out.println(aUrl);
			System.out.println(aId);
			System.out.println(aData);
            //添加匹配带的信息
            if ("".equals(shh)) {
                json.append("{\"id\":\"").append(aId).append("\",\"name\":\"").append(aData).append("\"},");
            } else {
                json.append("{\"id\":\"").append(aId).append("\",\"name\":\"").append(aData).append("\",\"children\":[").append(getInfo(aUrl)).append("]},");
            }
        }
        return json.toString();
    }

    /**
     * 执行正则
     *
     * @param data
     * @param regex
     * @return
     */
    public String getDataByRegex(String data, String regex) {
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(data);
            matcher.find();
            return matcher.group();
        } catch (Exception e) {
            return "";
        }
    }
}
