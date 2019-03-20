package store.zabbix;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ZParams;

import java.util.*;

public class Chapter01 {

    private static final int ON_WEEK_SECONDS = 7 * 86400;

    private static final int UP_SCORE = 432;
    private static final int ARTICLES_PAGES = 25;


    public static void main(String[] args) {
        Jedis conn = new Jedis("localhost");
        conn.auth("pass1234");
//        String[] delkeys = new String[]{"now", "votes"};
        conn.select(0);
//        String articleId = postArticle(conn, "cuifuan", "谷歌", "www.google.com");
//        System.out.println("articled = " + articleId);
        //获取文章
//        List<Map<String, String>> articleList = getArticles(conn, 1);
//        articleList.forEach(System.out::println);
//        articleList.forEach(map -> conn.del(map.get("id")));
        //投票
//        articleVote(conn,"cuifuan3","article:1");
        addOrReMoveGroups(conn,"2",1,new String[]{"java"});

        //增加组
//        addOrReMoveGroups(c);
        //获取每组排序
        List<Map<String, String>> groupArticle=getGroupArticle(conn,"java",1);
        groupArticle.forEach(System.out::println);
    }

    /**
     * 增加投票
     *
     * @param conn
     * @param user
     * @param article
     */
    public static void articleVote(Jedis conn, String user, String article) {
        long cutoff = System.currentTimeMillis() / 1000 - ON_WEEK_SECONDS;
        if (conn.zscore("score:", article) < cutoff) {
            return;
        }
//        String articleId = article.substring(article.indexOf(":")+1);
        String articleId = article.split(":")[1];
        if (conn.sadd("voted:" + articleId, user) == 1) {
            conn.zincrby("score:", UP_SCORE, article);
            conn.hincrBy(article, "votes", 1);
        }

    }

//    /**
//     * 发表文章
//     * @param conn
//     * @param user
//     * @param title
//     * @param link
//     * @return
//     */
//    public static String postArticle(Jedis conn, String user, String title, String link) {
//        String articleId = String.valueOf(conn.incr("article:"));
//        String voted = "voted:" + articleId;
//        conn.sadd(voted, user);
//        //设置过期时间
//        conn.expire(voted, ON_WEEK_SECONDS);
//        long now = System.currentTimeMillis() / 1000;
//        String article = "article:" + articleId;
//        HashMap<String, String> articleData = new HashMap<>();
//        articleData.put("title", title);
//        articleData.put("link", link);
//        articleData.put("user", user);
//        articleData.put("now", String.valueOf(now));
//        articleData.put("votes", "1");
//        conn.hmset(article, articleData);
//        conn.zadd("score:", now + UP_SCORE, article);
//        conn.zadd("time", now, article);
//        return articleId;
//    }

    public static List<Map<String, String>> getArticles(Jedis conn, int page) {
        return getArticles(conn, page, "score:");
    }


    //    获取文章
    public static List<Map<String, String>> getArticles(Jedis conn, int page, String order) {
        int start = (page - 1) * ARTICLES_PAGES;
        int end = start + ARTICLES_PAGES - 1;
        Set<String> ids = conn.zrevrange(order, start, end);
        List<Map<String, String>> articles = new ArrayList<>();
        //根据ID获取文章的详细信息
        ids.forEach(id -> {
            Map<String, String> articleData = conn.hgetAll(id);
            articleData.put("id", id);
            articles.add(articleData);
        });
        return articles;
//        int start = (page - 1) * ARTICLES_PAGES;
//        int end = start + ARTICLES_PAGES - 1;
//
//        Set<String> ids = conn.zrevrange(order, start, end);
//        List<Map<String,String>> articles = new ArrayList<>();
//        for (String id : ids){
//            Map<String,String> articleData = conn.hgetAll(id);
//            articleData.put("id", id);
//            articles.add(articleData);
//        }
//
//        return articles;
    }

    /**
     * 发表文章
     *
     * @param conn
     * @param user
     * @param title
     * @param link
     * @return
     */
    public static String postArticle(Jedis conn, String user, String title, String link) {
        String articleID = String.valueOf(conn.incr("article:"));
        Map<String, String> articleData = new HashMap<>();
        long now = System.currentTimeMillis() / 1000;
        articleData.put("user", user);
        articleData.put("title", title);
        articleData.put("link", link);
        articleData.put("now", String.valueOf(now));
        String article = "article:" + articleID;
        conn.hmset(article, articleData);
        conn.zadd("score:", now + UP_SCORE, article);
        conn.zadd("time:", now, article);
        return articleID;
    }

    /**
     * @param conn
     * @param articleId
     * @param type
     * @param groups
     */
    public static void addOrReMoveGroups(Jedis conn, String articleId, int type, String[] groups) {
        String article = "article:" + articleId;
        for (String group : groups) {
            switch (type) {
                case 1:
                    //添加组
                    conn.sadd("group:" + group, article);
                    break;
                case 2:
                    //删除组
                    conn.srem("group:" + group, article);
                    break;
                default:
                    System.out.println("只有增加或删除...");
                    break;
            }
        }
    }

