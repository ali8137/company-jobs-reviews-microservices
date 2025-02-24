package com.ali.companyms.company.dto;

//    this DTO merges Review data fields and companyId data field. this class represents message structure in queue "companyRatingQueue" in RabbitMQ, this queue is defined in RabbitMQConfig class
public class ReviewMessage
{

    private Long id;
    private String title;
    private String description;
    private Double rating;
    private Long companyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
