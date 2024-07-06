package com.example.springjwt.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class JoinDTO {

    private String username;
    private String password;
}
