package com.example.camundadmndemo;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@Slf4j
public class DMNService {

    private final DmnEngine dmnEngine;

    public DMNService(DmnEngine dmnEngine) {
        this.dmnEngine = dmnEngine;
    }

    public DmnDecision getDmnDecision(String dmnKey) {
        InputStream dmn = DMNService.class.getResourceAsStream("/" + dmnKey + ".dmn");
        return dmnEngine.parseDecision(dmnKey, dmn);

    }


    public boolean checkEligibilityForOperation(String customerType, String customerSegment, String accountClass, String accountProduct) {
        DmnDecision decision = getDmnDecision("B2BIN");
        boolean checkEligibility = false;
        VariableMap inputParameters = Variables.createVariables()

                .putValue(EligibilityConfig.CUSTOMER_TYPE_DMN_INPUT, customerType)
                .putValue(EligibilityConfig.CUSTOMER_SEGMENT_DMN_INPUT, customerSegment)
                .putValue(EligibilityConfig.ACCOUNT_CLASS_DMN_INPUT, accountClass)
                .putValue(EligibilityConfig.PRODUCT_DMN_INPUT, accountProduct);
        DmnDecisionResult result = dmnEngine.evaluateDecision(decision, inputParameters);
        if (result.getSingleResult() != null && (boolean) result.getSingleResult().get(EligibilityConfig.IS_ACCOUNT_ELIGIBLE_DMN_OUTPUT)) {
            checkEligibility = true;
        }
        return checkEligibility;
    }

}
