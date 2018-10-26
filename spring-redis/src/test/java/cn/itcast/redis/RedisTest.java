package cn.itcast.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath:spring/applicationConte-Redis.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest {
   /**
    * junit4 测试
    */
	
	@Autowired
	private RedisTemplate redisTemplate ;
	
	
	/**
	 *   Spring 整合Redis 添加和新增
	 *   
	 */
 
	 @Test
	 public void TestRedisOne(){
		 redisTemplate.boundValueOps("name").set("Redis");
	 }
	 
	 /**
	  *  查询
	  *  
	  */
	 @Test
	 public void TestRedisTwo(){
		 
			System.out.println(redisTemplate.boundValueOps("name").get());
	
	 }
	 
	 
	 /**
	  * 储存Hash
	  */
	 @Test
	 public void TestRedisThree(){
		 redisTemplate.boundHashOps("hashMap").put("周维","19");
	 }
	 
	 
	 /**
	  * 取hash储存值
	  */
	 
	 @Test
	 public void TestRedisfour(){
		 
			System.out.println(redisTemplate.boundHashOps("CONTENT").get(1));
	 }
	 

	 /**
	  * 取出所有的 hash 的所有的Key 
	  */
	 
	 @Test
	 public void TestRedisfive(){
	Set key= redisTemplate.boundHashOps("hashMap").keys();
		for (Object object : key) {
			System.out.println(object);
		}
		 
	 }
	 
	 /**
	  * 获得所有值
	  */
	 
	 @Test
	 public void TestRedisSix(){
	List key= redisTemplate.boundHashOps("hashMap").values();
		for (Object object : key) {
			System.out.println(object);
		}
		 
	 }
	 
	 /*
	  *获得所有的map  
	  *
	  */
	 @Test
	 public void TestRedisSeven(){
		 
	   Map  map=  redisTemplate.boundHashOps("hashMap").entries();		 
		   Set<Map.Entry<Object, Object>> entrySet = map.entrySet();
		   for (Entry<Object, Object> entry : entrySet) {
			   System.out.println(entry);
		}
	}
	 
	 /**
	  * 删除key
	  */
	 @Test
	 public void TestRedisEight(){
		 redisTemplate.delete("name");
		 List<String> list= new ArrayList();		
		 list.add("a");
		 list.add("b");
		 list.add("c");
		 redisTemplate.delete(list); 
		 
	 }
	 
	 
	 
	@Test
	public void  Test2JavaSE(){
		 int x;
		 int a=2;
		 int  b=3;
		 int c=4;		 x=++a+b+++c++;
		 System.out.println(x);
				 
	}
	 
	 
}
