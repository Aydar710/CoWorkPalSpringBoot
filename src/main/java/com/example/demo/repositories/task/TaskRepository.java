package com.example.demo.repositories.task;

import com.example.demo.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAll();

    Task save(Task task);

    Optional<Task> findById(Long id);

    List<Task> findAllByProject_Id(Long projectId);


    @Modifying
    @Transactional
    @Query(value = "update task set is_done = true where id = :id", nativeQuery = true)
    int markTaskAsDone(@Param("id") Long id);

    /*@Query(value = "select * from task where project_id = ? and is_done = false", nativeQuery = true)
    List<Task> getAllNotActiveTasks(@Param("projectId") Long projectId);*/

    //getAllNotActiveTasks(int projectId)
    List<Task> getAllByProjectId(Long projectId);

    List<Task> getAllByProjectIdAndIsDone(Long projectId, boolean done);
}
