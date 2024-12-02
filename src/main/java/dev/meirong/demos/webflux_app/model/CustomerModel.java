package dev.meirong.demos.webflux_app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class CustomerModel {
    @Id
    Long id;

    String companyName;

    String companyEmail;

    String taxId;
}
