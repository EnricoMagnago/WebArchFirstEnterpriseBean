package it.unitn.webarch.enterprisebean.client;

import it.unitn.webarch.enterprisebean.common.Timestamp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class Client{

        public static void main(String[] args){
                Properties props = new Properties();
                props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
                props.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
                props.put(Context.SECURITY_PRINCIPAL, "user");
                props.put(Context.SECURITY_CREDENTIALS, "user");

                Context ctx = null;
                try{
                        ctx = new InitialContext(props);
                }catch(NamingException e){
                        System.err.println("Client: can not create initial context");
                        e.printStackTrace();
                        System.exit(1);
                }

                Timestamp timestamp = null;
                try{
                        timestamp = (Timestamp) ctx.lookup("ejb:/Server/TimestampBean!it.unitn.webarch.enterprisebean.common" +
                                        ".Timestamp");
                }catch(NamingException e){
                        System.err.println("Client: object lookup failed");
                        e.printStackTrace();
                        System.exit(1);
                }

                System.out.println(timestamp.getTimestamp());
                try{
                        Thread.sleep(1000);
                }catch(InterruptedException e){
                        e.printStackTrace();
                }
                System.out.println(timestamp.getTimestamp());
        }
}
