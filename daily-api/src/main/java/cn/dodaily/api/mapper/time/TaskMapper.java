package cn.dodaily.api.mapper.time;

import cn.dodaily.api.bean.time.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskMapper {
    @Select("SELECT * FROM TASK")
    @Results({
            @Result(column = "CREATE_TIME", property = "createTime"),
            @Result(column = "DEADLINE_TIME", property = "deadlineTime"),
            @Result(column = "ID", property = "id"),
            @Result(column = "ID", property = "taskSteps",
                    many = @Many(select = "cn.dodaily.api.mapper.time.TaskStepMapper.selectAllByTaskId"))})
    List<Task> selectAll();

    @Select("SELECT * FROM TASK WHERE TO_DAYS(CREATE_TIME) = TO_DAYS(NOW())")
    @Results({
            @Result(column = "CREATE_TIME", property = "createTime"),
            @Result(column = "DEADLINE_TIME", property = "deadlineTime"),
            @Result(column = "ID", property = "id"),
            @Result(column = "ID", property = "taskSteps",
                    many = @Many(select = "cn.dodaily.api.mapper.time.TaskStepMapper.selectAllByTaskId"))})
    List<Task> selectAllToday();

    @Select("SELECT * FROM TASK WHERE ID = #{id}")
    @Results({
            @Result(column = "CREATE_TIME", property = "createTime"),
            @Result(column = "DEADLINE_TIME", property = "deadlineTime"),
            @Result(column = "ID", property = "id"),
            @Result(column = "ID", property = "taskSteps",
                    many = @Many(select = "cn.dodaily.dailyserver.mapper.time.TaskStepMapper.selectAllByTaskId"))})
    Task select(int id);

    @Insert("INSERT INTO TASK(CONTENT,CREATE_TIME) VALUES(#{content}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Task task);

    @Update("UPDATE TASK SET CONTENT=#{content},STATUS=#{status}, DEADLINE_TIME=#{deadlineTime} WHERE ID=#{id}")
    int update(Task task);

    @Delete("DELETE FROM TASK WHERE ID=#{id}")
    int delete(int id);
}
