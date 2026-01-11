# Soap Project

This is going to be an example project using soap

## Overview

__Contract-First approach__ - define format of request and response before you start developing the web service. I guess
this is just the paradigm that Java soap projects use -\(-_-)/-

For example, we will define the request XML and response XML. We use XSD to do this :)

__Web Service Provider__ -> web service provider takes in SOAP XML Request and gives back a SOAP XML Response


### Example Soap Request:
```xml
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body xmlns:ns2="http://<path-to-xsd-that-defines-this-api">
        <ns2:SomeDefinedApiMethod>
            <ns2:somePreDefinedParamaterId>anIdValue</ns2:somePreDefinedParamaterId>
        </ns2:SomeDefinedApiMethod>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```

I guess Soap is supposed to reflect like sending messages. It wraps the whole entire message in something called a `Envelope`
Inside the `Envelope` we define a `Header` and a `Body`

The `Header` is optional. It usually defines security-related stuff for like authentication and authorization.

The `Body` is the meat and potatoes of the message. It usually contains the payload and all the shit that you care about :)

### Example Soap Response:

```xml
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body xmlns:ns2="http://<path-to-xsd-that-defines-this-api">
        <ns2:SomeDefinedApiMethodResponse>
            <ns2:DetailsYouRequested>
                <ns2:somePreDefinedParamaterId>anIdValue</ns2:somePreDefinedParamaterId>
                <ns2:someInfo>blah</ns2:someInfo>
                <ns2:someMoreInfo>blah2</ns2:someMoreInfo>
            </ns2:DetailsYouRequested>
        </ns2:SomeDefinedApiMethodResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```


## XSD

XSD is a topic in and of itself. There is a lot of stuff you can learn about it. Here is a link if you want more info:

https://www.w3schools.com/xml/schema_intro.asp


## JAXB (Java API for XML Binding)

When we send messages we want that message to be XML. Plain and Simple

When we process the message we work in Java.

Problem: XML is not Java. Java is not XML.

Solution: Transform XML into Java and then respond back into XML when processed :)

### Using JAXB

JAXB will read the schema and create Java Objects from the XSD. The way we do this in this project is using the `jaxb-maven-plugin`.
This will generate the classes and package them as part of the current project. We want to add this plugin as follows:

#### pom.xml
```xml
<plugins>
    <!-- JAXB2 Maven Plugin -->
    <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>jaxb2-maven-plugin</artifactId>
        <version>3.1.0</version>

        <executions>
            <execution>
                <id>xjc</id> <!-- I think this can be wtv -->
                <goals>
                    <goal>xjc</goal> <!-- This plugin has a few goals. XJC generates from xsd to class files we will use in the code base. -->
                </goals>
            </execution>
        </executions>
        <configuration>
            <!-- XSD Source Files -->
            <sources>
                <source>${project.basedir}/src/main/resources/schema-definitions/course-details.xsd</source>
            </sources>
            <!-- Java Object Source Folder -->
            <outputDirectory>${project.basedir}/src/main/java</outputDirectory> <!-- At work we simply package it and throw it into nexus for wide-use -->
            <!-- clear folder -> false (would delete the src/main/java after every build apparently) -->
            <clearOutputDir>false</clearOutputDir>
        </configuration>
    </plugin>

</plugins>
```



## Creating Endpoint