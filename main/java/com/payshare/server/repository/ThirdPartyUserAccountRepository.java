package com.payshare.server.repository;

import com.payshare.server.model.user.ThirdPartyUserAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ThirdPartyUserAccountRepository extends CrudRepository<ThirdPartyUserAccount, Long> {
    Optional<ThirdPartyUserAccount> findByEmail(String email);

    boolean existsByEmail(String email);
}
