package cn.dodaily.dailyserver.mapper.time;

import cn.dodaily.dailyserver.bean.time.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskMapper {
    @Select("SELECT * FROM task")
    @Results(@Result(property = "createTime", column = "create_time"))
    List<Task> selectAll();

    @Select("SELECT * FROM task WHERE id = #{id}")
    @Results(@Result(property = "createTime", column = "create_time"))
    Task select(int id);

    @Insert("INSERT INTO task(content,create_time,status) VALUES(#{content}, #{createTime}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Task task);

    @Update("UPDATE task set content=#{content},create_time=#{createTime},status=#{status} WHERE id=#{id}")
    int update(Task task);

    @Delete("DELETE FROM task WHERE id=#{id}")
    int delete(int id);
}
