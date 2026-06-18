package frontend_traffic.services.implementation;

import java.util.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import frontend_traffic.dto.account_dto;
import frontend_traffic.models.account_entity;
import frontend_traffic.repository.account_repository;
import frontend_traffic.services.interfaces.account_inter_service;
import lombok.*;

@Service
@AllArgsConstructor
public class account_service implements account_inter_service {
    private final account_repository accountRepository;

    // lấy tất cả thông tin
    @Override
    public List<account_entity> getAll() {
        return accountRepository.findAll();
    }

    // tìm id để lấy dữ liệu
    @Override
    public account_entity getByIdAccount(UUID id) {
        account_entity account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account already exists!"));
        return account;
    }

    // đăng ký tài khoản / tạo tài khoản
    @Override
    public account_entity register(account_dto request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        account_entity entity = new account_entity();
        entity.setAccount(request.getAccount());
        entity.setPassword(passwordEncoder.encode(request.getPassword()));
        return accountRepository.save(entity);
    }

    // xóa tài khoản với id
    @Override
    public account_entity deleteAccount(UUID id) {
        account_entity result = getByIdAccount(id);
        accountRepository.delete(result);
        return result;
    }

    // cập nhật thông tin với id
    @Override
    public account_entity updateAccount(UUID id, account_dto request) {
        account_entity result = getByIdAccount(id);
        result.setAccount(request.getAccount());
        result.setPassword(request.getPassword());
        return accountRepository.save(result);
    }

    // hàm update kiểm tra id nếu id đó tồn tại thì cập nhật lại thông tin,
    // còn ngược lại thì tạo mới
    /*
     * @Override
     * public account_entity checkIdAccount(UUID id, account_dto request) {
     * 
     * // viết theo kiểu if else dễ hiểu
     * Optional<account_entity> account = accountRepository.findById(id);
     * if (account.isPresent()) {
     * return updateAccount(id, request);
     * }
     * return register(request);
     * 
     * // viết tắt ngắn gọn hơn
     * // return accountRepository.findById(id)
     * // .map(existing -> updateAccount(id, request))
     * // .orElseGet(() -> register(request));
     * }
     */

}
