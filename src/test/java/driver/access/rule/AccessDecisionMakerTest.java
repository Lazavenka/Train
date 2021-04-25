package driver.access.rule;

import data.PersonSamples;
import domain.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static data.PersonSamples.anyValidDriver;
import static data.PersonSamples.anyValidPerson;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AccessDecisionMakerTest {
    Person person;
    private AccessDecisionMaker accessDecisionMaker;


    @Test
    void hasAccess_allRules() {
        accessDecisionMaker = new AccessDecisionMaker(getDenyRules());

        person = anyValidDriver();

        final boolean hasAccess = accessDecisionMaker.hasAccess(person);

        assertThat(hasAccess, is(true));
    }

    @Test
    void hasAccess_AgeRuleOnly() {
        accessDecisionMaker = new AccessDecisionMaker(getAgeDenyRule());

        person = anyValidPerson(12);

        boolean hasAccess = accessDecisionMaker.hasAccess(person);

        assertThat(hasAccess, is(false));

        person = anyValidPerson(22);

        hasAccess = accessDecisionMaker.hasAccess(person);

        assertThat(hasAccess, is(true));
    }

    @Test
    void hasAccess_LicenseRuleOnly() {
        accessDecisionMaker = new AccessDecisionMaker(getLicenseDenyRule());

        person = anyValidPerson(12);

        boolean hasAccess = accessDecisionMaker.hasAccess(person);

        assertThat(hasAccess, is(false));

        person.setDriverLicense(true);

        hasAccess = accessDecisionMaker.hasAccess(person);

        assertThat(hasAccess, is(true));
    }

    private List<DriverDenyRule> getDenyRules() {
        AgeDenyRule ageDenyRule = new AgeDenyRule();
        LicenseDenyRule licenseDenyRule = new LicenseDenyRule();
        return List.of(ageDenyRule, licenseDenyRule);
    }

    private List<DriverDenyRule> getAgeDenyRule() {
        AgeDenyRule ageDenyRule = new AgeDenyRule();
        return List.of(ageDenyRule);
    }

    private List<DriverDenyRule> getLicenseDenyRule() {
        LicenseDenyRule licenseDenyRule = new LicenseDenyRule();
        return List.of(licenseDenyRule);
    }
}