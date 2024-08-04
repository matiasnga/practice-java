package org.example.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Flags {
    private String alt;

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    @Override
    public String toString() {
        return "Flags{" +
                "alt='" + alt + '\'' +
                '}';
    }
}
