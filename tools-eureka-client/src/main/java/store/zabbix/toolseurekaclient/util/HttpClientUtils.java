package store.zabbix.toolseurekaclient.util;


import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author 黎杰
 */
public class HttpClientUtils {
    private static final String URL_ERROR = "URL地址出错";

    public static void main(String[] args) throws Exception {
      /*  String url="https://apis.map.qq.com/tools/geolocation?key=3PQBZ-BOBKD-GDQ4U-PLGJH-RFQCS-HABRO&referer=http://map.qq.com/api/js?v=2.exp";
        Map<String,String> map=new HashMap<>();
        map.put("key","3PQBZ-BOBKD-GDQ4U-PLGJH-RFQCS-HABRO");
        map.put("referer","http://map.qq.com/api/js?v=2.exp");*/
//         HttpClientUtils.getRequest(url,map);

        //      testGet();

        //      testPost();
        testPost("https://www.apiopen.top/journalismApi");
    }

    /**
     * httpclient get请求
     *
     * @throws Exception
     */
    public static Object getRequest(String url) {

        //创建一个httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();

        //创建URIBuilder
        URIBuilder uri;
        //创建httpGet对象
        HttpGet hg;
        try {
            uri = new URIBuilder(url);
            hg = new HttpGet(uri.build());
        } catch (URISyntaxException e) {
            return URL_ERROR;
        }


        //设置请求的报文头部的编码
        hg.setHeader(
                new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));

        //设置期望服务端返回的编码
        hg.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
        //写在try()括号里的相当于在finally里写入xx.close()的效果
        try (
                //请求服务
                CloseableHttpResponse response = client.execute(hg)
        ) {
            //获取响应码
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                //获取返回实例entity
                HttpEntity entity = response.getEntity();
                //通过EntityUtils的一个工具方法获取返回内容
                String resStr = EntityUtils.toString(entity, "utf-8");
                return JSON.parse(resStr);
            } else {
                return statusCode;
            }
        } catch (IOException e) {
            return e.getMessage();
        }

    }

    public static void testPost(String url) throws Exception {

        //创建一个httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();

        //创建一个post对象
        HttpPost post = new HttpPost(url);

        //创建一个Entity，模拟表单数据
//        List<NameValuePair> formList = new ArrayList<>();

        //添加表单数据
       /* formList.add(new BasicNameValuePair("username", "黎杰"));
        formList.add(new BasicNameValuePair("password", "10086"));*/

        //包装成一个Entity对象
//        StringEntity entity = new UrlEncodedFormEntity(formList, "utf-8");

        //设置请求的内容
//        post.setEntity(entity);

        //设置请求的报文头部的编码
        post.setHeader(
                new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));

        //设置期望服务端返回的编码
        post.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));

        //执行post请求
        CloseableHttpResponse response = client.execute(post);

        //获取响应码
        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode == 200) {

            //获取数据
            String resStr = EntityUtils.toString(response.getEntity());

            //输出
            System.out.println("请求成功,请求返回内容为: " + resStr);
        } else {

            //输出
            System.out.println("请求失败,错误码为: " + statusCode);
        }

        //关闭response和client
        response.close();
        client.close();

    }
}