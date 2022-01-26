package com.payshare.server.repository;

import com.payshare.server.model.user.Friendship;
import com.payshare.server.model.user.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FriendshipRepository extends CrudRepository<Friendship, Long> {
    List<Friendship> getAllByPerson1AndConfirmed(Person person, boolean confirmed);

    List<Friendship> getAllByPerson2AndConfirmed(Person person, boolean confirmed);

    Optional<Friendship> getByPerson1AndPerson2AndConfirmedIsTrue(Object object, Object object2);
}
