import controllers.TaskCtrl;
import db.DataAccess;
import models.Task;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created: 15-12-2012
 * @version: 0.1
 * Filename: TaskTest.java
 * Description:
 * @changes
 */

public class TaskTest
{
    private TaskCtrl _taskCtrl;
    public TaskTest()
    {
    }

    @Before
    public void setUp()
    {
        _taskCtrl = new TaskCtrl();
    }

    @Test
    public void insertTask() throws Exception
    {
        Task task = new Task("Test task", "Created by unit test");
        int rowsAffected = _taskCtrl.insertTask(task);

        assertEquals(1, rowsAffected);
    }

    @Test
    public void getTaskById() throws Exception
    {
        Task task = _taskCtrl.getTaskById(1);

        assertNotNull(task);
    }

    @Test
    public void getTaskByTitle() throws Exception
    {
        Task task = _taskCtrl.getTaskByTitle("Test task");

        assertNotNull(task);
    }

    @Test
    public void getAllTask() throws Exception
    {
        ArrayList<Task> tasks = _taskCtrl.getAllTasks();

        assertNotNull(tasks);
    }

    @Test
    public void getNextId() throws Exception
    {
        DataAccess _da = DataAccess.getInstance();
        long id = _da.getNextId("Tasks");
        assertTrue(id > 0);
    }

    @Test
    public void updateTask() throws Exception
    {
        DataAccess _da = DataAccess.getInstance();
        long id = _da.getNextId("Tasks");
        Task task = _taskCtrl.getTaskById((int)id - 1);
        task.setTitle("Test task2");
        task.setDescription("Created by unit test - updated");
        int rowsAffected = _taskCtrl.updateTask(task);

        assertEquals(1, rowsAffected);
    }

    @Test
    public void deleteTask() throws Exception
    {
        DataAccess _da = DataAccess.getInstance();
        long id = _da.getNextId("Tasks");
        Task task = _taskCtrl.getTaskById((int)id - 1);
        int rowsAffected = _taskCtrl.deleteTask(task);

        assertEquals(1, rowsAffected);
    }
}
