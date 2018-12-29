package com.taobao.order.component;

public interface JedisClient {

	public String set(String key,String value);
	public String get(String key);
	public Long del(String key);
	
	public Long hset(String key,String item,String value);
	public String hget(String key,String item);
	public Long hdel(String key,String item);
	/**
	 * +1
	 * @param key
	 * @return
	 */
	public Long incr(String key);
	/**
	 * -1
	 * @param key
	 * @return
	 */
	public Long decr(String key);
	/**
	 * 设置失效时间
	 * @param key
	 * @param second
	 * @return
	 */
	public Long expire(String key,int second);
	/**
	 * 查询失效剩余时间
	 * @param key
	 * @return
	 */
	public Long ttl(String key);
}
