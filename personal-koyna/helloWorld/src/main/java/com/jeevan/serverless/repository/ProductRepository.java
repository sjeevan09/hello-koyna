package com.jeevan.serverless.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductRepository {

    private static final Logger LOG = LogManager.getLogger(ProductRepository.class);
    private static final String DB_TABLE_NAME = "DB_TABLE_NAME";
    private static final String DB_GSI_NAME = "DB_GSI_NAME";

    static String tableName = System.getenv(DB_TABLE_NAME);

    public void createItems(com.jeevan.serverless.learning.dto.Product dto, DynamoDBMapper ddbMapper) {
        ddbMapper.save(dto);
        LOG.info("Transaction Successfully written into database with version - {}", dto);
    }

}
