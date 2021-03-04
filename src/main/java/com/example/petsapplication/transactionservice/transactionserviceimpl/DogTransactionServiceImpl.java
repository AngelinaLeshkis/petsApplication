package com.example.petsapplication.transactionservice.transactionserviceimpl;

import com.example.petsapplication.dto.CreateDogDTO;
import com.example.petsapplication.dto.DogDTO;
import com.example.petsapplication.entity.Owner;
import com.example.petsapplication.repository.DogRepository;
import com.example.petsapplication.transactionservice.DogTransactionService;
import com.example.petsapplication.transactionservice.RollbackTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import static com.example.petsapplication.utils.ConstantValues.DOG_ID;
import static com.example.petsapplication.utils.ConstantValues.OWNER_ID;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;
import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

@Service
@RequiredArgsConstructor
public class DogTransactionServiceImpl implements DogTransactionService {

    private final DogRepository dogRepository;

    @Override
    public DogDTO save(CreateDogDTO dogDTO, Owner owner) {
        try {
            DogDTO dog = dogRepository.save(dogDTO, owner);
            currentRequestAttributes()
                    .setAttribute(DOG_ID, dog.getId(), SCOPE_REQUEST);
            return dog;
        } catch (HttpClientErrorException | HttpServerErrorException errorException) {
            throw errorException;
        }
    }

    @Override
    public void rollback() {
        long id = (long) currentRequestAttributes().getAttribute(DOG_ID, SCOPE_REQUEST);
        dogRepository.delete(id);
    }
}
