package com.svitsmachnogo.api.dto.picture;

import com.svitsmachnogo.api.domain.entity.Picture;
import com.svitsmachnogo.api.dto.DtoFactory;

/**
 * A factory class responsible for converting Picture entities into PictureDTO instances.
 */
public class PictureDtoFactory extends PictureDTO implements DtoFactory<Picture> {

    /**
     * Converts a Picture entity into a PictureDTO instance.
     *
     * @param targetEntity The Picture entity to be converted.
     * @return The converted PictureDTO instance.
     */
    @Override
    public PictureDTO of(Picture targetEntity) {
        PictureDTO pictureDTO = new PictureDTO();
        pictureDTO.setId(targetEntity.getId());
        pictureDTO.setUrlPath(targetEntity.getUrlPath());
        return pictureDTO;
    }
}

