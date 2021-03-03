package com.example.petsapplication.service;

import com.example.petsapplication.dto.GeneralResponseDTO;
import com.example.petsapplication.dto.PostRequestDTO;
import com.example.petsapplication.dto.ResponseDTO;

public interface GeneralService {

    GeneralResponseDTO getAll();

    ResponseDTO save(PostRequestDTO postRequestDTO);
}

