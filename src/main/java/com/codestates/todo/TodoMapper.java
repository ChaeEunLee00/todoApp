package com.codestates.todo;

import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    default Todo todoPostDtoToTodo(TodoDto.Post todoPostDto) {
        if ( todoPostDto == null ) {
            return null;
        }

        Todo todo = new Todo();

        todo.setTitle( todoPostDto.getTitle() );
        todo.setTodoOrder( todoPostDto.getOrder() );
        todo.setCompleted( todoPostDto.isCompleted() );

        return todo;
    }

    default Todo todoPatchDtoToTodo(TodoDto.Patch todoPatchDto) {
        if ( todoPatchDto == null ) {
            return null;
        }

        Todo todo = new Todo();

        todo.setId( todoPatchDto.getId() );
        todo.setTitle( todoPatchDto.getTitle() );
        todo.setTodoOrder( todoPatchDto.getOrder() );
        todo.setCompleted( todoPatchDto.isCompleted() );

        return todo;
    }

    default TodoDto.Response todoToTodoResponseDto(Todo todo) {
        if ( todo == null ) {
            return null;
        }

        TodoDto.Response response = new TodoDto.Response();

        response.setId( todo.getId() );
        response.setTitle( todo.getTitle() );
        response.setOrder( todo.getTodoOrder() );
        response.setCompleted( todo.getCompleted() );

        return response;
    }

    List<TodoDto.Response> todosToTodoResponseDtos(List<Todo> todos);
}
