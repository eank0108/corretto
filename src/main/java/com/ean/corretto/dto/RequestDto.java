package com.ean.corretto.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

public class RequestDto {
    @Getter
    @Setter
    public static class Create{
        private Integer boardId;
        @Size(max = 128, message = "max length 128")
        private String title;
        private String content;
    }
    @Getter
    @Setter
    public static class Update{
        private String title;
        private String content;
    }
}
