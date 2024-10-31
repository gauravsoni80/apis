package com.users.account.service;

import com.users.account.entity.Account;
import com.users.account.repository.AccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AccountService {


    @Autowired
    private AccountRepository repository;

    @Autowired
    private EntityManagerFactory factory;

    public List<Account> accounts(){
        return repository.findAll();
    }

    public Optional<Account> signin(String user, String pass){
        return repository.signIn(user, pass);
    }

    public Optional<Account> signin(String user){
        return repository.signIn(user);
    }

    public Optional<Account> signup(String email,String phone,int cr){
        return repository.signUp(email, phone, cr);
    }

    public Account save(Account account){
        return repository.save(account);
    }

    public Object updateStatusCode(int status,String id) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction tn = manager.getTransaction();
        tn.begin();
        Query query = manager.createNativeQuery("UPDATE account SET status=:status WHERE id=:id");
        query.setParameter("status", status);
        query.setParameter("id", id);
        query.executeUpdate();
        tn.commit();
        return manager.find(Account.class, id);
    }

}