    public static List<Map<String,String>> getGroupArticle(Jedis conn,String group,int page){
        return getGroupArticle(conn,group,page,"score:");
    }
    public  static List<Map<String,String>> getGroupArticle(Jedis conn,String group,int page,String order) {
        String key = order + group ;
        if(!conn.exists(key)){
            /**
             * WEIGHTS
             * 使用 WEIGHTS 选项，你可以为 每个 给定有序集 分别 指定一个乘法因子(multiplication factor)，每个给定有序集的所有成员的 score 值在传递给聚合函数(aggregation function)之前都要先乘以该有序集的因子。
             *
             * 如果没有指定 WEIGHTS 选项，乘法因子默认设置为 1 。
             *
             * AGGREGATE
             * 使用 AGGREGATE 选项，你可以指定并集的结果集的聚合方式。
             *
             * 默认使用的参数 SUM ，可以将所有集合中某个成员的 score 值之 和 作为结果集中该成员的 score 值；使用参数 MIN ，可以将所有集合中某个成员的 最小 score 值作为结果集中该成员的 score 值；而参数 MAX 则是将所有集合中某个成员的 最大 score 值作为结果集中该成员的 score 值。
             */
            ZParams params = new ZParams().aggregate(ZParams.Aggregate.MAX);
            conn.zinterstore(key,params,"group:"+group,order);
            conn.expire(key,60);
        }
        return getArticles(conn,page,key);
    }


//zincrby
/**
  为有序集 key 的成员 member 的 score 值加上增量 increment 。
  可以通过传递一个负数值 increment ，让 score 减去相应的值，比如 ZINCRBY key -5 member ，就是让 member 的 score 值减去 5 。
  <p>
  当 key 不存在，或 member 不是 key 的成员时， ZINCRBY key increment member 等同于 ZADD key increment member 。
  <p>
  当 key 不是有序集类型时，返回一个错误。
  <p>
  score 值可以是整数值或双精度浮点数。
  <p>
  返回值
  member 成员的新 score 值，以字符串形式表示。
  es:
  redis> ZSCORE salary tom
  "2000"
  <p>
  redis> ZINCRBY salary 2000 tom   # tom 加薪啦！
  "4000"
  <p>
  HINCRBY key field increment
  <p>
  为哈希表 key 中的域 field 的值加上增量 increment 。
  <p>
  增量也可以为负数，相当于对给定域进行减法操作。
  <p>
  如果 key 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。
  <p>
  如果域 field 不存在，那么在执行命令前，域的值被初始化为 0 。
  <p>
  对一个储存字符串值的域 field 执行 HINCRBY 命令将造成一个错误。
  <p>
  本操作的值被限制在 64 位(bit)有符号数字表示之内
  <p>
  ps:
  # increment 为正数
  <p>
  redis> HEXISTS counter page_view    # 对空域进行设置
  (integer) 0
  <p>
  redis> HINCRBY counter page_view 200
  (integer) 200
  <p>
  redis> HGET counter page_view
  "200"
  <p>
  <p>
  # increment 为负数
  <p>
  redis> HGET counter page_view
  "200"
  <p>
  redis> HINCRBY counter page_view -50
  (integer) 150
  <p>
  redis> HGET counter page_view
  "150"
  <p>
  <p>
  # 尝试对字符串值的域执行HINCRBY命令
  <p>
  redis> HSET myhash string hello,world       # 设定一个字符串值
  (integer) 1
  <p>
  redis> HGET myhash string
  "hello,world"
  <p>
  redis> HINCRBY myhash string 1              # 命令执行失败，错误。
  (error) ERR hash value is not an integer
  <p>
  redis> HGET myhash string                   # 原值不变
  "hello,world"
 */

//hincrBy
/**
  HINCRBY key field increment

  为哈希表 key 中的域 field 的值加上增量 increment 。

  增量也可以为负数，相当于对给定域进行减法操作。

  如果 key 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。

  如果域 field 不存在，那么在执行命令前，域的值被初始化为 0 。

  对一个储存字符串值的域 field 执行 HINCRBY 命令将造成一个错误。

  本操作的值被限制在 64 位(bit)有符号数字表示之内

  ps:
  # increment 为正数

  redis> HEXISTS counter page_view    # 对空域进行设置
  (integer) 0

  redis> HINCRBY counter page_view 200
  (integer) 200

  redis> HGET counter page_view
  "200"


  # increment 为负数

  redis> HGET counter page_view
  "200"

  redis> HINCRBY counter page_view -50
  (integer) 150

  redis> HGET counter page_view
  "150"


  # 尝试对字符串值的域执行HINCRBY命令

  redis> HSET myhash string hello,world       # 设定一个字符串值
  (integer) 1

  redis> HGET myhash string
  "hello,world"

  redis> HINCRBY myhash string 1              # 命令执行失败，错误。
  (error) ERR hash value is not an integer

  redis> HGET myhash string                   # 原值不变
  "hello,world"
 */


}