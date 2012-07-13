package com.server;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.server.constants.CONSTANTS;
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

		JSONObject jsonObject = null;
		try
		{
			jsonObject = new JSONObject(messageReceived);
		} catch (JSONException e)
		{
			jsonObject = null;
			e.printStackTrace();
		}
		if (jsonObject == null)
		{
			logger.error("Load message fail!");
			return;
		}

		int type = JSONHelper.getInt(jsonObject, CONSTANTS.TYPES.JSON_TYPE + "");

		String value = null;
		value = JSONHelper.getString(jsonObject, CONSTANTS.TYPES.JSON_VALUE
				+ "");

		if (value == null || value.equals(""))
		{
			logger.error("Have no message value!");
			return;
		}

		jsonObject = null;
		try
		{
			jsonObject = new JSONObject(value);
		} catch (JSONException e)
		{
			jsonObject = null;
			e.printStackTrace();
		}

		if (jsonObject == null)
		{
			logger.error("Load message value fail!");
			return;
		}

		String columnSet = null;
		String columnValue = null;

		switch (type)
		{
			case CONSTANTS.TYPES.TYPE_BOX_INVENTORY:

				DatabaseUtils.createTableBoxInventory();
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
			case CONSTANTS.TYPES.TYPE_PRODUCT_PUTIN:

				DatabaseUtils.createTableProductPutIn();
				columnSet = CONSTANTS.PRODUCT_PUT_IN.COLUMN_AMOUNT + ", "
						+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_CODE_BOX + ", "
						+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_CODE_PRODUCT + ", "
						+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_DATE + ", "
						+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_MACHINE_ID + ", "
						+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_STAFF_CHECKER + ", "
						+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_STAFF_SERVICE;

				columnValue = +JSONHelper.getInt(jsonObject, CONSTANTS.PRODUCT_PUT_IN.COLUMN_INDEX_AMOUNT
						+ "")
						+ ","

						+ JSONHelper.getInt(jsonObject, CONSTANTS.PRODUCT_PUT_IN.COLUMN_INDEX_CODE_BOX
								+ "")

						+ ",'"
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_PUT_IN.COLUMN_INDEX_CODE_PRODUCT
								+ "")
						+ "','"
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_PUT_IN.COLUMN_INDEX_DATE
								+ "")
						+ "','"
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_PUT_IN.COLUMN_INDEX_MACHINE_ID
								+ "")
						+ "','"

						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_PUT_IN.COLUMN_INDEX_STAFF_CHECKER
								+ "")
						+ "','"
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_PUT_IN.COLUMN_INDEX_STAFF_SERVICE
								+ "") + "'";

				break;
			case CONSTANTS.TYPES.TYPE_PRODUCT_TAKEOUT:

				DatabaseUtils.createTableProductTakeOut();

				columnSet = CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_AMOUNT + ", "
						+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_CODE_BOX + ", "
						+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_CODE_PRODUCT + ", "
						+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_DATE + ", "
						+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_MACHINE_ID + ", "
						+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_STAFF_CHECKER
						+ ", "
						+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_STAFF_SERVICE;

				columnValue = +JSONHelper.getInt(jsonObject, CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_INDEX_AMOUNT
						+ "")
						+ ","

						+ JSONHelper.getInt(jsonObject, CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_INDEX_CODE_BOX
								+ "")

						+ ",'"
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_INDEX_CODE_PRODUCT
								+ "")
						+ "','"
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_INDEX_DATE
								+ "")
						+ "','"
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_INDEX_MACHINE_ID
								+ "")
						+ "','"

						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_INDEX_STAFF_CHECKER
								+ "")
						+ "','"
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_INDEX_STAFF_SERVICE
								+ "") + "'";
				break;
			case CONSTANTS.TYPES.TYPE_SELL:

				DatabaseUtils.createTableProductSell();

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

				columnValue = "'"
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_ACOUNT_NUMBER
								+ "")
						+ "',"

						+ JSONHelper.getInt(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_AMOUNT
								+ "")

						+ ",'"
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_CODE_BOX
								+ "")
						+ "',"

						+ "'"
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_CODE_PRODUCT
								+ "")
						+ "',"

						+ "'"
						+ JSONHelper.getString(jsonObject, CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_DATE
								+ "")
						+ "',"

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
				break;
		}
		if (columnSet != null && columnValue != null) DatabaseManager.insert(CONSTANTS.PRODUCT_TAKE_OUT.TABLE_NAME, columnSet, columnValue);
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
		logger.debug("@1111111111111111111111111111");
		logger.error("@222222222222222222222222222222");
		logger.trace("@333333333333333333333333");
		logger.warn("@4444444444444444444444444");

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
