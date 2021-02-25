package akbal.berkan.app.service;

import akbal.berkan.app.entity.Task;


import java.util.List;
import java.util.Optional;

public interface ITaskService {
    List<Task> findAll();
    Optional<Task> findById(int id);
    Task save(Task task);
}
