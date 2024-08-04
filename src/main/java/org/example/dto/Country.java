package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    private CountryName name;
    private Flags flags;
    public  CountryName getCountryName() {
        return name;
    }

    public void setCountryName(CountryName countryName) {
        this.name = countryName;
    }

    public CountryName getName() {
        return name;
    }

    public void setName(CountryName name) {
        this.name = name;
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name=" + name +
                ", flags=" + flags +
                '}';
    }
}
