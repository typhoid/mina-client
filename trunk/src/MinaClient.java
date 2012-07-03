import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * @author giftsam
 */
public class MinaClient
{
	private static final int PORT = 1234;

	public static void main(String[] args) throws IOException,
			InterruptedException
	{
		System.out.println("Open connect...");
		IoConnector connector = new NioSocketConnector();
		connector.getSessionConfig().setReadBufferSize(2048);

		System.out.println("Add Logging Filter..");
		connector.getFilterChain().addLast("logger", new LoggingFilter());

		System.out.println("Add Protocol Codec Filter..");
		connector.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(
						Charset.forName("UTF-8"))));

		System.out.println("Add handler..");
		connector.setHandler(new MinaClientHandler("Hello Server.."));

		System.out.println("Connecting to " + "localhost" + " via port " + PORT);
		ConnectFuture future = connector.connect(new InetSocketAddress(
				"localhost", PORT));

		System.out.println("Wait to interrupt..");
		future.awaitUninterruptibly();

		if (!future.isConnected())
		{
			return;
		}
		
		System.out.println("Get session..");
		IoSession session = future.getSession();
		session.getConfig().setUseReadOperation(true);
		session.getCloseFuture().awaitUninterruptibly();

		System.out.println("Close!");
		connector.dispose();

	}

}
