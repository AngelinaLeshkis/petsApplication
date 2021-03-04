package com.example.petsapplication.transactionservice.transactionserviceimpl;

import com.example.petsapplication.dto.CatDTO;
import com.example.petsapplication.dto.CreateCatDTO;
import com.example.petsapplication.entity.Owner;
import com.example.petsapplication.repository.CatRepository;
import com.example.petsapplication.transactionservice.CatTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import static com.example.petsapplication.utils.ConstantValues.CAT_ID;
import static com.example.petsapplication.utils.ConstantValues.DOG_ID;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;
import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

@Service
@RequiredArgsConstructor
public class CatTransactionServiceImpl implements CatTransactionService {

    private final CatRepository catRepository;

    @Override
    public CatDTO save(CreateCatDTO catDTO, Owner owner) {
        try {
            CatDTO cat = catRepository.save(catDTO, owner);
            currentRequestAttributes()
                    .setAttribute(DOG_ID, cat.getId(), SCOPE_REQUEST);
            return cat;
        } catch (HttpClientErrorException | HttpServerErrorException errorException) {
            throw errorException;
        }
    }

    @Override
    public void rollback() {
        long id = (long) currentRequestAttributes().getAttribute(CAT_ID, SCOPE_REQUEST);
        catRepository.delete(id);
    }
}
