package com.server;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.server.constants.CONSTANTS;
import com.server.db.DatabaseManager;
import com.server.helper.JSONHelper;
import com.server.json.JSONException;
import com.server.json.JSONObject;
import com.server.model.Message;

/**
 * @author giftsam
 */
public class MinaServerHandler extends IoHandlerAdapter
{
	private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

	@Override
	public void sessionOpened(IoSession session)
	{
		// set idle time to 10 seconds
		session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

		session.setAttribute("Values: ");
	}

	@Override
	public void messageReceived(IoSession session, Object message)
	{
		logger.info("Message received in the server..");
		logger.info("Message is: " + message.toString());
		System.out.println("Message received in the server..");
		System.out.println("Message is: " + message.toString());
		
		Message message2 = (Message) message;
		int type = message2.getType();

		JSONObject jsonObject = null;
		try
		{
			jsonObject = new JSONObject(message2.getValue());
		} catch (JSONException e)
		{
			jsonObject = null;
			e.printStackTrace();
		}
		if (jsonObject == null) return;

		switch (type)
		{
			case CONSTANTS.TYPES.TYPE_BOX_INVENTORY:

				break;
			case CONSTANTS.TYPES.TYPE_PRODUCT_PUTIN:

				break;
			case CONSTANTS.TYPES.TYPE_PRODUCT_TAKEOUT:

				break;
			case CONSTANTS.TYPES.TYPE_SELL:

				String columnSet = "id INT UNSIGNED NOT NULL AUTO_INCREMENT, "
						+ "PRIMARY KEY (id), "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_ACCOUNT_NUMBER
						+ " CHAR(15), "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_AMOUNT
						+ " INT, "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_CODE_BOX
						+ " CHAR(20), "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_CODE_PRODUCT
						+ " CHAR(20), "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_DATE
						+ " CHAR(30), "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_IS_DELIVERIED
						+ " INT, "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_MACHINE_ID
						+ " CHAR(20), "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_MACHINE_NAME
						+ " CHAR(20), "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_PRICE
						+ " INT, "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_WORK_NUMBER1
						+ " CHAR(20), "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_WORK_NUMBER2
						+ " CHAR(20), ";
				DatabaseManager.createTable(CONSTANTS.PRODUCT_SELL.TABLE_NAME, columnSet);

				columnSet ="\"" + 
						CONSTANTS.PRODUCT_SELL.COLUMN_ACCOUNT_NUMBER
						+ ", "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_AMOUNT
						+ ", "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_CODE_BOX
						+ ", "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_CODE_PRODUCT
						+ ", "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_DATE
						+ ", "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_IS_DELIVERIED
						+ ", "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_MACHINE_ID
						+ ", "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_MACHINE_NAME
						+ ", "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_PRICE
						+ ", "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_WORK_NUMBER1
						+ ", "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_WORK_NUMBER2
						+ "\"";
				
				String columnValue = null;
				
				columnValue = "\"" + 
						JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_ACOUNT_NUMBER + "")
						+ JSONHelper.getInt(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_AMOUNT + "")
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_CODE_BOX+ "")
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_CODE_PRODUCT+ "")
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_DATE + "")
						+ JSONHelper.getInt(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_IS_DELIVERIED + "")
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_MACHINE_ID + "")
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_MACHINE_NAME + "")
						+ JSONHelper.getInt(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_PRICE + "")
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_WORK_NUMBER1 + "")
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_WORK_NUMBER2 + "")
						+ "\"";
				
				DatabaseManager.insert(CONSTANTS.PRODUCT_SELL.TABLE_NAME, columnSet, columnValue);
				
				break;

			default:
				break;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
	{
		logger.info("Disconnecting the idle.");
		// disconnect an idle client
		session.close();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
	{
		// close the connection on exceptional situation
		session.close();
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception
	{
		session.write("111111111111111111111111111");
		// TODO Auto-generated method stub
		super.sessionCreated(session);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception
	{
		// session.write("111111111111111111111111111222222222222");
		super.messageSent(session, message);
	}
}
