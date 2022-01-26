package com.payshare.server.repository;

import com.payshare.server.model.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {
    Optional<Currency> findCurrencyByAbbreviation(String abbreviation);

    boolean existsByAbbreviation(String abbreviation);
}
