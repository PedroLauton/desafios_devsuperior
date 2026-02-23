package com.example.desafio.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ClientRequestDTO {

    @Size(min = 1, max = 30, message = "O nome deve conter entre 1 e 30 caracteres.")
    @NotBlank(message = "O nome é obrigatório.")
    private String name;

    @NotBlank(message = "O CPF é obrigatório.")
    private String cpf;

    @NotNull(message = "A renda é obrigatória.")
    @PositiveOrZero(message = "A renda deve ser um valor positivo ou zero.")
    private Double income;

    @NotNull(message = "A data de nascimento é obrigatória")
    @PastOrPresent(message = "A data de nascimento deve ser no passado ou presente.")
    private LocalDate birthDate;

    @NotNull(message = "O número de filhos é obrigatório.")
    @PositiveOrZero(message = "O número de filhos deve ser um valor positivo ou zero.")
    private Integer children;

    public ClientRequestDTO() {
    }

    public ClientRequestDTO(String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }
}
