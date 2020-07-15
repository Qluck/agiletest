package agiletest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Media {
    private String id;
    @JsonProperty("cropped_picture")
    private String imageUrl;
}
