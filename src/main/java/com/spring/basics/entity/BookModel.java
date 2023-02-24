package com.spring.basics.entity;

import com.spring.basics.dto.BookDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Entity
@Table
@Data
@NoArgsConstructor
public class BookModel
{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String phoneNumber;
    @ElementCollection
    @CollectionTable(name = "addresses", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "address")
    private List<String> address;
    @ElementCollection
    @CollectionTable(name = "cities", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "city")
    private List<String> city;
    @ElementCollection
    @CollectionTable(name = "states", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "state")
    private List<String> state;
    @ElementCollection
    @CollectionTable(name = "zipCodes", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "zipCode")
    private List<String> zipCode;
    private String email;

    public BookModel(BookDTO bookDTO)
    {
        this.name=bookDTO.getName();
        this.phoneNumber=bookDTO.getPhoneNumber();
        this.email=bookDTO.getEmail();
        this.address=bookDTO.getAddress();
        this.city=bookDTO.getCity();
        this.state=bookDTO.getState();
        this.zipCode=bookDTO.getZipCode();
    }

}
