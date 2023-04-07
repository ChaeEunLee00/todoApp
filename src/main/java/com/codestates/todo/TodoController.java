package com.codestates.todo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/")
public class TodoController {  //Todo: 대체 왜 todoBackend에서는 patch랑 delete가 안되지??? ㅠㅠ
    private TodoService todoService;
    private TodoMapper todoMapper;

    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    @GetMapping("/main")
    public String main(){
        return "Hello Spring World!";
    }

    @PostMapping
    public ResponseEntity postTodo(@Valid @RequestBody TodoDto.Post todoPostDto){
        // dto -> entity 매핑 후 서비스에서 create 요청한 것 가져오기
        Todo todo = todoService.createTodo(todoMapper.todoPostDtoToTodo(todoPostDto));

        // Todo: URI 추가해줄지말지

        //매핑 후 반환
        return new ResponseEntity<>(todoMapper.todoToTodoResponseDto(todo), HttpStatus.CREATED);
    }

    @GetMapping("/{todo-id}")
    public ResponseEntity getTodo(@PathVariable("todo-id") @Positive int todoId){
        // 매핑후 서비스에서 get 요청한 것 가져오기
        Todo todo = todoService.findTodo(todoId);

        // 매핑후 반환
        return new ResponseEntity<>(todoMapper.todoToTodoResponseDto(todo), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTodos(){  // Todo: page 할지말지
        // 매핑후 서비스에서 get 요청한 것 가져오기
        List<Todo> todos = todoService.findTodos();

        // 매핑후 변환
        return new ResponseEntity<>(todoMapper.todosToTodoResponseDtos(todos), HttpStatus.OK);
    }

    @PatchMapping("/{todo-id}")
    public ResponseEntity patchTodo(@PathVariable("todo-id") @Positive int todoId,
                                    @Valid @RequestBody TodoDto.Patch todoPatchDto){
        //dto에 id값 추가
        todoPatchDto.setId(todoId);
        //매핑후 서비스에 patch 요청 후 받아오기
        Todo todo = todoService.updateTodo(todoMapper.todoPatchDtoToTodo(todoPatchDto));

        //매핑 후 반환
        return new ResponseEntity<>(todoMapper.todoToTodoResponseDto(todo), HttpStatus.OK);

    }

    @DeleteMapping("/{todo-id}")
    public ResponseEntity deleteTodo(@PathVariable("todo-id") @Positive int todoId){
        //서비스에 delete 요청
        todoService.deleteTodo(todoId);

        //http상태 반환
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity deleteTodos(){
        //서비스에 delete 요청
        todoService.deleteTodos();
        //http상태 반환
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
