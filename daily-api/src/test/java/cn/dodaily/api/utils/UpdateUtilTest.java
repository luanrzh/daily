package cn.dodaily.api.utils;

import cn.dodaily.api.bean.time.Task;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static cn.dodaily.api.utils.UpdateUtil.fillNotNullToOld;
import static cn.dodaily.api.utils.UpdateUtil.fillNullToNew;

public class UpdateUtilTest {

    @Test
    public void testFillNullToNew() {
        Task oldTask = new Task(1, 1, 0, "content0", new Date(), null, null);
        Task newTask = new Task(1, 1, 1, null, null, null, null);
        Task task = (Task) fillNullToNew(newTask, oldTask);
        Assert.assertEquals(task.getId(), newTask.getId());
        Assert.assertEquals(task.getContent(), oldTask.getContent());
        Assert.assertEquals(task.getCreateTime(), oldTask.getCreateTime());
        Assert.assertEquals(task.getStatus(), newTask.getStatus());
    }

    @Test
    public void testFillNotNullToOld() {
        Task oldTask = new Task(1, 1, 0, "content0", new Date(), null, null);
        Task newTask = new Task(1, 1, 1, null, null, null, null);
        Task task = (Task) fillNotNullToOld(newTask, oldTask);
        Assert.assertEquals(task.getId(), oldTask.getId());
        Assert.assertEquals(task.getContent(), oldTask.getContent());
        Assert.assertEquals(task.getCreateTime(), oldTask.getCreateTime());
        Assert.assertEquals(task.getStatus(), newTask.getStatus());
    }
}