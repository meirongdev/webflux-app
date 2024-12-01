package dev.meirong.demos.webflux_app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
@Table(name = "customers")
public class CustomerModel {
    @Id
    Long id;

    String companyName;

    String companyEmail;

    String taxId;

    @JsonCreator
    public CustomerModel(@JsonProperty Long id, @JsonProperty String companyName,
            @JsonProperty String companyEmail, @JsonProperty String taxId) {
        this.id = id;
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.taxId = taxId;
    }
}
