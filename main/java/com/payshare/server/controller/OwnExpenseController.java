package com.payshare.server.controller;

import com.payshare.server.dto.expense.OwnExpenseCreateDto;
import com.payshare.server.dto.expense.OwnExpenseReadDto;
import com.payshare.server.model.expense.OwnExpense;
import com.payshare.server.model.user.Person;
import com.payshare.server.security.JwtUtils;
import com.payshare.server.service.OwnExpenseService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/expenses")
@RequiredArgsConstructor
public class OwnExpenseController {

    @NonNull
    private final OwnExpenseService ownExpenseService;

    @NonNull
    private final JwtUtils jwtUtils;

    @GetMapping
    public ResponseEntity<?> getAllExpenses(Authentication authentication) {
        try {
            Person person = jwtUtils.getPersonFromAuthentication(authentication);
            return ResponseEntity.ok().body(
                    person.getOwnExpenses().stream().map(OwnExpenseReadDto::new));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> addOwnExpense(@RequestBody @Valid OwnExpenseCreateDto expenseDto,
            Authentication authentication) {
        try {
            Person person = jwtUtils.getPersonFromAuthentication(authentication);
            OwnExpense ownExpense = ownExpenseService.addExpense(expenseDto, person);
            return ResponseEntity.ok(new OwnExpenseReadDto(ownExpense));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
