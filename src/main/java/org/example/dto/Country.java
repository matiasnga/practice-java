package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.function.Predicate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    private CountryName name;
    @JsonProperty("capital")
    private List<String> capitalCities;
    private String flag;
    private Flags flags;
    private CapitalInfo capitalInfo;
}
