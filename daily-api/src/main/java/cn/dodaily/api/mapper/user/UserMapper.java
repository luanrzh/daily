package cn.dodaily.api.mapper.user;

import cn.dodaily.api.bean.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT ID,USERNAME FROM USER WHERE USERNAME=#{username} AND PASSWORD=#{password}")
    User selectUser(User user);
}
