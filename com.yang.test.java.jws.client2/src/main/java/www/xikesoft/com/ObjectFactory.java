
package www.xikesoft.com;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the www.xikesoft.com package. 
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

    private final static QName _AccountInfo_QNAME = new QName("http://www.xikesoft.com/", "AccountInfo");
    private final static QName _AccountInfoResponse_QNAME = new QName("http://www.xikesoft.com/", "AccountInfoResponse");
    private final static QName _Init_QNAME = new QName("http://www.xikesoft.com/", "init");
    private final static QName _InitResponse_QNAME = new QName("http://www.xikesoft.com/", "initResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: www.xikesoft.com
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AccountInfo }
     * 
     */
    public AccountInfo createAccountInfo() {
        return new AccountInfo();
    }

    /**
     * Create an instance of {@link AccountInfoResponse }
     * 
     */
    public AccountInfoResponse createAccountInfoResponse() {
        return new AccountInfoResponse();
    }

    /**
     * Create an instance of {@link Init }
     * 
     */
    public Init createInit() {
        return new Init();
    }

    /**
     * Create an instance of {@link InitResponse }
     * 
     */
    public InitResponse createInitResponse() {
        return new InitResponse();
    }

    /**
     * Create an instance of {@link WebServiceTestDto }
     * 
     */
    public WebServiceTestDto createWebServiceTestDto() {
        return new WebServiceTestDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccountInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xikesoft.com/", name = "AccountInfo")
    public JAXBElement<AccountInfo> createAccountInfo(AccountInfo value) {
        return new JAXBElement<AccountInfo>(_AccountInfo_QNAME, AccountInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccountInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xikesoft.com/", name = "AccountInfoResponse")
    public JAXBElement<AccountInfoResponse> createAccountInfoResponse(AccountInfoResponse value) {
        return new JAXBElement<AccountInfoResponse>(_AccountInfoResponse_QNAME, AccountInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Init }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xikesoft.com/", name = "init")
    public JAXBElement<Init> createInit(Init value) {
        return new JAXBElement<Init>(_Init_QNAME, Init.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InitResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xikesoft.com/", name = "initResponse")
    public JAXBElement<InitResponse> createInitResponse(InitResponse value) {
        return new JAXBElement<InitResponse>(_InitResponse_QNAME, InitResponse.class, null, value);
    }

}
