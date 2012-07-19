package com.server;

import java.sql.Timestamp;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.server.constants.CONSTANTS;
import com.server.constants.CONSTANTS.JSON;
import com.server.constants.CONSTANTS.SERVER_TRANSACTION;
import com.server.constants.CONSTANTS.STATUS;
import com.server.db.DatabaseManager;
import com.server.db.DatabaseUtils;
import com.server.helper.JSONHelper;
import com.server.json.JSONException;
import com.server.json.JSONObject;

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

		String messageReceived = (String) message;
		logger.info("Message is: " + messageReceived);

		if (messageReceived != null && !messageReceived.equals(""))
		{
			int status = process(messageReceived);

			JSONObject jsonObject = new JSONObject();
			try
			{
				jsonObject.put(JSON.KEY_TYPE + "", SERVER_TRANSACTION.REPLY);
				jsonObject.put(CONSTANTS.JSON.KEY_VALUE + "", status);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
			session.write(jsonObject.toString());
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

		super.sessionCreated(session);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception
	{
		super.messageSent(session, message);
	}

	private int process(String message)
	{
		JSONObject jsonObject = null;
		try
		{
			jsonObject = new JSONObject(message);
		} catch (JSONException e)
		{
			jsonObject = null;
			e.printStackTrace();

			logger.error("Load message fail!");
			return STATUS.RECEIVE_FAIL_JSON;
		}

		int type = JSONHelper.getInt(jsonObject, CONSTANTS.JSON.KEY_TYPE + "");

		String value = null;
		value = JSONHelper.getString(jsonObject, CONSTANTS.JSON.KEY_VALUE + "");

		if (value == null || value.equals(""))
		{
			logger.error("Have no message value!");
			return STATUS.RECEIVE_FAIL_VALUE_NULL;
		}

		jsonObject = null;
		try
		{
			jsonObject = new JSONObject(value);
		} catch (JSONException e)
		{
			jsonObject = null;
			e.printStackTrace();

			logger.error("Load message value fail!");
			return STATUS.RECEIVE_FAIL_JSON_VALUE;
		}

		String columnSet = null;
		String columnValue = null;
		Timestamp timestamp = null;
		long time = 0;
		String tableName = null;

		boolean isCreateTableSuccess = false;
		boolean isInsertSuccess = false;

		switch (type)
		{
			case CONSTANTS.CLIENT_TRANSACTION.BOX_INVENTORY:

				isCreateTableSuccess = DatabaseUtils.createTableBoxInventory();

				tableName = CONSTANTS.BOX_INVENTORY.TABLE_NAME;

				columnSet = CONSTANTS.BOX_INVENTORY.COLUMN_AMOUNT + ", "
						+ CONSTANTS.BOX_INVENTORY.COLUMN_CODE_BOX + ", "
						+ CONSTANTS.BOX_INVENTORY.COLUMN_CODE_PRODUCT + ", "
						+ CONSTANTS.BOX_INVENTORY.COLUMN_MACHINE_ID;

				columnValue = +JSONHelper.getInt(jsonObject, CONSTANTS.BOX_INVENTORY.COLUMN_INDEX_AMOUNT
						+ "")
						+ ","

						+ JSONHelper.getInt(jsonObject, CONSTANTS.BOX_INVENTORY.COLUMN_INDEX_CODE_BOX
								+ "")

						+ ",'"
						+ JSONHelper.getString(jsonObject, CONSTANTS.BOX_INVENTORY.COLUMN_INDEX_CODE_PRODUCT
								+ "")
						+ "','"
						+ JSONHelper.getString(jsonObject, CONSTANTS.BOX_INVENTORY.COLUMN_INDEX_MACHINE_ID
								+ "") + "'";

				break;
			case CONSTANTS.CLIENT_TRANSACTION.PRODUCT_PUTIN:

				isCreateTableSuccess = DatabaseUtils.createTableProductPutIn();

				tableName = CONSTANTS.PRODUCT_PUT_IN.TABLE_NAME;

				columnSet = CONSTANTS.PRODUCT_PUT_IN.COLUMN_AMOUNT + ", "
						+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_CODE_BOX + ", "
						+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_CODE_PRODUCT + ", "
						+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_DATE + ", "
						+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_MACHINE_ID + ", "
						+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_STAFF_CHECKER + ", "
						+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_STAFF_SERVICE;

				time = JSONHelper.getLong(jsonObject, CONSTANTS.PRODUCT_PUT_IN.COLUMN_DATE);

				if (time == 0) time = System.currentTimeMillis();

				timestamp = new Timestamp(time);

				columnValue = +JSONHelper.getInt(jsonObject, CONSTANTS.PRODUCT_PUT_IN.COLUMN_INDEX_AMOUNT
						+ "")
						+ ","

						+ JSONHelper.getInt(jsonObject, CONSTANTS.PRODUCT_PUT_IN.COLUMN_INDEX_CODE_BOX
								+ "")

						+ ",'"
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_PUT_IN.COLUMN_INDEX_CODE_PRODUCT
								+ "")
						+ "', ?,'"
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_PUT_IN.COLUMN_INDEX_MACHINE_ID
								+ "")
						+ "','"

						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_PUT_IN.COLUMN_INDEX_STAFF_CHECKER
								+ "")
						+ "','"
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_PUT_IN.COLUMN_INDEX_STAFF_SERVICE
								+ "") + "'";

				break;
			case CONSTANTS.CLIENT_TRANSACTION.PRODUCT_TAKEOUT:

				isCreateTableSuccess = DatabaseUtils.createTableProductTakeOut();

				tableName = CONSTANTS.PRODUCT_TAKE_OUT.TABLE_NAME;

				columnSet = CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_AMOUNT + ", "
						+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_CODE_BOX + ", "
						+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_CODE_PRODUCT + ", "
						+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_DATE + ", "
						+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_MACHINE_ID + ", "
						+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_STAFF_CHECKER
						+ ", "
						+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_STAFF_SERVICE;

				time = JSONHelper.getLong(jsonObject, CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_DATE);

				if (time == 0) time = System.currentTimeMillis();

				timestamp = new Timestamp(time);

				columnValue = +JSONHelper.getInt(jsonObject, CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_INDEX_AMOUNT
						+ "")
						+ ","

						+ JSONHelper.getInt(jsonObject, CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_INDEX_CODE_BOX
								+ "")

						+ ",'"
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_INDEX_CODE_PRODUCT
								+ "")
						+ "', ?,'"
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_INDEX_MACHINE_ID
								+ "")
						+ "','"

						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_INDEX_STAFF_CHECKER
								+ "")
						+ "','"
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_INDEX_STAFF_SERVICE
								+ "") + "'";
				break;
			case CONSTANTS.CLIENT_TRANSACTION.SELL:

				isCreateTableSuccess = DatabaseUtils.createTableProductSell();

				tableName = CONSTANTS.PRODUCT_SELL.TABLE_NAME;

				columnSet = CONSTANTS.PRODUCT_SELL.COLUMN_ACCOUNT_NUMBER + ", "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_AMOUNT + ", "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_BOX_CODE + ", "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_PRODUCT_CODE + ", "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_DATE + ", "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_IS_DELIVERIED + ", "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_MACHINE_ID + ", "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_PRICE + ", "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_WORK_NUMBER1 + ", "
						+ CONSTANTS.PRODUCT_SELL.COLUMN_WORK_NUMBER2;

				time = JSONHelper.getLong(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_DATE);

				if (time == 0) time = System.currentTimeMillis();

				timestamp = new Timestamp(time);

				columnValue = "'"
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_ACOUNT_NUMBER
								+ "")
						+ "',"

						+ JSONHelper.getInt(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_AMOUNT
								+ "")

						+ ","
						+ JSONHelper.getInt(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_CODE_BOX
								+ "")
						+ ",'"
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_CODE_PRODUCT
								+ "")
						+ "', ?,"

						+ JSONHelper.getInt(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_IS_DELIVERIED
								+ "")
						+ ",'"
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_MACHINE_ID
								+ "")
						+ "',"

						+ JSONHelper.getInt(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_PRICE
								+ "")

						+ ",'"
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_WORK_NUMBER1
								+ "")
						+ "',"

						+ "'"
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_WORK_NUMBER2
								+ "") + "'";
				break;

			default:
				return STATUS.RECEIVE_UNKNOWN_TYPE;
		}
		if (columnSet != null && columnValue != null && tableName != null)
		{
			isInsertSuccess = (timestamp != null) ? DatabaseManager.insert(tableName, columnSet, columnValue, timestamp) : DatabaseManager.insert(tableName, columnSet, columnValue);
		}

		if (!isCreateTableSuccess) return STATUS.DB_CREATE_FAIL;
		if (!isInsertSuccess) return STATUS.DB_INSERT_FAIL;

		return STATUS.RECEIVE_OK;
	}
}
