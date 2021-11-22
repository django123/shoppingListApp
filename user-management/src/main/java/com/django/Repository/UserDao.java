package com.django.Repository;

import com.django.domain.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserApp, Long> {


    UserApp findUserAppByUsername(String username);

    UserApp findUserAppByEmail(String email);
}
