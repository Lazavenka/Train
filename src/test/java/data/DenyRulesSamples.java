package data;

import driver.access.rule.AgeDenyRule;
import driver.access.rule.DriverDenyRule;
import driver.access.rule.LicenseDenyRule;

import java.util.List;

public class DenyRulesSamples {
    public static List<DriverDenyRule> getDenyRules() {
        AgeDenyRule ageDenyRule = new AgeDenyRule();
        LicenseDenyRule licenseDenyRule = new LicenseDenyRule();
        return List.of(ageDenyRule, licenseDenyRule);
    }

    public static List<DriverDenyRule> getAgeDenyRule() {
        AgeDenyRule ageDenyRule = new AgeDenyRule();
        return List.of(ageDenyRule);
    }

    public static List<DriverDenyRule> getLicenseDenyRule() {
        LicenseDenyRule licenseDenyRule = new LicenseDenyRule();
        return List.of(licenseDenyRule);
    }
}
