package org.blackapple.backend.communication.task.service.impl;

import org.blackapple.backend.communication.task.model.Task;
import org.blackapple.backend.communication.task.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Override
    public List<Task> getTasksByIdProject(int id) {
        return null;
    }

    @Override
    public List<Task> getTasksByIdUser(int id) {
        return null;
    }
}
