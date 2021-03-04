package com.example.petsapplication.transactionservice.transactionserviceimpl;

import com.example.petsapplication.dto.CreateOwnerDTO;
import com.example.petsapplication.entity.Owner;
import com.example.petsapplication.repository.OwnerRepository;
import com.example.petsapplication.transactionservice.OwnerTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import static com.example.petsapplication.utils.ConstantValues.OWNER_ID;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;
import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

@Service
@RequiredArgsConstructor
public class OwnerTransactionServiceImpl implements OwnerTransactionService {

    private final OwnerRepository ownerRepository;

    @Override
    public Owner save(CreateOwnerDTO ownerDTO) {
        try {
            Owner owner = ownerRepository.save(ownerDTO);
            currentRequestAttributes()
                    .setAttribute(OWNER_ID, owner.getId(), SCOPE_REQUEST);
            return owner;
        } catch (HttpClientErrorException | HttpServerErrorException errorException) {
            throw errorException;
        }
    }

    @Override
    public void rollback() {
        long id = (long) currentRequestAttributes().getAttribute(OWNER_ID, SCOPE_REQUEST);
        ownerRepository.delete(id);
    }
}
