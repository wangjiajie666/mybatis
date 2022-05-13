package cn.itcast.mp.pojo;

import cn.itcast.mp.enums.SexEnum;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@TableName("tb_user")
public class User extends Model<User> {
    //@TableId(type = IdType.AUTO)
    private Long id;
    private String userName;
    @TableField(select = false,fill = FieldFill.INSERT)
    private String password;
    private String name;
    private Integer age;
    private String email;

    @Version    //乐观锁的版本字段
    private Integer version;

    @TableLogic //逻辑删除字段  ，1-删除， 0-未删除
    private Integer deleted;

    private SexEnum sex;
}