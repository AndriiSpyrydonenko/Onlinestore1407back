package com.svitsmachnogo.api.dto;

import com.svitsmachnogo.api.domain.entity.Picture;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PictureDTO {

    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public URL getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(URL urlPath) {
        this.urlPath = urlPath;
    }
}
