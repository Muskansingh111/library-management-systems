package com.muskan.singh.lib_management_system.repository;

import dev.apurvasingh.minilibrary.models.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower ,String>
{

}
