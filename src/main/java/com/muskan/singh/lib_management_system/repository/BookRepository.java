package com.muskan.singh.lib_management_system.repository;
import dev.apurvasingh.minilibrary.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book , String>
{
    List<Book> findByAvailable(boolean available);

}
