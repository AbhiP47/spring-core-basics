package com.abhinav.transactions.repository;




import com.abhinav.transactions.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}