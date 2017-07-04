package org.zp.notes.spring.common.cache.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author victor zhang
 */
public class EhcacheDemo {
	Logger log = LoggerFactory.getLogger(EhcacheDemo.class);

	public static void main(String[] args) throws Exception {
		// 创建一个缓存管理器 CacheManager
		CacheManager cacheManager = new CacheManager();

		// 创建名为“helloworld”的缓存
		Cache cache = cacheManager.getCache("helloworld");
		
		// 创建用于映射数据的密钥
		String key = "keyString";

		// 创建一个数据元素
		Element putKey = new Element(key, "Hello,World");

		// 将元素放入缓存中
		cache.put(putKey);

		// 检索数据元素
		Element element = cache.get(key);

		// 输出这个元素
		System.out.println(element.getObjectValue());
	}
}
