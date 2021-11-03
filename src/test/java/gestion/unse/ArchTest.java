package gestion.unse;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("gestion.unse");

        noClasses()
            .that()
                .resideInAnyPackage("gestion.unse.service..")
            .or()
                .resideInAnyPackage("gestion.unse.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..gestion.unse.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
