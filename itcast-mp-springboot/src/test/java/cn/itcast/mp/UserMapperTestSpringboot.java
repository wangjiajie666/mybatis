package cn.itcast.mp;
import cn.itcast.mp.enums.SexEnum;
import cn.itcast.mp.mapper.UserMapper;
import cn.itcast.mp.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserMapperTestSpringboot {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        user.setAge(21);
        user.setEmail("zgeqing@itcast.cn");
        user.setName("诸葛青");
        user.setUserName("caocao");
        user.setPassword("123456");
        int result = userMapper.insert(user);// result数据库受影响的行数
        System.out.println("result => " + result);

        //获取自增长后的id值，自增长后的id值会回填到user对象中
        System.out.println(" id=> " + user.getId());
    }
    @Test
    public void testSelectById(){
        //根据id查询数据
        User user = userMapper.selectById(13l);
        System.out.println(user);
    }

    @Test
    public void testUpdateById(){
        User user = new User();
        user.setId(2l);
        user.setPassword("777777");

        //根据id更新，更新不为null的字段
        int i = userMapper.updateById(user);
        System.out.println("i => " + i);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        //更新的字段
        user.setPassword("555555");
        user.setAge(22);
        //更新的条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id",6);
        //执行更新操作
        int update = userMapper.update(user, wrapper);
        System.out.println("update => " + update);
    }

    @Test
    public void testUpdate2(){
        //更新的条件以及字段
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("age",24).eq("id",6);
        //执行更新操作
        int update = userMapper.update(null, wrapper);
        System.out.println("update => " +update);
    }

    @Test
    public void testDeleteById(){
        //执行更新操作
        int i = userMapper.deleteById(13L);
        System.out.println("i => " + i);
    }
    @Test
    public void testDeleteByMap(){
        Map<String,Object> map = new HashMap();
        map.put("user_name","zhangsan");
        map.put("password","123456");

        //将columnMap中的元素设置为删除的条件，多个之间为and关系
        int i = userMapper.deleteByMap(map);
    }

    @Test
    public void testDelete(){
        //用法一
        /*QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name","caocao")
                .eq("password","123456");*/

        //用法二
        User user = new User();
        user.setUserName("caocao");
        user.setPassword("123456");
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        //根据包装条件做删除
        userMapper.delete(wrapper);
    }

    @Test
    public void testSelectBatchIds(){
        //根据id集合批量查询
        List<User> users = userMapper.selectBatchIds(Arrays.asList(2L, 3L, 4L, 100L));
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void testSelectOne(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name","lisi");
        //根据条件查询一条数据，如果结果超过一条会报错
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    public void testSelectCount(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age",20); //年龄大于20的数据
        Integer integer = userMapper.selectCount(wrapper);
        System.out.println("count => " + integer);
    }
    @Test
    public void testSelectList(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("age","2");    //模糊查询，包括2的数据
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    //测试分页查询
    @Test
    public void testSelectPage(){
        Page<User> page = new Page<>(3,2);  //查询第一页， 查询1条数据
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //设置查询条件
        wrapper.like("email","itcast");
        IPage<User> userIPage = userMapper.selectPage(page, wrapper);
        System.out.println("当前总条数：" + userIPage.getTotal());
        System.out.println("当前总页数：" + userIPage.getPages());
        System.out.println("当前页数：" + userIPage.getCurrent());

        List<User> records = userIPage.getRecords();
        for (User record : records) {
            System.out.println(record);
        }
    }

    @Test
    public void testInsert2(){
        User user = new User();
        user.setName("貂蝉");
        user.setUserName("diaochan");
        user.setAge(20);
        user.setEmail("diaochan@itast.cn");
        user.setVersion(1);
        user.setSex(SexEnum.WOMAN);
        int result = this.userMapper.insert(user);
        System.out.println("result = " + result);
    }


}
