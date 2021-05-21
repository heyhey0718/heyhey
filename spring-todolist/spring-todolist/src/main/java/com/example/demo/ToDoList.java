package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="todolist")
public class ToDoList
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="code")
	private Integer code;

	@Column(name="todo")
	private String todo;

	@Column(name="priority")
	private String priority;

	@Column(name="deadline")
	private String deadline;

	public ToDoList() 
	{
		super();
	}

	public ToDoList(Integer code, String todo, String priority, String deadline) 
	{
		this(todo, priority, deadline);
		this.code = code;
	}

	public ToDoList(String todo, String priority, String deadline) 
	{
		super();
		this.todo = todo;
		this.priority = priority;
		this.deadline = deadline;
	}

	public Integer getCode() 
	{
		return code;
	}

	public void setCode(Integer code) 
	{
		this.code = code;
	}

	public String getTodo() 
	{
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}	
}

