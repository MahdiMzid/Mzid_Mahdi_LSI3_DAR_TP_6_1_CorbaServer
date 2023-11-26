package corbaServer;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import service.ConversionImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class conversionServer {
    public static void main(String[] args) {


        try {
            // a.Initialisation ORB
            ORB orb = ORB.init(args, null);
            // c.Configurer annuaire
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sum.jndi.cosnaming.CNCtxFactory");
            props.put(Context.PROVIDER_URL, "iiop://localhost:900");
            Context ctx = new InitialContext(props);
            // b.
            POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            poa.the_POAManager().activate();
            ConversionImpl od = new ConversionImpl();
            ctx.rebind("OD",poa.servant_to_reference(od));
            orb.run();

        } catch (NamingException | InvalidName e) {
            e.printStackTrace();
        } catch (AdapterInactive | WrongPolicy | ServantNotActive e) {
            throw new RuntimeException(e);
        }
        //

    }
}
