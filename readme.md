To use Allure, you only need to complete two steps:

1. Gather information about tests
    1. For this, there are adapters for different test frameworks like JUnit, Cucumber-JVM etc.
2. Generate a report
    1. Few ways to generate the report:
        1. Allure command line
        2. Team City
        3. Jenkins etc.
This is the easiest set up:

To generate the Allure compatible XML (Which is generated per test class):

Add the following plugins in your POM xml:
<plugin>
    <groupId>com.github.temyers</groupId>
    <artifactId>cucumber-jvm-parallel-plugin</artifactId>
    <version>2.2.0</version>
    <executions>
        <execution>
            <id>generateRunners</id>
            <phase>generate-test-sources</phase>
            <goals>
                <goal>generateRunners</goal>
            </goals>
            <configuration>
                <!-- Mandatory -->
                <!-- comma separated list of package names to scan for glue code -->
                <glue>com.amitsh.allure</glue>
                <!-- These are optional, with the default values -->
                <!-- Where to output the generated tests -->
                <outputDirectory>${project.build.directory}/generated-test-sources/cucumber</outputDirectory>
                <!-- The directory, which must be in the root of the runtime classpath, containing your feature files.  -->
                <featuresDirectory>src/test/resources/features/</featuresDirectory>
                <!-- Directory where the cucumber report files shall be written  -->
                <cucumberOutputDir>target/cucumber-parallel</cucumberOutputDir>
                <!-- comma separated list of output formats -->
                <format>json</format>
                <!-- CucumberOptions.strict property -->
                <strict>true</strict>
                <!-- CucumberOptions.monochrome property -->
                <monochrome>true</monochrome>
                <!-- The tags to run, maps to CucumberOptions.tags property -->
                <tags>"@amit"</tags>
                <!-- If set to true, only feature files containing the required tags shall be generated. -->
                <filterFeaturesByTags>false</filterFeaturesByTags>
                <!-- Generate TestNG runners instead of JUnit ones. -->
                <useTestNG>false</useTestNG>
                <!-- The naming scheme to use for the generated test classes.  One of 'simple' or 'feature-title' -->
                <namingScheme>simple</namingScheme>
                <!-- The class naming pattern to use.  Only required/used if naming scheme is 'pattern'.-->
                <namingPattern>Parallel{c}IT</namingPattern>
                <!-- One of [SCENARIO, FEATURE]. SCENARIO generates one runner per scenario.  FEATURE generates a runner per feature. -->
                <parallelScheme>SCENARIO</parallelScheme>
                <cucumberOptions>--plugin ru.yandex.qatools.allure.cucumberjvm.AllureReporter</cucumberOptions>
            </configuration>
        </execution>
    </executions>
</plugin>

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-failsafe-plugin</artifactId>
    <version>2.19.1</version>
    <executions>
        <execution>
            <phase>integration-test</phase>
            <goals>
                <goal>integration-test</goal>
            </goals>
            <configuration>
                <forkCount>1</forkCount>
                <reuseForks>false</reuseForks>
                <includes>
                    <include>**/*IT.java</include>
                </includes>
            </configuration>
        </execution>
    </executions>
</plugin>

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.19.1</version>
    <configuration>
        <skipTests>false</skipTests>
        <properties>
            <property>
                <name>listener</name>
                <value>ru.yandex.qatools.allure.junit.AllureRunListener</value>
            </property>
        </properties>
    </configuration>
    <dependencies>
        <dependency>
            <groupId>org.apache.maven.surefire</groupId>
            <artifactId>surefire-junit4</artifactId>
            <version>2.19.1</version>
        </dependency>
    </dependencies>
</plugin>
Also, add these dependencies:
<!--Cucumber parallel plugin-->
<dependency>
    <groupId>com.github.temyers</groupId>
    <artifactId>cucumber-jvm-parallel-plugin</artifactId>
    <version>2.2.0</version>
</dependency>

<!--Allure dependencies-->
<dependency>
    <groupId>ru.yandex.qatools.allure</groupId>
    <artifactId>allure-cucumber-jvm-adaptor</artifactId>
    <version>1.6.1</version>
</dependency>

<dependency>
    <groupId>ru.yandex.qatools.allure</groupId>
    <artifactId>allure-junit-adaptor</artifactId>
    <version>1.5.0</version>
</dependency>
Obviously, you’ll need to add the other dependencies like cucumber-java etc.
With this set up, when you execute mvn test, unit tests will be executed and the xml report will get generated.
When you execute mvn verify, unit tests and cucumber tests will be executed and the xml reports will get generated for unit test and the cucumber scenarios.
it’s generated in target/allure-results
cd into the project dir, and execute this command: allure generate target/allure-results
A folder allure-report will be generated in the project dir.
Then execute allure report open to start the web server and launch the html report.
