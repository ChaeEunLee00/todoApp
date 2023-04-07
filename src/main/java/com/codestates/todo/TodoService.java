package com.codestates.todo;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo){

        //이미 같은 투두가 있는지 확인(order로)
        //verifyExistTodo(todo.getTodoOrder());  => 할필요 없나?

        return todoRepository.save(todo);
    }

    public Todo findTodo(int todoId){
        //있는 투두인지 확인하고 찾기
        return findVerifiedTodo(todoId);
    }

    public List<Todo> findTodos(){

        //return todoRepository.findAll(); => todoBackend 에서는 알아서 정렬이 되지만, postman에서는 정렬되지 않음
        return todoRepository.findAll(Sort.by(Sort.Direction.ASC, "todoOrder"));
    }

    public Todo updateTodo(Todo todo){

        //있는 투두인지 확인
        Todo findTodo = findVerifiedTodo(todo.getId());

        Optional.ofNullable(todo.getTodoOrder())
                .ifPresent(todoOrder -> {
                    //verifyExistTodo(todoOrder);  //order 겹치는지 확인 => 할 필요 없나?
                    findTodo.setTodoOrder(todoOrder);
                });
        Optional.ofNullable(todo.getTitle())
                .ifPresent(title -> findTodo.setTitle(title));
        Optional.ofNullable(todo.getCompleted())
                .ifPresent(completed -> findTodo.setCompleted(completed));


        return todoRepository.save(findTodo);
    }


    public void deleteTodo(int todoId){
        // 있는 투두인지
        Todo todo = findVerifiedTodo(todoId);

        todoRepository.delete(todo);

    }
    public void deleteTodos(){
        todoRepository.deleteAll();
    }

    private void verifyExistTodo(int todoOrder){
        Optional<Todo> todo = todoRepository.findByTodoOrder(todoOrder);
        if(todo.isPresent()) throw new RuntimeException();
    }

    private Todo findVerifiedTodo(int todoId){
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);

        Todo findTodo = optionalTodo
                .orElseThrow(()-> new RuntimeException());

        return findTodo;
    }
}
