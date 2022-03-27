package rest.pojos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import rest.utils.DateDeserilizer;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse extends UserRequest {
    private int id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss.SSSZ") //2022-03-12T09:33:47.176Z
    @JsonDeserialize(using = DateDeserilizer.class)
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss.SSSZ") //2022-03-12T09:33:47.176Z
    @JsonDeserialize(using = DateDeserilizer.class)
    private LocalDateTime updatedAt;
}
