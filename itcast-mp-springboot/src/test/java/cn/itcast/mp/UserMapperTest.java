package cn.itcast.mp;
import cn.itcast.mp.mapper.UserMapper;
import cn.itcast.mp.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindById(){
        User byId = userMapper.findById(2L);
        System.out.println(byId);
    }

    @Test
    public void testWrapper(){
        Map<String,Object> map = new HashMap<>();
        map.put("name", "曹操");
        map.put("age", "20");
        map.put("password", null);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //wrapper.allEq(map);
        //SELECT id,user_name,name,age,email FROM tb_user WHERE name = ? AND age = ?
        //wrapper.allEq(map,false);
        //SELECT id,user_name,name,age,email FROM tb_user WHERE name = ?
        wrapper.allEq((k,y)->(k.equals("name")||k.equals("id")),map);

        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testEq() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//SELECT id,user_name,password,name,age,email FROM tb_user WHERE password = ?AND age >= ? AND name IN (?,?,?)
        wrapper.eq("password", "123456")
                .ge("age", 20)
                .in("name", "李四", "王五", "赵六");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testLike(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //SELECT id,user_name,name,age,email FROM tb_user WHERE name LIKE ?
        //参数 %五
        wrapper.likeLeft("name","五");

        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testOrderByAgeDesc(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //按年龄倒序排序
        //SELECT id,user_name,name,age,email FROM tb_user ORDER BY age DESC
        wrapper.orderByDesc("age");

        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testOr(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        //SELECT id,user_name,name,age,email FROM tb_user WHERE name = ? OR age = ?
        wrapper.eq("name","王五").or().eq("age",21);

        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void testSelect2(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("name","王五").or().eq("age",21).select("age","name");

        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void testFindAll(){
        List<User> all = userMapper.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }

}
