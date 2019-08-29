package cn.dodaily.api.mapper.time;

import cn.dodaily.api.bean.time.TaskStep;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskStepMapper {
    @Select("SELECT * FROM TASK_STEP WHERE TASK_ID = #{taskId}")
    @Results(@Result(column = "TASK_ID", property = "taskId"))
    List<TaskStep> selectAllByTaskId(int taskId);

    @Select("SELECT * FROM TASK_STEP WHERE ID = #{id}")
    @Results(@Result(column = "TASK_ID", property = "taskId"))
    TaskStep select(int id);

    @Insert("INSERT INTO TASK_STEP(TASK_ID, CONTENT) VALUES(#{taskId}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(TaskStep task);

    @Update("UPDATE TASK_STEP SET CONTENT=#{content},STATUS=#{status} WHERE ID=#{id}")
    int update(TaskStep task);

    @Delete("DELETE FROM TASK_STEP WHERE ID = #{id}")
    int delete(int id);
}
