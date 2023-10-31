# springboot-3rd-pary-intergration
This Spring Boot application serves as a server that doubles as a client, sending requests to third-party APIs to fetch and display weather and location details.

<h2>Calling Third-Party APIs:</h2> The app sends requests to two separate third-party APIs: one for weather information and another for location details. This is done using the HttpClient, HttpRequest, and HttpResponse classes, which are part of the Spring framework and Java's standard library. These classes provide the foundation for making HTTP requests and handling responses, allowing our application to communicate effectively with the external APIs.

<h2>Data Parsing:</h2> The data returned by the APIs is typically in JSON format. To make it more usable within our application, we parse this JSON data using the ObjectMapper class. This class is a versatile tool for converting JSON data into specific Java classes or mappers. These classes represent the structure of the data, making it easier to work with.

<h2>Error Handling:</h2> We all know that not everything goes perfectly all the time. To ensure a robust application, I've included error handling mechanisms to gracefully deal with any issues that may arise during the API requests or data processing.
