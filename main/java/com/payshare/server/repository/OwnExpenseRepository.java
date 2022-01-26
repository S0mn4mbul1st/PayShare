package com.payshare.server.repository;

import com.payshare.server.model.expense.OwnExpense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnExpenseRepository extends CrudRepository<OwnExpense, Long> {
}
