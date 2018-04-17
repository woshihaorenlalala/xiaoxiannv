package com.play.moduls.user.service;

import com.play.moduls.user.bean.User;
import com.play.moduls.user.repository.Impl.UserDao;
import com.play.moduls.user.repository.UserRepository;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by cc on 2018/2/6.
 */
@Service
public class UserService {
    //使用JPA
    @Resource
    private UserRepository userRepository;
    //未使用JPA
    @Resource
    private UserDao userDao;
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    public User getById(Integer id){
        return userDao.getById(id);
    }

   /* public void test(){
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("mykey4","radom1="+String.valueOf(Math.random()));
        System.out.println(valueOperations.get("mykey4"));
    }*/

    @Cacheable(value = "user",key = "#p0")     //p0指第一个属性，一般为id，当参数为对象的时候写成#p0.id
    public User findById(Long id){
        System.err.println("UserService.findById()========从数据库中查取的...id="+id);
        return userRepository.findById(id);
    }

    public User findByUsername(String usernamee){
        System.err.println("UserService.findById()========从数据库中查取的...usernamee="+usernamee);
        return userRepository.findByUsername(usernamee);
    }

    @CacheEvict(value = "user",key = "#p0")
    @Transactional(rollbackFor = Exception.class)
    public void deleteFromCache(Long id){
        System.out.println("UserService.deleteFromCache()：从缓存中删除");
    }

    /**
     * 新增一条信息
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean save(User user){
        if(user.getId() == 0) {
            ByteSource salt = ByteSource.Util.bytes(user.getUsername().toString());
            String pw = user.getPassword();
            String newpw = new SimpleHash("MD5",pw,salt,2).toHex();
            user.setPassword(newpw);
            //设置盐和状态
            user.setSalt(salt.toHex());
            user.setState(Byte.valueOf("1"));
            if(userRepository.findByUsername(user.getUsername()) != null){
                User u = userRepository.save(user);
                if(u.getId() != 0){
                    return true;
                }else{
                    return false;
                }
            }else {
                return false;
            }
        }else{
            //修改只修改昵称和密码
            User u = userRepository.findById(user.getId());
            u.setName(user.getName());
            u.setPassword(user.getPassword());
            u = userRepository.save(user);
            if(u.getId() != 0){
                return true;
            }else{
                return false;
            }
        }
    }
}
