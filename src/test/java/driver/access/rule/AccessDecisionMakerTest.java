package driver.access.rule;

import domain.Person;
import org.junit.jupiter.api.Test;

import static data.DunyRulesSamples.*;
import static data.PersonSamples.anyValidDriver;
import static data.PersonSamples.anyValidPerson;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class AccessDecisionMakerTest {
    private Person person;
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

}