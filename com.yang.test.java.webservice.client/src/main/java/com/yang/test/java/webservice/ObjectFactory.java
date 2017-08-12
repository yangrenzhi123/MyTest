
package com.yang.test.java.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.yang.test.java.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DoTestResponse_QNAME = new QName("http://webservice.java.test.yang.com/", "doTestResponse");
    private final static QName _DoTest_QNAME = new QName("http://webservice.java.test.yang.com/", "doTest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.yang.test.java.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DoTestResponse }
     * 
     */
    public DoTestResponse createDoTestResponse() {
        return new DoTestResponse();
    }

    /**
     * Create an instance of {@link DoTest }
     * 
     */
    public DoTest createDoTest() {
        return new DoTest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoTestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.java.test.yang.com/", name = "doTestResponse")
    public JAXBElement<DoTestResponse> createDoTestResponse(DoTestResponse value) {
        return new JAXBElement<DoTestResponse>(_DoTestResponse_QNAME, DoTestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoTest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.java.test.yang.com/", name = "doTest")
    public JAXBElement<DoTest> createDoTest(DoTest value) {
        return new JAXBElement<DoTest>(_DoTest_QNAME, DoTest.class, null, value);
    }

}
