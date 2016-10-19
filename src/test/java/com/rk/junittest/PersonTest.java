package com.rk.junittest;

/**
 * Created by rajeendra on 10/19/16.
 *
 *
 */

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class PersonTest {


    @Test
    @Parameters({
            "17, false",
            "22, true" })
    public void personIsAdult(int age, boolean valid) throws Exception {
        assertThat(new Person(age).isAdult(), is(valid));
    }


    //This is ok if you want to have only a few values, but if you want to have more, you can use a method from the test class to give you the values:

    @Test
    @Parameters(method = "adultValues")
    public void xpersonIsAdult(int age, boolean valid) throws Exception {
        assertEquals(valid, new Person(age).isAdult());
    }

    private Object[] adultValues() {
        return new Object[]{
                new Object[]{13, false},
                new Object[]{17, false},
                new Object[]{18, true},
                new Object[]{22, true}
        };
    }

    //If your test method name is short, like ours here, you can skip the method attribute of the `@Parameters` annotation and name your params providing method just like the test method, but prefixed with parametersFor:

    @Test
    @Parameters
    public void personIsAdultY(int age, boolean valid) throws Exception {
        assertEquals(valid, new Person(age).isAdult());
    }

    private Object[] parametersForPersonIsAdultY() {
        return new Object[]{
                new Object[]{13, false},
                new Object[]{17, false},
                new Object[]{18, true},
                new Object[]{22, true}
        };
    }

    //Isn't giving primitives to a test a bit un-object-oriented? Sure - and there's the stupid constructor call in the test method. So let's skip it by passing the whole Person object to the test class:

    @Test
    @Parameters
    public void isAdult(Person person, boolean valid) throws Exception {
        assertThat(person.isAdult(), is(valid));
    }

    private Object[] parametersForIsAdult() {
        return new Object[]{
                new Object[]{new Person(13), false},
                new Object[]{new Person(17), false},
                new Object[]{new Person(18), true},
                new Object[]{new Person(22), true}
        };
    }


    //Sometimes you may want to externalise the params provider (to test-drive it, or because it's complex and clutters the test class, or because you already have it...). The common use would be when reading the test data/params from a file or a DB. There's obviously a way to do that too:

    @Test
    @Parameters(source = PersonProvider.class)
    public void personIsAdult(Person person, boolean valid) {
        assertThat(person.isAdult(), is(valid));
    }

}
