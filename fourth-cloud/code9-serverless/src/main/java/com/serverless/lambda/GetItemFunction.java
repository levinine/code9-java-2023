package com.serverless.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.model.Item;
import com.serverless.utils.DependencyFactory;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.Collections;
import java.util.Map;

public class GetItemFunction implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private final DynamoDbEnhancedClient dynamoClient;
    private final String tableName;
    private final TableSchema<Item> itemTableSchema;

    public GetItemFunction() {
        dynamoClient = DependencyFactory.dynamoDbEnhancedClient();
        tableName = DependencyFactory.tableName();
        itemTableSchema = TableSchema.fromBean(Item.class);
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        String response = "";
        LambdaLogger logger = context.getLogger();
        DynamoDbTable<Item> itemsTable = dynamoClient.table(tableName, itemTableSchema);
        Map<String, String> pathParameters = input.getPathParameters();

        if (pathParameters != null) {
            String itemPartitionKey = pathParameters.get(Item.PARTITION_KEY);
            Item item = itemsTable.getItem(Key.builder().partitionValue(itemPartitionKey).build());
            if (item != null) {
                try {
                    response = new ObjectMapper().writeValueAsString(item);
                } catch (JsonProcessingException e) {
                    logger.log("Failed create a JSON response: " + e);
                }
            }
        }

        return new APIGatewayProxyResponseEvent().withStatusCode(200)
                .withIsBase64Encoded(Boolean.FALSE)
                .withHeaders(Collections.emptyMap())
                .withBody(response);
    }
}
