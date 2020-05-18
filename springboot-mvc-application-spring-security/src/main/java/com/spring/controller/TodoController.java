package com.spring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.model.Todo;
import com.spring.service.TodoService;

@Controller
public class TodoController {

	@Autowired
	TodoService todoService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	/*
	 * @RequestMapping("/login") public String loginMessage(@RequestParam(name =
	 * "name" , defaultValue = "jack") String name, Model model) {
	 * model.addAttribute("name", name);
	 * 
	 * return "login"; }
	 */

	@RequestMapping(value = "/todopage", method = RequestMethod.GET)
	public String showTodoPage(ModelMap model) {
		model.put("todolist", todoService.retrieveTodos(getLoggedInUserName(model)));
		return "todopage";
	}

	@RequestMapping(value = "/addtodolist", method = RequestMethod.GET)
	public String showTodoPageList(ModelMap model) {
		model.addAttribute("todo", new Todo(0, getLoggedInUserName(model), "Default Desc", new Date(), false));
		return "todo";
	}

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int did) {
		
		if (did ==1) {
			throw new RuntimeException();
		}
		
		todoService.deleteTodo(did);
		return "redirect:/todopage";
	}

	@RequestMapping(value = "/addtodolist", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}
		todoService.addTodo(getLoggedInUserName(model), todo.getDesc(), new Date(), false);
		return "redirect:/todopage";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String ShowUpdateTodo(@RequestParam int uid, ModelMap model) {
		Todo todo = todoService.retrieveTodo(uid);
		model.addAttribute("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}
		todo.setUser(getLoggedInUserName(model));
		todoService.updateTodo(todo);

		return "redirect:/todopage";
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}

}
