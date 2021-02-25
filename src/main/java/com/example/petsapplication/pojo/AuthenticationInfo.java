package com.example.petsapplication.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@Data
@RequestScope
public class AuthenticationInfo {

    private String authenticationInfo;
}
