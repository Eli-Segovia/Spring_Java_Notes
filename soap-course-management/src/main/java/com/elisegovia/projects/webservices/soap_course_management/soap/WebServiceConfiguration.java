package com.elisegovia.projects.webservices.soap_course_management.soap;



import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


/**
 *
 * Some new annotations here:
 * Configuration -> tells Spring this is a configuration class
 * EnableWs -> Is used on a Configuration class to enable annotation-driven support for SOAP web services. idk what that means. wtv.
 *
 * Bean -> tells Spring that the returned object should be managed by spring using Dependency Injection
 *
 */
@EnableWs
@Configuration
public class WebServiceConfiguration {
    /**
     * ###############  Registering a MessageDispatcherServlet #############################
     *
     * WTF is a servlet -> A Java Servlet is a server-side Java class used to handle requests.
     *
     * This project has SOAP endpoints that needs to handle requests. Therefore, we need a servlet.
     *
     * MessageDispatcherServlet is a specialized Spring Web Services (Spring-WS) servlet used to handle SOAP web service messages.
     *        This is the type of servlet we need for SOAP.
     *
     *
     * MessageDispatcherServlet needs to register to our Spring Application
     *
     *
     * This dispatcher servlet needs the following details:
     *     - ApplicationContext
     *     - url
     *
     * So here we create a servlet, map it to the url provided and hand it off to Spring.
     */

    @Bean
    public ServletRegistrationBean registerMessageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
    }


    /**
     * ##################  Configuring a WSDL #############################
     * We are going to tell spring to generate a wsdl from an xsd file.
     *
     * generateCoursesSchema
     * First, we tell spring -> Hey, we need the schema to be handled by Spring. So spring now knows about the
     *     courses schema
     *
     * generateCoursesWSDL
     * Then, we tell Spring -> Hey, I want a mapping from code to schema to the URI, so that when we hit the endpoint
     *     the servlet can direct us to the right Java methods and shit. Spring will autowire in the schema
     *     (that we already generated in generateCoursesSchema above the generateCoursesWsdl method) and then
     *     we configure the rest of the needed information
     *        - PortType -> this is sort of like the name of the interface/class in WSDL land
     *        - namespace -> the namespace is the same as the xsd
     *        - uri -> the url the wsdl will be mapped to so the servlet can send over to right java methods.
     *        - schema -> the xsd that defines the interface.
     *
     *
     */

    @Bean
    public XsdSchema generateCoursesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schema-definitions/course-details.xsd"));
    }

    @Bean(name="courses")
    public DefaultWsdl11Definition generateCoursesWSDL (XsdSchema coursesSchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("CoursePort");
        definition.setTargetNamespace("http://elisegovia.com/courses");
        definition.setLocationUri("/ws");
        definition.setSchema(coursesSchema);
        return definition;
    }



}
