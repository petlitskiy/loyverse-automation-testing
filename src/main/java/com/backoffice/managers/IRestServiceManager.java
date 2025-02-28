package com.backoffice.managers;

import com.backoffice.core.model.Supplier;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * @author Neha Kharbanda
 */
public interface IRestServiceManager {

    Map<String, String> cabinetRegistration(String registrationCountry) throws IOException, ParseException;

    public String getStoreUUID(String accessToken);

    public void createItemByAPI(String accessToken, String request) throws FileNotFoundException;

    public Supplier createSupplierByAPI(String accessToken, String request);
}
