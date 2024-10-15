package br.edu.iff.ccc.bsi.webdev.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.ccc.bsi.webdev.entities.Tasks;
import br.edu.iff.ccc.bsi.webdev.repository.TasksRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TasksViewController {
    
	@Autowired
	private TasksRepository tasksRepository;

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", tasksRepository.findAll());
        return "tasks";
    }

    @GetMapping("/new")
    public String newTaskForm(Model model) {
        model.addAttribute("task", new Tasks());
        return "new-task";
    }

    @PostMapping
    public String saveTask(@Valid @ModelAttribute("task") Tasks task, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	model.addAttribute("task", task);
        	return "new-task"; // Retorna ao formulário se houver erros
        }
        tasksRepository.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String editTaskForm(@PathVariable Long id, Model model) {
        Tasks task = tasksRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task inválida: " + id));
        model.addAttribute("task", task);
        return "edit-task";
    }

    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Tasks tasks) {
        Tasks existingTask = tasksRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task inválida: " + id));
        existingTask.setName(tasks.getName());
        existingTask.setDescription(tasks.getDescription());
        existingTask.setCompleted(tasks.isCompleted());
        tasksRepository.save(existingTask);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        tasksRepository.deleteById(id);
        return "redirect:/tasks";
    }
}
