package frontend_traffic.services.interfaces;

import java.util.*;
import frontend_traffic.dto.account_dto;
import frontend_traffic.models.account_entity;

public interface account_inter_service {
    account_entity register(account_dto request);

    List<account_entity> getAll();

    account_entity getByIdAccount(UUID id);

    account_entity deleteAccount(UUID id);

    account_entity updateAccount(UUID id, account_dto request);

    // account_entity checkIdAccount(UUID id, account_dto request);
}
