package com.payshare.server.repository;

import com.payshare.server.model.user.UserAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
    Optional<UserAccount> findByEmail(String email);
}
