package cn.dodaily.dailyserver.mapper.time;

import cn.dodaily.dailyserver.bean.time.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskMapper {
    @Select("SELECT * FROM task")
    @Results(@Result(property = "createTime", column = "create_time"))
    List<Task> selectAll();

    @Insert("INSERT INTO task(content,create_time,status) VALUES(#{content}, #{createTime}, #{status})")
    void insert(Task task);
}
