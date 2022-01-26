package com.payshare.server.repository;

import com.payshare.server.model.expense.GroupExpense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupExpenseRepository extends CrudRepository<GroupExpense, Long> {
}
