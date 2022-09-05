package com.csv.test.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "university")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "state")
    private String state;

    @Column(name = "name")
    private String name;

    @Column(name = "institution_type")
    private String institutionType;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "website")
    private String website;

}
