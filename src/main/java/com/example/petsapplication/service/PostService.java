package com.example.petsapplication.service;

import com.example.petsapplication.dto.PostRequestDTO;
import com.example.petsapplication.dto.ResponseDTO;

public interface PostService {

    ResponseDTO save(PostRequestDTO postRequestDTO);
}
