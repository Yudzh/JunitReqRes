package rest.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Unknown {
    private int id;
    private String name;
    private int year;
    private String color;
    @JsonProperty("pantone_value")
    private String pantoneValue;
}
