package com.users.account.repository;

import com.users.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,String> {
    @Query(value = "SELECT * FROM account WHERE email=:user AND password=:pass OR phone=:user AND password=:pass", nativeQuery = true)
    Optional<Account> signIn(@Param("user") String user, @Param("pass") String pass);

    @Query(value = "SELECT * FROM account WHERE email=:user", nativeQuery = true)
    Optional<Account> signIn(@Param("user") String user);

    @Query(value = "SELECT * FROM account WHERE email=:email OR phone=:phone OR cr_number=:cr", nativeQuery = true)
    Optional<Account> signUp(@Param("email") String email, @Param("phone") String phone, @Param("cr") int cr);
}