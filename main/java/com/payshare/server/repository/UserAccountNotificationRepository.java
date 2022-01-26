package com.payshare.server.repository;

import com.payshare.server.model.user.UserAccount;
import com.payshare.server.model.user.UserAccountNotification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountNotificationRepository extends CrudRepository<UserAccountNotification, Long> {

    List<UserAccountNotification> findAllByUserAccount(UserAccount userAccount);

}
