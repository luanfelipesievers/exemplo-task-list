package br.com.supero.tasklist.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


/**
 * Entidade que representa uma tarefa
 *
 * @author Luan Felipe Sievers
 * @version 1.0
 * @since 02/11/2018
 */
@Data
@Entity
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String title;
    private String description;
    private Date createDate;
    private Date updateDate;
    private Date conclusionDate;
    private Date deleteDate;
}
