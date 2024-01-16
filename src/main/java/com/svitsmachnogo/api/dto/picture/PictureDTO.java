package com.svitsmachnogo.api.dto.picture;

import com.svitsmachnogo.api.dto.AbstractDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.net.URL;

/**
 * An object that stores information about the picture of the product for further transportation.
 */
@Data
@Schema(description = "An object that stores information about the picture of the product for further transportation")
public class PictureDTO implements AbstractDto {

    @Schema(description = "Picture identifier")
    private Integer id;

    @Schema(description = "Contain a link to a picture of the product")
    private URL urlPath;

}
