package com.codestates.todo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TodoDto {

    @Getter
    public static class Post{
        @NotBlank
        String title;

        @NotNull
        Integer order;
        @NotNull
        boolean completed;
    }

    @Getter
    public static class Patch{
        int id;
        String title;
        Integer order;
        boolean completed;

        public void setId(int id) {
            this.id = id;
        }
    }

    @Getter
    @Setter
    public static class Response{
        int id;
        String title;
        Integer order;
        boolean completed;

    }
}
