package com.assignment.bend;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlipkartApi
{
private static final String BASE_URL = "https://affiliate.flipkart.com";
    @Test
    public void testProductSearch() {
        // Define the endpoint for searching products
        String endpoint = "/af_prod_ref.html#get-1-0-search-format";

        // Define the query parameter (example: searching for "Titan watch")
        String query = "Puma Shoes";

        // Perform a GET request
        Response response = RestAssured
                .given()
                    .queryParam("query", "Puma Shoes") // Set the query parameter
                .when()
                    .get("https://affiliate.flipkart.com/api-docs/af_prod_ref.html#get-1-0-search-format") // Execute the GET request
                .then()
                    .statusCode(200) // Validate the status code
                    .extract().response(); // Extract the response

        // Print the response body for debugging
        System.out.println("Response Body: " + response.asString());

        // Example of extracting specific values (adapt according to actual response structure)
        String productName = response.jsonPath().getString("products[0].name");
        double productPrice = response.jsonPath().getDouble("products[0].price");

        // Validate the extracted values (you would replace these with expected values)
        Assert.assertNotNull(productName, "Product name should not be null");
        Assert.assertTrue(productPrice > 0, "Product price should be greater than 0");
    }
}
