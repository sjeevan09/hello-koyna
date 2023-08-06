package com.jeevan.serverless.learning.dto;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@DynamoDBTable(tableName = "Product")
public class Product {

    @JsonProperty("uuid")
    @DynamoDBHashKey(attributeName = "id")
    String id;

    @JsonProperty("name")
    @DynamoDBAttribute(attributeName = "name")
    String name;

    @JsonProperty("description")
    @DynamoDBAttribute(attributeName = "description")
    String description;

    @JsonProperty("price")
    @DynamoDBAttribute(attributeName = "price")
    float price;
}