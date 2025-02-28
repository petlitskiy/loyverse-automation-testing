package com.backoffice.managers;

import com.backoffice.config.GsonGenerator;
import com.backoffice.core.model.Supplier;
import com.backoffice.provider.TestContextProvider;
import com.backoffice.utilities.Constants;
import com.backoffice.utilities.UniqueCredentialsGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.config.ConnectionConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.backoffice.utilities.Constants.*;

/**
 * @author Neha Kharbanda
 */
public class RestServiceManager implements IRestServiceManager {


    public static ISharedFileManager sharedFileManager;
    public static UniqueCredentialsGenerator uniqueCredentialsGenerator;
    private static Response response;
    private final Logger log = LogManager.getLogger(RestServiceManager.class);

    public RestServiceManager() {
        log.info("RestServiceManager constructor initialized.");
        sharedFileManager = new SharedFileManager();
        uniqueCredentialsGenerator = new UniqueCredentialsGenerator();
    }

    @Override
    public Map<String, String> cabinetRegistration(String registrationCountry) throws IOException, ParseException {

        ObjectMapper objMapper = new ObjectMapper();
        String key = null;
        String value = null;

        String emailID = uniqueCredentialsGenerator.generateUniqueEmail();
        String pwd = uniqueCredentialsGenerator.generateUniquePassword();
        log.info("Generated EmailID from CredentialsGenerator is: {}", emailID);
        log.info("Generated Password from CredentialsGenerator is: {}", pwd);
        JsonObject json = new JsonObject();
        Map<String, String> credsMap = new HashMap<>();
        RestAssured.baseURI = sharedFileManager.getConfigReader().getCabinetRegistrationEndpoint();
        String authUserName = sharedFileManager.getConfigReader().getAuthUserName();
        String authPassword = sharedFileManager.getConfigReader().getAuthPassword();

        RequestSpecification httpRequest = RestAssured.given().auth().basic(authUserName, authPassword).cookie("skipCaptcha", "true");
        httpRequest.header("Content-Type", "application/json");

        JSONParser jsonObj = new JSONParser();
        JSONObject jObject = (JSONObject) jsonObj.parse(new FileReader("src/test/resources/data/cabinet_registration_request.json"));
        Map<String, String> jMap = objMapper.readValue(jObject.toString(), new TypeReference<HashMap<String, String>>() {
        });

        for (Map.Entry<String, String> entry : jMap.entrySet()) {
            if (entry.getKey().equals("email") && entry.getValue().equals("generatedEmailID")) {
                key = entry.getKey();
                value = emailID;
            } else if (entry.getKey().equals("password") && entry.getValue().equals("generatedPwd")) {
                key = entry.getKey();
                value = pwd;
            } else if (entry.getKey().equals("country") && entry.getValue().equals("givenRegistrationCountry")) {
                key = entry.getKey();
                value = registrationCountry;
            } else {
                key = entry.getKey();
                value = entry.getValue();
            }
            json.addProperty(key, value);
        }

        httpRequest.body(json.toString());
        Response response = null;

        try {
            response = httpRequest.when().post(RestAssured.baseURI);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("An error occurred while submitting the request  " + RestAssured.baseURI);
        }

        handleCabinetRegistrationResponse(response, credsMap, emailID, pwd);
        //getProfile();
        RestAssured.config().connectionConfig(ConnectionConfig.connectionConfig().closeIdleConnectionsAfterEachResponse());
        return credsMap;

    }

