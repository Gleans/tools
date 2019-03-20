package store.zabbix;


import redis.clients.jedis.Jedis;

public class Chapter02 {

    public static void main(String[] args) {
        new Chapter02().run();
    }

    public void run(){
        Jedis conn = new Jedis("localhost");
        conn.select(0);



        System.out.println("run test ...");
    }


    public String checkToken(Jedis conn, String token){
        return conn.hget("login:",token);
    }


    public void updateToken(Jedis conn,String token,String user,String item){
        double times = (double) System.currentTimeMillis();
        conn.hset("login:",user,token);
        conn.zadd("recent:",times,token);
        if(item != null){
            conn.zadd("viewed:"+token,times,item);
            conn.zremrangeByRank("viewed:"+token,0,-26);
            conn.zincrby("viewed:",-1,item);
        }

    }
}
