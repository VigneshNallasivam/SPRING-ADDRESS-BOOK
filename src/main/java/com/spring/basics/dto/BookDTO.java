package com.spring.basics.dto;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@NoArgsConstructor
public class BookDTO
{
    @Pattern(regexp = "^[A-Z]{1}[a-z]{3,}\\s{0,}[A-z]{1}[a-z]{2,}$",message = "Employee name can have First name and Last name and both should start with caps!")
    private String name;
    @Pattern(regexp = "^[1-9]{2}\\s{0,1}[0-9]{10}$",message = "Phone number should have 2 digit country code and 10 digit number!")
    private String phoneNumber;
    private String email;
    private List<String> address;
    private List<String> city;
    private List<String> state;
    private List<String> zipCode;
}
