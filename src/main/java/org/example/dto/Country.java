package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    private CountryName name;
    @JsonProperty("capital")
    private List<String> capitalCities;
    private String flag;
    private Flags flags;
    private CapitalInfo capitalInfo;
    private Translations translations;

    public String getSpanishName() {
        return this.translations.getSpa().getCommon();
    }

    public String getName() {
        return this.name.getCommon();
    }

    public String getFlagDescription() {
        String commonName = this.getName();
        String[] words = getSpanishName().split(" ");
        StringBuilder replacement = new StringBuilder();

        for (String word : words) {
            if (replacement.length() > 0) {
                replacement.append(" ");
            }
            replacement.append("*".repeat(word.length()));
        }

        return this.flags.getAlt().replace(commonName, replacement.toString());
    }

}
