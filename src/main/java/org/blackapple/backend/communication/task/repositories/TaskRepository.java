package org.blackapple.backend.communication.task.repositories;

import org.blackapple.backend.communication.task.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {
}
