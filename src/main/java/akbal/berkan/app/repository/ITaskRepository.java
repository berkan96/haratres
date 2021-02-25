package akbal.berkan.app.repository;

import akbal.berkan.app.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepository extends JpaRepository<Task, Integer> {

}
