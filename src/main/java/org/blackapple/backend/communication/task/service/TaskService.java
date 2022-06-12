package org.blackapple.backend.communication.task.service;

import org.blackapple.backend.communication.task.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> getTasksByIdProject(int id);

    List<Task> getTasksByIdUser(int id);
}
