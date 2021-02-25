package akbal.berkan.app.service;

import akbal.berkan.app.entity.Task;
import akbal.berkan.app.repository.ITaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements ITaskService {

    private final ITaskRepository m_taskRepository;

    public TaskService(ITaskRepository m_taskRepository)
    {
        this.m_taskRepository = m_taskRepository;
    }


    @Override
    public List<Task> findAll()
    {
        return m_taskRepository.findAll();
    }

    @Override
    public Optional<Task> findById(int id)
    {
            return m_taskRepository.findById(id);
    }

    @Override
    public Task save(Task task)
    {
            return m_taskRepository.save(task);
    }
}
