package com.numble.challenge.novel.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorDTO {

    private int errorCode;
    private String errorMessage;
    private String errorPath;


}
