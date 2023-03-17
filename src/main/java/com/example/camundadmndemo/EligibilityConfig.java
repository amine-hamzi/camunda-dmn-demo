package com.example.camundadmndemo;

import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EligibilityConfig {

    @Value("${ unibank.services.eligibilities.entities.have.opposition.bug:MG}")
    private String entityHaveOppositionBug;


    public static final String CUSTOMER_TYPE_DMN_INPUT = "customerType";
    public static final String CUSTOMER_SEGMENT_DMN_INPUT = "customerSegment";
    public static final String ACCOUNT_CLASS_DMN_INPUT = "accountClass";
    public static final String PRODUCT_DMN_INPUT = "productCode";
    public static final String OPERATION_IDENTIFIER_DMN_INPUT = "OperationIdentifier";
    public static final String OPPOSITION_CODE_DMN_INPUT = "OppositionCode";
    public static final String IS_ACCOUNT_ELIGIBLE_DMN_OUTPUT = "isAccountEligible";
    public static final String OPERATION_OPPOSED_DMN_OUTPUT = "OperationOpposed";

    @Bean
    DmnEngine createDmnEngine(){
        return DmnEngineConfiguration.createDefaultDmnEngineConfiguration().buildEngine();
    }


}
