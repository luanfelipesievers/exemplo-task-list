package br.com.tasklist.controller;

import br.com.tasklist.entity.Task;
import br.com.tasklist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller principal da aplicação
 *
 * @author Luan Felipe Sievers
 * @version 1.0
 * @since 02/11/2018
 */
@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * Request inicial da aplicação que retorna a página inicial
     *
     * @author Luan Felipe Sievers
     * @since 02/11/2018
     * @return model
     */
    @RequestMapping(value = "/")
    public ModelAndView home() {
        ModelAndView model = new ModelAndView("home", "task", new Task());
        model.addObject("taskList", this.taskService.findAllTasks());
        return model;
    }

    /**
     * Request que insere uma task
     *
     * @param task É o próprio objeto que irá ser salvo
     * @author Luan Felipe Sievers
     * @since 02/11/2018
     * @return model
     */
    @RequestMapping(value = "/insert")
    public ModelAndView insertTask(Task task) {
        this.taskService.saveTask(task);
        return new ModelAndView("redirect:/");
    }

    /**
     * Request que atualiza uma task
     *
     * @param task É o próprio objeto que irá ser atualizado
     * @author Luan Felipe Sievers
     * @since 02/11/2018
     * @return model
     */
    @RequestMapping(value = "/update")
    public ModelAndView updateTask(Task task) {
        this.taskService.updateTask(task);
        return new ModelAndView("redirect:/");
    }

    /**
     * Request que atualiza uma task para feito
     *
     * @param id É o id do task
     * @author Luan Felipe Sievers
     * @since 02/11/2018
     */
    @RequestMapping(value = "/updateDone")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateDone(Integer id) {
        this.taskService.updateDone(id);
    }

    /**
     * Request que atualiza uma task para fazendo
     *
     * @param id É o id do task
     * @author Luan Felipe Sievers
     * @since 02/11/2018
     */
    @RequestMapping(value = "/updateDoing")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateDoing(Integer id) {
        this.taskService.updateDoing(id);
    }

    /**
     * Request que deleta uma task
     *
     * @param id É o id do task
     * @author Luan Felipe Sievers
     * @since 02/11/2018
     */
    @RequestMapping(value = "/delete/{id}")
    public ModelAndView deleteTask(@PathVariable(value="id") Integer id) {
        this.taskService.deleteTask(id);
        return new ModelAndView("redirect:/");
    }


}