    public void handleCabinetRegistrationResponse(Response response, Map<String, String> credsMap, String emailID, String pwd) {
        log.info("Status code received from CabinetRegistration API is: {}", response.getStatusCode());

        JsonPath json = response.getBody().jsonPath();

        if (response.getStatusCode() == Constants.OK) {
            credsMap.put("email", emailID);
            credsMap.put("password", pwd);

            String[] cookieHeaders = response.getHeaders().getValues("Set-Cookie").stream().filter(a -> a.contains("JSESSIONID")).collect(Collectors.joining()).split(";");


            TestContextProvider.getContext().getScenarioContext().setContext("ownerId", json.get("ownerId"));
            TestContextProvider.getContext().getScenarioContext().setContext("JSESSIONID", cookieHeaders[0]);
        } else if (response.getStatusCode() == Constants.BAD_REQUEST) {
            log.error("Bad Request {}: There is something wrong with API request.Please try again.", response.getStatusCode());
            throw new RuntimeException("Exception occurred as the CabinetRegistration returned {} bad request." + response.getStatusCode());
        } else if (response.getStatusCode() == Constants.NOT_FOUND) {
            log.error("Not Found {}: API Endpoint is missing", response.getStatusCode());
            throw new RuntimeException("Exception occurred as the CabinetRegistration returned {} Not Found." + response.getStatusCode());
        } else if (response.getStatusCode() == Constants.INTERNAL_SERVER_ERROR) {
            log.error("Internal Server Error {}:Something went wrong. Please try again later", response.getStatusCode());
            throw new RuntimeException("Exception occurred as the CabinetRegistration returned {} Internal Server Error." + response.getStatusCode());
        } else {
            log.error("The account registration was not successful as the API returned {}", response.getStatusCode());
        }
    }


    public void setTrailSubscription(String ownerId, String subscription_Type, String subscription_Status) throws FileNotFoundException {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneDayBefore = now.minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        ObjectMapper objMapper = new ObjectMapper();
        String key = null;
        Object value = null;


        Map<String, Object> jsonMap = new HashMap<>();
        Map<String, String> credsMap = new HashMap<>();

        RestAssured.baseURI = sharedFileManager.getConfigReader().getTrailSubscriptionURL();

        JSONParser jsonObj = new JSONParser();
        JSONObject jObject = (JSONObject) jsonObj.parse(new FileReader("src/test/resources/data/set_trail_subscription_request.json"));
        Map<String, Object> jMap = objMapper.readValue(jObject.toString(), new TypeReference<HashMap<String, Object>>() {
        });


        for (Map.Entry<String, Object> entry : jMap.entrySet()) {

            switch (entry.getKey()) {
                case Constants.OWNER_ID:
                    key = entry.getKey();
                    value = Integer.valueOf(ownerId);
                    break;
                case Constants.TRAIL_START:
                    key = entry.getKey();
                    value = oneDayBefore.format(formatter);
                    log.info("Trail Start generated datetime {}", value);
                    break;
                case Constants.TRIAL_END:
                    key = entry.getKey();
                    value = now.format(formatter);
                    log.info("Trail End generated datetime {}", value);
                    break;
                case Constants.SUBSCRIPTION_TYPE:
                    key = entry.getKey();
                    value = subscription_Type;
                    break;
                case Constants.SUBSCRIPTION_STATUS:
                    key = entry.getKey();
                    value = Boolean.valueOf(subscription_Status);
                    break;
                default:
                    key = entry.getKey();
                    value = entry.getValue();
                    break;
            }
            jsonMap.put(key, value);
        }
        org.json.JSONObject json = new org.json.JSONObject().put("hello", jsonMap);

        log.info("Json request for trial subscription API {}", json.toString());

        RequestSpecification httpRequest = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("X-Manager-Login", sharedFileManager.getConfigReader().getXManagerLogin());

        log.info("print json", json.get("hello"));
        httpRequest.body(json.get("hello").toString());

        Response response = null;

        try {
            response = httpRequest.when().post(RestAssured.baseURI);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("An error occurred while submitting the request " + RestAssured.baseURI);
        }

        RestAssured.config().connectionConfig(ConnectionConfig.connectionConfig().closeIdleConnectionsAfterEachResponse());
        log.info("Response from set trail subscription API {}", response.getStatusCode());
    }


    public String getProfile() {

        //JSESSIONID
        //RestAssured.baseURI = sharedFileManager.getConfigReader().getTrailSubscriptionURL();
        RestAssured.baseURI = "https://r.lv-dev.eu.loyverse.com/data/ownercab/getprofile";

        RequestSpecification httpRequest = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("X-Manager-Login", sharedFileManager.getConfigReader().getXManagerLogin())
                .header("Cookie", TestContextProvider.getContext().getScenarioContext().getContext("JSESSIONID"));

        httpRequest.body("{}");


        Response response = null;

        try {
            response = httpRequest.when().post(RestAssured.baseURI);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("An error occurred while submitting the request " + RestAssured.baseURI);
        }
        JsonPath json = response.getBody().jsonPath();


        log.info("Response from getProfile Endpoint >>>> {}", json.get("billingInfo.billingStatus").toString());
        return json.get("billingInfo.billingStatus").toString();
    }


