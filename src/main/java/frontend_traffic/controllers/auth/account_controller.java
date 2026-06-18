package frontend_traffic.controllers.auth;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import frontend_traffic.dto.account_dto;
import frontend_traffic.helpers.errors.success_handle;
import frontend_traffic.models.account_entity;
import frontend_traffic.services.implementation.account_service;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.*;

@Controller
@AllArgsConstructor
public class account_controller {
    private final account_service account_service;

    @GetMapping("/login")
    public String pageSignIn(Model model) {
        return "modules/frontend/pages/login";
    }

    

    @GetMapping("/account")
    @ResponseBody
    public List<account_entity> getAllAccount() {
        return account_service.getAll();
    };

    @GetMapping("/account/get:{id}")
    @ResponseBody
    public ResponseEntity<account_entity> getByIdAccount(@PathVariable UUID id) {
        return ResponseEntity.ok(account_service.getByIdAccount(id));
    }

    @DeleteMapping("/account/delete:{id}")
    public ResponseEntity<account_entity> deleteAccount(@PathVariable UUID id) {
        return ResponseEntity.ok(account_service.deleteAccount(id));
    }

    @PostMapping("/account/sign-up")
    public ResponseEntity<success_handle<account_entity>> postRegister(@RequestBody @Valid account_dto request) {
        account_service.register(request);
        return ResponseEntity.ok(new success_handle<>(HttpStatus.NO_CONTENT, "Register successfully!"));
    }

    @PutMapping("/account/update:{id}")
    public ResponseEntity<success_handle<account_entity>> updateAccount(@PathVariable UUID id,
            @RequestBody @Valid account_dto request) {
        return ResponseEntity.ok(new success_handle<>(HttpStatus.OK, "Update successfully!",
                account_service.updateAccount(id, request)));
    }
}

// region @PathVariable vs @RequestParam
/*
 * - Hai annotation này đều dùng để lấy dữ liệu từ URL, nhưng vị trí lấy khác
 * nhau.
 * - @PathVariable : Lấy dữ liệu từ đường dẫn URL.
 * vd: @GetMapping("/account/{id}")
 * or @GetMapping("/account/{accountId}/roles/{roleId}")
 * - @RequestParam : Lấy dữ liệu từ query string.
 * vd: @GetMapping("/account") request: GET /account?id=123 or GET
 * /account?keyword=admin&page=1
 */
// endregion