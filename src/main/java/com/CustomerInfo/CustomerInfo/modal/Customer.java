package com.CustomerInfo.CustomerInfo.modal;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "City is mandatory")
    private String city;

    @NotNull(message = "Balance is mandatory")
    @Min(value = 0, message = "Balance cannot be negative")
    @Digits(integer = 10, fraction = 2, message = "Balance can have max 10 integer digits and 2 decimal places")
    private BigDecimal balance;

    @NotNull(message = "Age is mandatory")
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 120, message = "Age cannot exceed 120")
    private Integer age;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$",
            message = "Password must contain at least one digit and one letter")
    private String password;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Gender is mandatory")
    @Pattern(regexp = "^(Male|Female)$", message = "Gender must be Male, Female")
    private String gender;

    @NotNull(message = "Credit card is mandatory")
    @Digits(integer = 16, fraction = 0, message = "Credit card must be exactly 16 digits")
    private String creditCard;

    @NotNull
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @NotNull
    @Future(message = "Membership expiry must be in the future")
    private LocalDate membershipExpiry;


    @NotNull
    @AssertTrue(message = "Must agree to terms and conditions")
    private Boolean agreedToTerms;

    @NotNull
    @AssertFalse(message = "Account cannot be suspended")
    private Boolean accountSuspended;

    @NotNull
    @Digits(integer = 5, fraction = 3, message = "Discount rate can have max 5 integer and 3 fraction digits")
    private BigDecimal discountRate;

    @NotEmpty(message = "Members list cannot be empty")
    @Singular
    private List<String> members;
}