package pl.dziedziul.myapp;

import static com.tngtech.archunit.lang.conditions.ArchConditions.callMethod;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import org.junit.Test;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.conditions.ArchConditions;

public class NoThreadSleepTest {

    private final ArchCondition<JavaClass> notPutThreadToSleep = ArchConditions.not(callMethod(Thread.class, "sleep", long.class));

    @Test
    public void shouldNotUseSleep() {
        //given
        JavaClasses testedClasses = new ClassFileImporter().importPackagesOf(SampleClass.class);
        //then
        classes().should(notPutThreadToSleep).check(testedClasses);

    }
}
