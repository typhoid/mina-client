package com.server;

/**
 * @author Ngoc Tan
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.server.db.DatabaseManager;

public class MinaServer
{
	private static final int PORT = 1234;

	public static void main(String[] args) throws IOException
	{
		System.out.println("Starting server..");

		testDB();
		
		IoAcceptor acceptor = new NioSocketAcceptor();

		System.out.println("Add Logging Filter..");
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());

		System.out.println("Add Protocol Codec Filter..");
		acceptor.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(
						Charset.forName("UTF-8"))));

		System.out.println("Add handler..");
		acceptor.setHandler(new MinaServerHandler());

		System.out.println("Add config..");
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

		System.out.println("Listening on port " + PORT);
		acceptor.bind(new InetSocketAddress(PORT));

	}
	
	
	@SuppressWarnings("unused")
	private static void testDB()
	{
		
		DatabaseManager.open();

		DatabaseManager.createTable("animal",
				"id INT UNSIGNED NOT NULL AUTO_INCREMENT,"
						+ "PRIMARY KEY (id),"
						+ "name CHAR(40), category CHAR(40)");

		DatabaseManager.insert("animal", "name, category",
				"'snake', 'reptile'");

		DatabaseManager.close();

	}
}
