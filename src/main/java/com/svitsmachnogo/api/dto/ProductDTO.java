package com.svitsmachnogo.api.dto;

import com.svitsmachnogo.api.domain.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Schema(description = "An object that stores information about the product for further transportation")
public class ProductDTO {

    @Schema(description = "Product identifier")
    private int id;

    private CategoryDTO category;

    @Schema(description = "Contains the category id in string form. For example: nuts, oil")
    private String categoryId;

    private int article;

    @NotBlank
    private String name;

    private String description;

    @Schema(description = "Contains information about the country of manufacture")
    private String countryProducer;

    @Schema(description = "Displays the status of product availability." +
            " If it is available, it is true, otherwise it is false")
    private boolean exist = true;

    @Schema(description = "Indicates the priority of displaying the product on the main page," +
            " where 1 is the highest priority and 15 is the lowest")
    @Min(0)
    @Max(15)
    private int priorityScore = 0;

    private double rating;

    private int reviewCount;

    @Schema(description = "Contains the percentage of the product discount")
    @Min(0)
    @Max(100)
    private int discountPercent = 0;

    private Timestamp create_date;

    @Schema(description = "Contains a link to the main image of the product." +
            " The picture is considered the main one if it is on the first place in the list of all pictures")
    private PictureDTO mainPicture;

    @Schema(description = "Contains a list that stores links to all pictures")
    private List<PictureDTO> pictures;

    @Schema(description = "Contains a price map where product quantity is the key and price is the value")
    private Map<String, Integer> packaging;


    private ProductDTO() {
    }

    public static ProductDTO createSimpleCard(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.id = product.getId();
        dto.name = product.getName();
        dto.categoryId = new CategoryDTO(product.getCategory()).getId();
        dto.exist = product.isExist();
        dto.rating = product.getRating();
        dto.reviewCount = product.getReviewCount();
        dto.packaging = product.getPackaging();
        dto.mainPicture = PictureDTO.getList(product.getPictures()).get(0);
        return dto;
    }

    public static ProductDTO createForDiscountBlock(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.id = product.getId();
        dto.discountPercent = product.getDiscountPercent();
        dto.mainPicture = PictureDTO.getList(product.getPictures()).get(0);
        return dto;
    }

    public static List<ProductDTO> getList(List<Product> products) {
        List<ProductDTO> dtos = new ArrayList<>();

        for (Product product : products) {
            ProductDTO dto = ProductDTO.createSimpleCard(product);
            dtos.add(dto);
        }
        return dtos;
    }

    public static ProductDTO createForQuickView(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.id = product.getId();
        dto.category = new CategoryDTO(product.getCategory());
        dto.article = product.getArticle();
        dto.name = product.getName();
        dto.description = product.getDescription();
        dto.countryProducer = product.getCountryProducer();
        dto.rating = product.getRating();
        dto.reviewCount = product.getReviewCount();
        dto.pictures = PictureDTO.getList(product.getPictures());
        dto.packaging = product.getPackaging();

        return dto;
    }

    public static List<ProductDTO> getListForDiscountBlock(List<Product> products) {
        List<ProductDTO> dtos = new ArrayList<>();

        for (Product product : products) {
            ProductDTO dto = ProductDTO.createForDiscountBlock(product);
            dtos.add(dto);
        }
        return dtos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountryProducer() {
        return countryProducer;
    }

    public void setCountryProducer(String countryProducer) {
        this.countryProducer = countryProducer;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public int getPriorityScore() {
        return priorityScore;
    }

    public void setPriorityScore(int priorityScore) {
        this.priorityScore = priorityScore;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public PictureDTO getMainPicture() {
        return mainPicture;
    }

    public void setMainPicture(PictureDTO mainPicture) {
        this.mainPicture = mainPicture;
    }

    public List<PictureDTO> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureDTO> pictures) {
        this.pictures = pictures;
    }

    public Map<String, Integer> getPackaging() {
        return packaging;
    }

    public void setPackaging(Map<String, Integer> packaging) {
        this.packaging = packaging;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}