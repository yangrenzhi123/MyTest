import javax.xml.namespace.QName;
import org.apache.axis2.AxisFault;  
import org.apache.axis2.addressing.EndpointReference;  
import org.apache.axis2.client.Options;  
import org.apache.axis2.rpc.client.RPCServiceClient; 
public class Test {

	public static void main(String[] args) {
        String url="http://192.168.6.152:8080/axis2/services/Hello";  
        QName qName=new QName("http://ws.apache.org/axis2","sayHello");  
                    //其中第一个参数值为访问url的targetNamespace  
          
        try {  
            //指定调用webservice的URL  
            RPCServiceClient client = new RPCServiceClient();  
            Options option=client.getOptions();  
            ////指定调用webservice的URL  
            option.setTo(new EndpointReference(url));  
            //设置输入参数  
            Object[] input=new Object[]{"555"};  
            //设置返回类型  
            Class[] output=new Class[]{String.class};  
            //返回值  
            Object[] returnValue=client.invokeBlocking(qName, input, output);  
            System.out.println(returnValue[0]);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
	}
}