------------------------------------------------------------------------------------------------

How to start the service in local:

1. Please clone the repository as provided.
2. Navigate to directory in the Console where the project was cloned.
3. Run -> cd build/libs/
4. Run -> java -jar ecom-service-0.0.1-SNAPSHOT.jar
5. This will start the process and tomcat will listen on port 8080. Please make sure this port is available. 
6. Please refer below sample API endpoints and their Request/response details to start hitting the Momemtum application.
7. Few Customers and Products data are already loaded in the database when process is started. We can add more customer/product records during runtime using given endpoints below.
8. We can also manage Momentum database console at this endppoint - http://localhost:8080/momentumdb-console/
9. Please follow this endpoint for Metrics - http://localhost:8080/actuator/prometheus
10. Standard logging is used in the application. We can also add timing info in logging as well as for metrics data.
11. Error handling is added to handle several use-cases.
12. Several Jupiter tests cases have been added to cover all the use-cases. Additional Unit and Integration tests can be added for more effective automation testing.

------------------------------------------------------------------------------------------------
API #1:
orderApi/v1/purchase/
POST - Valid purchase use-case where valid customer has enough Active day points to purchase selected products.
Payload:
{
	"custId" : 7,
	"productCodes" : [101, 201, 502, 401]
}
Response: HTTP 200 
Purchase is vald. The customer with 500 points have enough points to buy product(s) worth 255 points.


------------------------------------------------------------------------------------------------
API #2:
orderApi/v1/purchase/
POST - Another purchase use-case where valid customer does not have enough Active day points to purchase selected products. API throws Business Exception in this scenario with 500 as HTTP response code.
Payload:
{
	"custId" : 1,
	"productCodes" : [101, 201, 502, 401]
}
Response: HTTP 500  - Business Exception
{
    "status": "INTERNAL_SERVER_ERROR",
    "timestamp": "01-03-2020 08:50:53",
    "message": "The customer with 85 points does not have enough points to buy product(s) worth 255 points.",
    "debugMessage": null,
    "throwable": null,
    "detailedErrors": null
}
------------------------------------------------------------------------------------------------
API #3:
orderApi/v1/purchase/
POST - Use-case where either Customer Id and/or at least one Product code is not valid.
Payload:
{
	"custId" : 1,
	"productCodes" : []
}
Response: HTTP 400 - Bad Reequest
{{
    "status": "BAD_REQUEST",
    "timestamp": "01-03-2020 08:51:56",
    "message": "Validation Error",
    "debugMessage": null,
    "throwable": null,
    "detailedErrors": [
        {
            "object": "PurchaseInfo",
            "field": "productCodes",
            "rejectedValue": [],
            "message": "Please select at least one product to purchase"
        }
    ]
}
------------------------------------------------------------------------------------------------
API #4:
/productApi/v1/products/
GET - Fetch all products from the database
Response: HTTP 200
[
    {
        "code": 101,
        "name": "Product-A",
        "pointsCost": 20
    },
    {
        "code": 102,
        "name": "Product-B",
        "pointsCost": 45
    }
]

------------------------------------------------------------------------------------------------
API #5:
/productApi/v1/products/
POST- Add a new product in the database
{
        "name": "Product-H",
        "pointsCost": 1000
}
Response: HTTP 201 - Returns newly added resource with unique product code.

------------------------------------------------------------------------------------------------
API #6:
customerApi/v1/customers/
GET - Getch all customers from the database
Response: HTTP 200
[
    {
        "id": 1,
        "name": "James",
        "adPoints": 85
    },
    {
        "id": 2,
        "name": "Laura",
        "adPoints": 20
    }
]
------------------------------------------------------------------------------------------------
API #7:
customerApi/v1/customers/
POST- Add a new customer in the database
 {
        "name": "John",
        "adPoints": 1000
 }
Response: HTTP 201 - Returns newly added resource with unique customer id.

