package com.backoffice.managers;

import com.backoffice.config.ConfigReader;
import com.backoffice.config.IConfigReader;

import java.util.Objects;

/**
 * @author Neha Kharbanda
 */
public class SharedFileManager implements ISharedFileManager {

    public static IConfigReader config;

    public IConfigReader getConfigReader() {
        return Objects.isNull(config) ? new ConfigReader() : config;
    }

}
