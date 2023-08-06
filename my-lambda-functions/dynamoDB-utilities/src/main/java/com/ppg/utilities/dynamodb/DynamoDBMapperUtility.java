package com.ppg.utilities.dynamodb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.regions.RegionUtils;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;

public class DynamoDBMapperUtility {
	
	private static final String DB_REGION = "DB_REGION";
	private static final String DDB_PREFIX = "DDB_PREFIX";

	public static final Logger LOG = LogManager.getLogger(DynamoDBMapperUtility.class);
	
	
	static DynamoDBMapper ddbMapper = null;
    static String prefix = System.getenv(DDB_PREFIX) + "";
 
    public DynamoDBMapperUtility() {

    	if(null == prefix || prefix.isBlank() || prefix.isEmpty())
    		LOG.warn("WARNING!! ... DynamoDB Mapper is being used with-out table prefix. This could result in unexpected behaviour.");
    	
    }
    
    public static DynamoDBMapper getInstance() {
    	
    	if(null == prefix || prefix.isBlank() || prefix.isEmpty())
    		LOG.warn("WARNING!! ... DynamoDB Mapper is being used with-out table prefix. This could result in unexpected behaviour.");
    	
    	if(null == ddbMapper) {
            AmazonDynamoDB client = new AmazonDynamoDBClient().withRegion(RegionUtils.getRegion(System.getenv(DB_REGION)));
            var mapperConfig = new DynamoDBMapperConfig.Builder()
                    .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNamePrefix(prefix + "-"))
                    .build();
            ddbMapper = new DynamoDBMapper(client, mapperConfig);
    	}
        return ddbMapper;
    }
}
