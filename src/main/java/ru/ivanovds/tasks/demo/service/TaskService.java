package ru.ivanovds.tasks.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ivanovds.tasks.demo.dto.TaskDto;
import ru.ivanovds.tasks.demo.entity.Person;
import ru.ivanovds.tasks.demo.entity.Task;
import ru.ivanovds.tasks.demo.repository.PersonRepository;
import ru.ivanovds.tasks.demo.repository.TaskRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    private final TaskRepository taskRepository;

    private final PersonRepository personRepository;

    public TaskDto getTaskById(Long id) throws Exception {
        try {
            Task task = taskRepository.findById(id).orElseThrow();

            return new TaskDto(task);
        } catch (Exception e) {
            log.error(e.getMessage());

            throw new Exception("Ошибка в сервисе");
        }
    }

    // Была задумка под получение задач определенного сотрудника
    public List<TaskDto> getAllTaskByPerson(Person person) {
        List<Task> tasks = person.getTasks();
        List<TaskDto> tasksDto = new ArrayList<>(tasks.size());

        tasks.forEach(
                it -> tasksDto.add(new TaskDto(it))
        );

        return tasksDto;
    }

    public List<TaskDto> getAllTask() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskDto> tasksDto = new ArrayList<>();

        tasks.forEach(
                it -> tasksDto.add(new TaskDto(it))
        );

        return tasksDto;
    }

    //TODO Обдумать (оптимизировать)
    public boolean updateTaskById(Long id, TaskDto task) {
        try {
            Task taskOld = taskRepository.findById(task.getId()).orElseThrow();
            Person person = personRepository.findById(id).orElseThrow();

            taskOld.setDescription(task.getDescription());
            taskOld.setPerson(person);

            if (isPriorityValid(taskOld, task)) {
                taskOld.setPriority(task.getPriority());
            } else {
                throw new Exception();
            }

            taskRepository.save(taskOld);

            return true;
        } catch (Exception e) {
            log.error(e.getMessage());

            return false;
        }
    }

    //TODO Обдумать (оптимизировать)
    @Transactional
    public boolean deleteTaskFromPersonById(Long idPerson, Long idTask) {
        try {
            Person person = personRepository.findById(idPerson).orElseThrow();
            Task task = taskRepository.findById(idTask).orElseThrow();
            person.delTask(task);

            personRepository.save(person);

            return true;
        } catch (Exception e) {
            log.error(e.getMessage());

            return false;
        }

    }

    public boolean deleteAllTask() {
        try {
            taskRepository.deleteAll();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isPriorityValid(Task taskOld, TaskDto taskNew) {

        Integer max = taskRepository.findMaximum();
        Integer min = taskRepository.findMinimum();

        if (taskOld.getPriority() == min && taskOld.getPriority() == max) {
            if (taskOld.getPriority() == min && taskNew.getPriority() < min) {
                return false;
            } else return taskOld.getPriority() != max || taskNew.getPriority() <= max;
        } else {
            return true;
        }
    }
}
