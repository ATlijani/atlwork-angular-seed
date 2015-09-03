package com.atlwork.angularseed.app.repository;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import com.atlwork.angularseed.app.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByLogin(String login);

    User findOneByEmail(String email);

    User findOneByActivationKey(String activationKey);

    List<User> findAllByActivatedIsFalseAndCreatedDateBefore(DateTime dateTime);

    User findOneByResetKey(String resetKey);
}