    public String getStoreUUID(String accessToken) {

        // Set the base URI for the API
        RestAssured.baseURI = sharedFileManager.getConfigReader().getDevStoreAPIURL();

        // Prepare the HTTP request
        RequestSpecification httpRequest = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .header("Cookie", TestContextProvider.getContext().getScenarioContext().getContext("JSESSIONID"));
        // Sending a GET request
        Response response = null;
        try {
            response = httpRequest.when().get(RestAssured.baseURI);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("An error occurred while submitting the request to " + RestAssured.baseURI);
        }
        // Extracting the response as JSON
        JsonPath json = response.getBody().jsonPath();
        // Logging the response
        log.info("Response from getStoreUUID Endpoint >>>> {}", json.getString(STORE_UUID));

        // Returning the store ID
        return json.getString(STORE_UUID);
    }

    @Override
    public Supplier createSupplierByAPI(String accessToken, String request) {
        ObjectMapper objMapper = new ObjectMapper();
        RestAssured.baseURI = sharedFileManager.getConfigReader().getDevSupplierAPIURL();

        RequestSpecification httpRequest = RestAssured.given()
                .header(CONTENT_TYPE_HEADER, APPLICATION_JSON_HEADER)
                .header(AUTHORIZATION_HEADER, "Bearer " + accessToken)
                .header(COOKIE_HEADER, TestContextProvider.getContext().getScenarioContext().getContext(JSESSION_ID_HEADER));

        Response response = null;
        try {
            response = httpRequest.body(request).when().post(RestAssured.baseURI);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("An error occurred while submitting the request to " + RestAssured.baseURI);
        }
        JsonPath json = response.getBody().jsonPath();
        log.info("Response & status from createSupplierByAPI Endpoint >>>> {} , ", response.getStatusCode(), json.prettyPrint());
        return GsonGenerator.toGson().fromJson(json.prettyPrint(), Supplier.class);
    }


    public String getSupplierID(String accessToken) {

        // Set the base URI for the API
        RestAssured.baseURI = sharedFileManager.getConfigReader().getDevSupplierAPIURL();

        // Prepare the HTTP request
        RequestSpecification httpRequest = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .header("Cookie", TestContextProvider.getContext().getScenarioContext().getContext("JSESSIONID"));
        // Sending a GET request
        Response response = null;
        try {
            response = httpRequest.when().get(RestAssured.baseURI);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("An error occurred while submitting the request to " + RestAssured.baseURI);
        }
        // Extracting the response as JSON
        JsonPath json = response.getBody().jsonPath();
        // Logging the response
        log.info("Response from getSupplierID Endpoint >>>> {}", json.getString(SUPPLIER_ID));

        // Returning the store ID
        return json.getString(SUPPLIER_ID);
    }


    @Override
    public void createItemByAPI(String accessToken, String request) throws FileNotFoundException {
        ObjectMapper objMapper = new ObjectMapper();
        RestAssured.baseURI = sharedFileManager.getConfigReader().getCreateWareAPIURL();

        RequestSpecification httpRequest = RestAssured.given()
                .header(CONTENT_TYPE_HEADER, APPLICATION_JSON_HEADER)
                .header(AUTHORIZATION_HEADER, "Bearer " + accessToken)
                .header(COOKIE_HEADER, TestContextProvider.getContext().getScenarioContext().getContext(JSESSION_ID_HEADER));

        Response response = null;
        try {
            response = httpRequest.body(request).when().post(RestAssured.baseURI);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("An error occurred while submitting the request to " + RestAssured.baseURI);
        }
        JsonPath json = response.getBody().jsonPath();
        log.info("Response & status from createItemByAPI Endpoint >>>> {} , ", response.getStatusCode(), json.prettyPrint());
    }
}



