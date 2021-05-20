package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ToDoController 
{	
	@Autowired
	ToDoRepository todoRepository;

	@RequestMapping(value="/top", method = RequestMethod.GET)
	public ModelAndView showTop(ModelAndView mv) 
	{
		//画面表示するhtmlを決定する
		mv.setViewName("top");
		//DBからtodoリストを全件取得し、画面に表示する
		mv.addObject("todolist", todoRepository.findAll());

		return mv;
	}
	
	@RequestMapping(value="/top/new", method = RequestMethod.GET)
	public String showAdd() 
	{
		return "add";
	}
	
	@RequestMapping(value="/top/create", method = RequestMethod.POST)
	public ModelAndView toDoAdd
			(
			@RequestParam("todo") String todo,
			@RequestParam("priority") String priority,
			@RequestParam("deadline") String deadline,
			ModelAndView mv
			) 
	{
		ToDo todolist = new ToDo(todo, priority, deadline); 
		
		// DB登録
		todoRepository.saveAndFlush(todolist);
		mv.setViewName("redirect:/top");
		return mv;
	}
	//更新画面に移動
	@RequestMapping(value="/top/{code}/edit", method = RequestMethod.GET)
	public ModelAndView toDoEdit(
			@PathVariable("code") String code,
			ModelAndView mv) 
	{

		mv.addObject("todolist", todoRepository.findById(Integer.parseInt(code)).get());		
		mv.setViewName("edit");
		return mv;
	}
	
	@RequestMapping(value="/top/update", method = RequestMethod.POST)
	public ModelAndView toDoUpdate(
			@RequestParam("code") String code,
			@RequestParam("todo") String todo,
			@RequestParam("priority") String priority,
			@RequestParam("deadline") String deadline,
			ModelAndView mv
			) 
	{
		ToDo todolist = todoRepository.findById(Integer.parseInt(code)).get();

		todolist.setTodo(todo);
		todolist.setPriority(priority);
		todolist.setDeadline(deadline);

		todoRepository.saveAndFlush(todolist);
		mv.setViewName("redirect:/top");
		return mv;
	}
}
