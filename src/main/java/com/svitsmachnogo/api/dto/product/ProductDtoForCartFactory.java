package com.svitsmachnogo.api.dto.product;

import com.svitsmachnogo.api.domain.entity.packaging.Packaging;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.dto.DtoFactory;
import com.svitsmachnogo.api.dto.packaging.PackagingDtoFactory;
import com.svitsmachnogo.api.dto.picture.PictureDtoFactory;
import lombok.RequiredArgsConstructor;
/**
 * Factory class responsible for creating instances of {@link ProductDtoForCart} from {@link Product}.
 * It utilizes {@link Packaging} information to create {@link com.svitsmachnogo.api.dto.packaging}.
 * This packaging is transferred in the factory constructor because we need only one specific packaging for the cart
 * (The {@link Product} contains more than one).
 * This factory is used for creating DTOs specific to the cart.
 */
@RequiredArgsConstructor
public class ProductDtoForCartFactory extends ProductDtoForCart implements DtoFactory<Product> {

    private final Packaging targetPackaging; // The packaging information used for creating PackagingDto.

    /**
     * Creates a {@link ProductDtoForCart} instance from the provided {@link Product}.
     *
     * @param targetEntity The target {@link Product} entity.
     * @return The created {@link ProductDtoForCart} instance.
     */
    @Override
    public ProductDtoForCart of(Product targetEntity) {
        ProductDtoForCart productDto = new ProductDtoForCart();
        productDto.setId(targetEntity.getId());
        productDto.setArticle(targetEntity.getArticle());
        productDto.setName(targetEntity.getName());
        productDto.setExist(targetEntity.isExist());
        productDto.setMainPicture(new PictureDtoFactory().of(targetEntity.getPictures().get(0)));
        productDto.setUnit(targetEntity.getUnit());
        productDto.setPackagingDto(new PackagingDtoFactory().of(targetPackaging));
        return productDto;
    }
}
