package com.example.petsapplication.service;

import com.example.petsapplication.dto.GeneralResponseDTO;
import com.example.petsapplication.dto.PostRequestDTO;

public interface GeneralService {

    GeneralResponseDTO getAll();

    PostRequestDTO save(PostRequestDTO postRequestDTO);
}

