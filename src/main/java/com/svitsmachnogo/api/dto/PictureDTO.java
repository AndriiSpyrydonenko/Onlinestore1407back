package com.svitsmachnogo.api.dto;

import com.svitsmachnogo.api.domain.entity.Picture;
import io.swagger.v3.oas.annotations.media.Schema;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Schema(description = "An object that stores information about the picture of the product for further transportation")
public class PictureDTO {

    @Schema(description = "Picture identifier")
    private Integer id;

    @Schema(description = "Contain a link to a picture of the product")
    private URL urlPath;

    private PictureDTO() {
    }

    public PictureDTO(Picture picture) {
        this.id = picture.getId();
        this.urlPath = picture.getUrlPath();
    }

    public static List<PictureDTO> getList(List<Picture> pictures) {
        List<PictureDTO> pictureDTOList = new ArrayList<>();
        for (Picture picture : pictures) {
            PictureDTO dto = new PictureDTO(picture);
            pictureDTOList.add(dto);
        }
        return pictureDTOList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public URL getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(URL urlPath) {
        this.urlPath = urlPath;
    }
}
