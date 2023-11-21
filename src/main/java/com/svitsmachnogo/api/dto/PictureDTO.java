package com.svitsmachnogo.api.dto;

import com.svitsmachnogo.api.domain.entity.Picture;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
@Data
@Schema(description = "An object that stores information about the picture of the product for further transportation")
public class PictureDTO {

    @Schema(description = "Picture identifier")
    private Integer id;

    @Schema(description = "Contain a link to a picture of the product")
    private URL urlPath;

    private PictureDTO() {
    }

    private PictureDTO(Picture picture) {
        this.id = picture.getId();
        this.urlPath = picture.getUrlPath();
    }

    public static PictureDTO of(Picture picture){
        return new PictureDTO(picture);
    }

    public static List<PictureDTO> getList(List<Picture> pictures) {
        List<PictureDTO> pictureDTOList = new ArrayList<>();

        pictures.forEach(picture -> pictureDTOList.add(of(picture)));

        return pictureDTOList;
    }
}
