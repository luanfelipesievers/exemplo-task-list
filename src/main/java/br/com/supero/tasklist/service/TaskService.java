package br.com.supero.tasklist.service;

import br.com.supero.tasklist.entity.Task;
import br.com.supero.tasklist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * Serviço da entidade Task
 *
 * @author Luan Felipe Sievers
 * @version 1.0
 * @since 02/11/2018
 */
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Encontra todas as tasks que não possuem data de deleção
     *
     * @return Iterable<Task> Lista das tasks
     * @author Luan Felipe Sievers
     * @since 02/11/2018
     */
    public Iterable<Task> findAllTasks() {
        return this.taskRepository.findAllNonDeleted();
    }

    /**
     * Salva uma task
     *
     * @param task É o próprio objeto que irá ser salvo
     * @author Luan Felipe Sievers
     * @since 02/11/2018
     */
    public void saveTask(Task task) {
        task.setCreateDate(new Date());
        this.taskRepository.save(task);
    }

    /**
     * Atualiza uma task com a data de deleção
     *
     * @param id É o id da task
     * @author Luan Felipe Sievers
     * @since 02/11/2018
     */
    public void deleteTask(Integer id) {
        Optional<Task> taskOptional = this.taskRepository.findById(id);
        if(taskOptional.isPresent()){
            taskOptional.get().setDeleteDate(new Date());
            taskOptional.get().setUpdateDate(new Date());
            this.taskRepository.save(taskOptional.get());
        }
    }

    /**
     * Atualiza uma task com uma data de deleção nula
     *
     * @param id É o id da task
     * @author Luan Felipe Sievers
     * @since 02/11/2018
     */
    public void updateDoing(Integer id){
        Optional<Task> taskOptional = this.taskRepository.findById(id);
        Task task = taskOptional.get();
        task.setConclusionDate(null);
        this.taskRepository.save(task);
    }

    /**
     * Atualiza uma task com uma data de deleção atual
     *
     * @param id É o id da task
     * @author Luan Felipe Sievers
     * @since 02/11/2018
     */
    public void updateDone(Integer id){
        Optional<Task> taskOptional = this.taskRepository.findById(id);
        Task task = taskOptional.get();
        task.setConclusionDate(new Date());
        this.taskRepository.save(task);
    }

    /**
     * Atualiza uma task
     *
     * @param task É o objeto task que irá atualizar
     * @author Luan Felipe Sievers
     * @since 02/11/2018
     */
    public void updateTask(Task task) {
        Optional<Task> taskOptional = this.taskRepository.findById(task.getId());
        if(taskOptional.isPresent()){
            String title = task.getTitle() ;
            String description = task.getDescription();
            Date conclusionDate = task.getConclusionDate();
            task = taskOptional.get();
            task.setTitle(title);
            task.setDescription(description);
            task.setUpdateDate(new Date());
            task.setConclusionDate(conclusionDate);
            this.taskRepository.save(task);
        }
    }


}
