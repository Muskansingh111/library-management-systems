package com.muskan.singh.lib_management_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@Table(name = "borrower")
@AllArgsConstructor
@NoArgsConstructor
public class Borrower {
    @Id
    @Column(nullable = false)
    private String borrowerId;
    private String name;
    private String email;

    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<BorrowRecord> borrowRecords;
}
