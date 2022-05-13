package cn.itcast.mp;

import cn.itcast.mp.enums.SexEnum;
import cn.itcast.mp.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserMapper2 {
    @Test
    public void testAR(){
        User user = new User();
        user.setId(2L);

        User user1 = user.selectById();
        System.out.println(user1);
    }

    @Test
    public void testAR2(){
        User user = new User();
        user.setName("刘备");
        user.setAge(30);
        user.setPassword("123456");
        user.setUserName("liubei");
        user.setEmail("liubei@itcast.cn");
        boolean insert = user.insert();
        System.out.println(insert);
    }

    @Test
    public void testUpdateById(){
        User user = new User();
        user.setId(9L);
        user.setAge(66);
        boolean update = user.updateById();
        System.out.println(update);
    }

    /**
     * 测试乐观锁
     */
    @Test
    public void testUpdateVersion(){
        User user = new User();
        user.setId(9L);
        user.setAge(66);
        boolean update = user.updateById();
        System.out.println(update);
    }

    @Test
    public void testAR3All(){
        User user = new User();
        user.setId(9L);
        user.setAge(23);
        boolean update = user.update(null);
        System.out.println(update);
    }

    @Test
    public void testAR4(){
        User user = new User();
        user.setId(10L);
        boolean b = user.deleteById();
        System.out.println(b);
    }

    @Test
    public void testARAll(){
        User user = new User();
        user.setId(11L);
        boolean b = user.delete(null);
        System.out.println(b);
    }

    @Test
    public void testAR5(){
        User user = new User();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("age",25);

        List<User> users = user.selectList(wrapper);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }

    @Test
    public void testAR5SelectBySex(){
        User user = new User();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("sex", SexEnum.MAN);

        List<User> users = user.selectList(wrapper);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }

}
