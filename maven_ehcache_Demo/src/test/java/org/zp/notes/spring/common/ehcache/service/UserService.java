package org.zp.notes.spring.common.ehcache.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.zp.notes.spring.common.ehcache.vo.User;


@Service
public class UserService {
    private Set<User> users;

    public UserService() {
        users = new HashSet<User>();
        User user1 = new User(1, "张三");
        User user2 = new User(2, "赵四");
        User user3 = new User(3, "王五");
        users.add(user1);
        users.add(user2);
        users.add(user3);
    }

    //根据缓存名称在调用方法时，将返回的数据存放在缓存中 当前没有设置key Spring将使用默认策略生成key
    @Cacheable({"users"})
    public User findUser(User user) {
        return findUserInDB(user.getId());
    }
    
    //根据条件将指定的数据存入缓存中, 当前没有设置key Spring将使用默认策略生成key
    @Cacheable(value = "users", condition = "#user.getId() <= 2")
    public User findUserInLimit(User user) {
        return findUserInDB(user.getId());
    }
    
    //将返回数据存放在缓存中，key为自定义的 
    @CachePut(value = "users", key = "#user.getId()")
    public void updateUser(User user) {
        updateUserInDB(user);
    }

    //移除缓存中指定的数据  系统会自己找到对应的key
    @CacheEvict(value = "users")
    public void removeUser(User user) {
        removeUserInDB(user.getId());
    }
    
    //根据自定义的key,移除缓存中对应的数据
    @CacheEvict(value = "users",key = "#user.getId()")
    public void removeUserKey(User user) {
        removeUserInDB(user.getId());
    }
    
    //根据设置的属性，当方法执行时移除指定缓存中全部数据
    @CacheEvict(value = "users", allEntries = true)
    public void clear() {
        removeAllInDB();
    }

    /**
     * 模拟查找数据库
     */
    private User findUserInDB(int id) {
        for (User u : users) {
            if (id == u.getId()) {
                System.out.println("查找数据库 id = " + id + " 成功");
                return u;
            }
        }
        return null;
    }

    /**
     * 模拟更新数据库
     */
    private void updateUserInDB(User user) {
        for (User u : users) {
            if (user.getId() == u.getId()) {
                System.out.println("更新数据库" + u + " -> " + user);
                u.setName(user.getName());
            }
        }
    }

    private void removeUserInDB(int id) {
        for (User u : users) {
            if (id == u.getId()) {
                System.out.println("从数据库移除 id = " + id + " 的数据");
                users.remove(u);
                break;
            }
        }
    }

    private void removeAllInDB() {
        users.clear();
    }
}
