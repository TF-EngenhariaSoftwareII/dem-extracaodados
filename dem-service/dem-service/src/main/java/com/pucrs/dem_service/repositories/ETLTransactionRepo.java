package com.pucrs.dem_service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pucrs.dem_service.entities.ETLTransaction;


@Repository
public interface ETLTransactionRepo extends JpaRepository<ETLTransaction, Long> {

}
