package com.wendel.DesafioPicpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wendel.DesafioPicpay.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
