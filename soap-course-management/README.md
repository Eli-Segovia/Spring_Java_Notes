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

please refer to src/main/java/com/elisegovia/projects/webservices/soap_course_management/soap. I have some notes in the
src code that is pretty good.

## SOAP Clients
In order to test soap endpoints, we can't just access natively on the browser like you can with GET requests for REST
apis. In this course, we are using Wizdler, which is a browser extension. --> it's deprecated -_-

So, I will be using Soap UI :)

## WTF is WSDL?

wsdl = definition of our services and what schemas they use for request and response. It has different parts and different definitions.
Here is a snippet of what one looks like:

```xml
<wsdl:definitions xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:sch="http://elisegovia.com/courses" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://elisegovia.com/courses" targetNamespace="http://elisegovia.com/courses">
    <wsdl:types>
        <xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema" elementFormDefault="qualified" targetNamespace="http://elisegovia.com/courses">
            <xs:element name="GetCourseDetailsRequest">
                <xs:complexType>
                    <xs:sequence>
                        <xs:element name="id" type="xs:int"/>
                    </xs:sequence>
                </xs:complexType>
            </xs:element>
            <xs:element name="GetCourseDetailsResponse">
                <xs:complexType>
                    <xs:sequence>
                        <xs:element name="CourseDetails" type="tns:CourseDetails"/>
                    </xs:sequence>
                </xs:complexType>
            </xs:element>
            <xs:element name="GetAllCourseDetailsRequest">
                <!--  Added so that JAXB can pick this up - doesn't handle simple types.  -->
                <xs:complexType/>
            </xs:element>
            <xs:element name="GetAllCourseDetailsResponse">
                <xs:complexType>
                    <xs:sequence>
                        <xs:element maxOccurs="unbounded" name="CourseDetails" type="tns:CourseDetails"/>
                    </xs:sequence>
                </xs:complexType>
            </xs:element>
            <xs:complexType name="CourseDetails">
                <xs:sequence>
                    <xs:element name="id" type="xs:int"/>
                    <xs:element name="name" type="xs:string"/>
                    <xs:element name="description" type="xs:string"/>
                </xs:sequence>
            </xs:complexType>
        </xs:schema>
    </wsdl:types>
    <wsdl:message name="GetAllCourseDetailsRequest">
        <wsdl:part element="tns:GetAllCourseDetailsRequest" name="GetAllCourseDetailsRequest"> </wsdl:part>
    </wsdl:message>
    <wsdl:message name="GetAllCourseDetailsResponse">
        <wsdl:part element="tns:GetAllCourseDetailsResponse" name="GetAllCourseDetailsResponse"> </wsdl:part>
    </wsdl:message>
    <wsdl:message name="GetCourseDetailsResponse">
        <wsdl:part element="tns:GetCourseDetailsResponse" name="GetCourseDetailsResponse"> </wsdl:part>
    </wsdl:message>
    <wsdl:message name="GetCourseDetailsRequest">
        <wsdl:part element="tns:GetCourseDetailsRequest" name="GetCourseDetailsRequest"> </wsdl:part>
    </wsdl:message>
    <wsdl:portType name="CoursePort">
        <wsdl:operation name="GetAllCourseDetails">
            <wsdl:input message="tns:GetAllCourseDetailsRequest" name="GetAllCourseDetailsRequest"> </wsdl:input>
            <wsdl:output message="tns:GetAllCourseDetailsResponse" name="GetAllCourseDetailsResponse"> </wsdl:output>
        </wsdl:operation>
        <wsdl:operation name="GetCourseDetails">
            <wsdl:input message="tns:GetCourseDetailsRequest" name="GetCourseDetailsRequest"> </wsdl:input>
            <wsdl:output message="tns:GetCourseDetailsResponse" name="GetCourseDetailsResponse"> </wsdl:output>
        </wsdl:operation>
    </wsdl:portType>
    <wsdl:binding name="CoursePortSoap11" type="tns:CoursePort">
        <soap:binding style="document" transport="http://schemas.xmlsoap.org/soap/http"/>
        <wsdl:operation name="GetAllCourseDetails">
            <soap:operation soapAction=""/>
            <wsdl:input name="GetAllCourseDetailsRequest">
                <soap:body use="literal"/>
            </wsdl:input>
            <wsdl:output name="GetAllCourseDetailsResponse">
                <soap:body use="literal"/>
            </wsdl:output>
        </wsdl:operation>
        <wsdl:operation name="GetCourseDetails">
            <soap:operation soapAction=""/>
            <wsdl:input name="GetCourseDetailsRequest">
                <soap:body use="literal"/>
            </wsdl:input>
            <wsdl:output name="GetCourseDetailsResponse">
                <soap:body use="literal"/>
            </wsdl:output>
        </wsdl:operation>
    </wsdl:binding>
    <wsdl:service name="CoursePortService">
        <wsdl:port binding="tns:CoursePortSoap11" name="CoursePortSoap11">
            <soap:address location="http://localhost:8080/ws"/>
        </wsdl:port>
    </wsdl:service>
</wsdl:definitions>
```

`wsdl:types` = the schemas we are using for this web service. It also enumerates the complex types and simple types that are
defined in these xsds

`wsdl:message` = the different requests and responses exchanged in our operations. Think of operations as the business logic
in the java, and the messages as the way we talk to the business logic, and the way the business logic responds.

`wsdl:portType` = portType and ports do NOT have anything to do with what you traditionally think a port is.
It has NOTHING to do with port 80,443, or 22 like you think when you think of port. Port in the wsdl context almost means 
the service itself. It's what defines the operations that we have defined in the Java itself.

`wsdl:operation` = operation defines an input and output and maps to a java method that takes said input and said output.
It's like a Java method exposed to the client as a `wsdl:operation` but they're basically one-to-one

`wsdl:binding` = tells you how to call this soap interface. specifies the `transport`: can be http or MQ. Tells you where
the transport method of your messages. Basically tells you how this service is exposed.

`wsdl:service` = sort of tells you the address the service is located

## Security and WS Security

Security with WS-Security provides the following functionality/interoperability:
- Authentication
- Digital Signatures
- Certificates

Implementation -> XWSS - XML and Web Services Security
- Security Policy
- XwsSecurityInterceptor

#### Security Dependencies:
```xml

<dependency>
    <groupId>org.springframework.ws</groupId>
    <artifactId>spring-ws-security</artifactId>
    <exclusions>
        <exlusion>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-core</artifactId>
        </exlusion>
    </exclusions>
</dependency>

<dependency>
    <groupId>com.sun.xml.wss</groupId>
    <artifactId>xws-security</artifactId>
    <version>3.0</version>
    <exclusions>
        <exlusion>
            <groupId>javax.xml.crypto</groupId>
            <artifactId>xmldsig</artifactId>
        </exlusion>
    </exclusions>
</dependency>

<dependency>
    <groupId>jakarta.activation</groupId>
    <artifactId>activation</artifactId>
    <version></version>
</dependency>
```


