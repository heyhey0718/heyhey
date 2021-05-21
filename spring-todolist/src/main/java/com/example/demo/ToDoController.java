package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	@Autowired
	Congratulations congratulations;
	
	//Top画面に移動
	@RequestMapping(value="/top", method = RequestMethod.GET)
	public ModelAndView showTop(ModelAndView mv) 
	{
		//画面表示するhtmlを決定する
		mv.setViewName("top");
		//DBからtodoリストを全件取得し、画面に表示する
		mv.addObject("todolist", todoRepository.findAll());

		return mv;
	}
	
	//新規登録画面に移動
	@RequestMapping(value="/top/new", method = RequestMethod.GET)
	public String showAdd() 
	{
		return "add";
	}
	
	//データベースに追加し、トップ画面に追加した値を描画
	@RequestMapping(value="/top/create", method = RequestMethod.POST)
	public ModelAndView toDoAdd
			(
			@RequestParam("todo") String todo,
			@RequestParam("priority") String priority,
			@RequestParam("deadline") String deadline,
			ModelAndView mv
			) 
	{
		ToDoList todolist = new ToDoList(todo, priority, deadline); 
		
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
	
	//データを更新してトップ画面に描画
	@RequestMapping(value="/top/update", method = RequestMethod.POST)
	public ModelAndView toDoUpdate(
			@RequestParam("code") String code,
			@RequestParam("todo") String todo,
			@RequestParam("priority") String priority,
			@RequestParam("deadline") String deadline,
			ModelAndView mv
			) 
	{
		ToDoList todolist = todoRepository.findById(Integer.parseInt(code)).get();

		todolist.setTodo(todo);
		todolist.setPriority(priority);
		todolist.setDeadline(deadline);

		todoRepository.saveAndFlush(todolist);
		mv.setViewName("redirect:/top");
		return mv;
	}
	
	/*タスク５削除機能
	@RequestMapping(value="/top/delete", method = RequestMethod.POST)
	public String toDoDelete(
			@RequestParam("code") String code
			) 
	{

		todoRepository.deleteById(Integer.parseInt(code));
		return "redirect:/top";
		
	}*/
	
	//タスク６トップ画面にねぎらいのメッセージを追加
	@RequestMapping(value="/top/delete", method = RequestMethod.POST)
	
		/*1レコード削除
		todoRepository.deleteById(Integer.parseInt(code));
		
		//ここでcongの値を表示できる処理をしたい
		Congratulations cong = new Congratulations();
		cong.congratulations(String[]);→失敗
		
		//クラスのデータ型　変数名　＝　congratulations.congratulations();
		String ret = congratulations.congratulations();
		
		
		return "redirect:/top";*/
	
	 // Delete関数の返却する型を変更する
		public ModelAndView toDoDelete(
				@RequestParam("code") String code,
	            // 引数にmvを追加
	            ModelAndView mv
				) 
		{
			//ここでcongの値を表示指せる処理をしたい
	        Congratulations cong = new Congratulations();
	        String[] array = {};
	        String str = cong.congratulations(array);
	        // mvにビューと返却する値をセット
	        mv.addObject("str", cong);
			mv.setViewName("redirect:/top");
			return mv;
		}
		
	}

