package br.com.supero.tasklist.repository;

import br.com.supero.tasklist.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Respositorio da entidade Task
 *
 * @author Luan Felipe Sievers
 * @version 1.0
 * @since 02/11/2018
 */
public interface TaskRepository extends CrudRepository<Task, Integer> {

    @Query("from Task where deleteDate is null")
    Iterable<Task> findAllNonDeleted();
}