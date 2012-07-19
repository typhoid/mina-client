package com.server;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.server.constants.CONSTANTS;
import com.server.constants.CONSTANTS.ACCOUNT;
import com.server.constants.CONSTANTS.CLIENT_TRANSACTION;
import com.server.constants.CONSTANTS.JSON;
import com.server.constants.CONSTANTS.PRODUCT;
import com.server.constants.CONSTANTS.SERVER_TRANSACTION;
import com.server.constants.CONSTANTS.STATUS;
import com.server.db.DatabaseManager;
import com.server.db.DatabaseUtils;
import com.server.helper.JSONHelper;
import com.server.json.JSONArray;
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
		String messageReceived = (String) message;
		logger.info("Message received: " + messageReceived);

		if (messageReceived != null && !messageReceived.equals(""))
		{
			int status = processIncoming(session, messageReceived);

			if (status != STATUS.CLIENT_WAITING_RESPONSE)
			{
				JSONObject jsonObject = new JSONObject();
				try
				{
					jsonObject.put(JSON.KEY_TYPE + "", SERVER_TRANSACTION.REPLY);
					jsonObject.put(CONSTANTS.JSON.KEY_VALUE + "", status);
					session.write(jsonObject.toString());
				} catch (JSONException e)
				{
					e.printStackTrace();
					logger.error(e.getMessage());
				}

				session.write(jsonObject.toString());
			}
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
		// session.write("111111111111111111111111111");

		super.sessionCreated(session);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception
	{
		super.messageSent(session, message);
	}

	private int processOutcoming(IoSession session, int clientType)
	{
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		int status = STATUS.SERVER_SEND_OK;

		ResultSet resultSet = null;
		switch (clientType)
		{
			case CLIENT_TRANSACTION.ACCOUNT:

				logger.info("Message type requet to table = "
						+ CONSTANTS.ACCOUNT.TABLE_NAME);

				resultSet = DatabaseManager.getAll(CONSTANTS.ACCOUNT.TABLE_NAME);

				if (resultSet == null)
				{
					logger.error("Get database data fail");
					status = STATUS.SERVER_SEND_FAIL_DB;
					break;
				}

				try
				{
					JSONObject jsonObjectElement = null;
					while (resultSet.next())
					{
						jsonObjectElement = new JSONObject();

						jsonObjectElement.put(
								ACCOUNT.COLUMN_INDEX_ACCOUNT_AMOUNT + "",
								resultSet.getInt(CONSTANTS.ACCOUNT.COLUMN_ACCOUNT_AMOUNT));

						jsonObjectElement.put(
								ACCOUNT.COLUMN_INDEX_ACTIVE + "",
								resultSet.getInt(CONSTANTS.ACCOUNT.COLUMN_ACTIVE));

						Date date = resultSet.getDate(CONSTANTS.ACCOUNT.COLUMN_DATE_CREATED);
						long time = date.getTime();

						jsonObjectElement.put(ACCOUNT.COLUMN_INDEX_DATE_CREATED
								+ "", time);

						jsonObjectElement.put(
								ACCOUNT.COLUMN_INDEX_GROUP_ID + "",
								resultSet.getInt(CONSTANTS.ACCOUNT.COLUMN_GROUP_ID));

						jsonObjectElement.put(
								ACCOUNT.COLUMN_INDEX_PASSWORD + "",
								resultSet.getString(CONSTANTS.ACCOUNT.COLUMN_PASSWORD));

						jsonObjectElement.put(
								ACCOUNT.COLUMN_INDEX_PIN_CODE + "",
								resultSet.getString(CONSTANTS.ACCOUNT.COLUMN_PIN_CODE));

						jsonObjectElement.put(
								ACCOUNT.COLUMN_INDEX_POSITION_ID + "",
								resultSet.getInt(CONSTANTS.ACCOUNT.COLUMN_POSITION_ID));
						jsonObjectElement.put(
								ACCOUNT.COLUMN_INDEX_STAFF_ID + "",
								resultSet.getString(CONSTANTS.ACCOUNT.COLUMN_STAFF_ID));
						jsonObjectElement.put(
								ACCOUNT.COLUMN_INDEX_TERMINATED + "",
								resultSet.getInt(CONSTANTS.ACCOUNT.COLUMN_TERMINATED));
						jsonObjectElement.put(
								ACCOUNT.COLUMN_INDEX_USER_NAME + "",
								resultSet.getString(CONSTANTS.ACCOUNT.COLUMN_USER_NAME));

						jsonArray.put(jsonObjectElement);
					}
				} catch (SQLException e)
				{
					e.printStackTrace();
					logger.error(e.getMessage());
					status = STATUS.SERVER_SEND_FAIL_DB_LOAD;
				} catch (JSONException e)
				{
					e.printStackTrace();
					logger.error(e.getMessage());
				}

				break;

			case CLIENT_TRANSACTION.PRODUCT:
				logger.info("Message type requet to table = "
						+ CONSTANTS.PRODUCT.TABLE_NAME);

				resultSet = DatabaseManager.getAll(CONSTANTS.PRODUCT.TABLE_NAME);

				if (resultSet == null)
				{
					logger.error("Get database data fail");
					status = STATUS.SERVER_SEND_FAIL_DB;
					break;
				}

				try
				{
					JSONObject jsonObjectElement = null;
					while (resultSet.next())
					{
						jsonObjectElement = new JSONObject();

						jsonObjectElement.put(
								PRODUCT.COLUMN_INDEX_IMAGE_PATH + "",
								resultSet.getString(CONSTANTS.PRODUCT.COLUMN_IMAGE_PATH));

						jsonObjectElement.put(
								PRODUCT.COLUMN_INDEX_PRICE_BUYING + "",
								resultSet.getInt(CONSTANTS.PRODUCT.COLUMN_PRICE_BUYING));

						jsonObjectElement.put(
								PRODUCT.COLUMN_INDEX_PRICE_SELLING + "",
								resultSet.getInt(CONSTANTS.PRODUCT.COLUMN_PRICE_SELLING));

						jsonObjectElement.put(
								PRODUCT.COLUMN_INDEX_PRODUCT_CODE + "",
								resultSet.getString(CONSTANTS.PRODUCT.COLUMN_PRODUCT_CODE));

						jsonObjectElement.put(
								PRODUCT.COLUMN_INDEX_PRODUCT_GROUP_ID + "",
								resultSet.getInt(CONSTANTS.PRODUCT.COLUMN_PRODUCT_GROUP_ID));

						jsonObjectElement.put(
								PRODUCT.COLUMN_INDEX_PRODUCT_NAME + "",
								resultSet.getString(CONSTANTS.PRODUCT.COLUMN_PRODUCT_NAME));
						jsonObjectElement.put(
								PRODUCT.COLUMN_INDEX_PRODUCT_SUPPLIER_ID + "",
								resultSet.getInt(CONSTANTS.PRODUCT.COLUMN_PRODUCT_SUPPLIER_ID));

						jsonArray.put(jsonObjectElement);
					}
				} catch (SQLException e)
				{
					e.printStackTrace();
					logger.error(e.getMessage());
					status = STATUS.SERVER_SEND_FAIL_DB_LOAD;
				} catch (JSONException e)
				{
					e.printStackTrace();
					logger.error(e.getMessage());
				}
				break;

			default:
				break;
		}

		try
		{
			if (status == STATUS.SERVER_SEND_FAIL_DB)
			{
				jsonObject.put(JSON.KEY_TYPE + "", SERVER_TRANSACTION.ERROR);
				jsonObject.put(CONSTANTS.JSON.KEY_VALUE + "",
						STATUS.SERVER_SEND_FAIL_DB + "");
			}
			else if (status == STATUS.SERVER_SEND_FAIL_DB_LOAD)
			{
				jsonObject.put(JSON.KEY_TYPE + "", SERVER_TRANSACTION.ERROR);
				jsonObject.put(CONSTANTS.JSON.KEY_VALUE + "",
						STATUS.SERVER_SEND_FAIL_DB_LOAD + "");
			}
			else
			{
				jsonObject.put(JSON.KEY_TYPE + "", SERVER_TRANSACTION.REPLY);
				jsonObject.put(CONSTANTS.JSON.KEY_VALUE + "",
						jsonArray.toString());
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		session.write(jsonObject.toString());

		return STATUS.CLIENT_WAITING_RESPONSE;
	}

	private int processIncoming(IoSession session, String message)
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
			logger.error(e.getMessage());
			return STATUS.CLIENT_SEND_FAIL_JSON;
		}

		int type = JSONHelper.getInt(jsonObject, CONSTANTS.JSON.KEY_TYPE + "");

		String value = null;
		value = JSONHelper.getString(jsonObject, CONSTANTS.JSON.KEY_VALUE + "");

		if (value == null || value.equals(""))
		{
			logger.error("Have no message value!");
			return STATUS.CLIENT_SEND_FAIL_VALUE_NULL;
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
			logger.error(e.getMessage());
			return STATUS.CLIENT_SEND_FAIL_JSON_VALUE;
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
			case CLIENT_TRANSACTION.BOX_INVENTORY:
				logger.info("Message type insert to table = "
						+ CONSTANTS.BOX_INVENTORY.TABLE_NAME);

				isCreateTableSuccess = DatabaseUtils.createTableBoxInventory();

				tableName = CONSTANTS.BOX_INVENTORY.TABLE_NAME;

				columnSet = CONSTANTS.BOX_INVENTORY.COLUMN_AMOUNT + ", "
						+ CONSTANTS.BOX_INVENTORY.COLUMN_BOX_CODE + ", "
						+ CONSTANTS.BOX_INVENTORY.COLUMN_PRODUCT_CODE + ", "
						+ CONSTANTS.BOX_INVENTORY.COLUMN_MACHINE_ID;

				columnValue = +JSONHelper.getInt(jsonObject,
						CONSTANTS.BOX_INVENTORY.COLUMN_INDEX_AMOUNT + "")
						+ ","

						+ JSONHelper.getInt(jsonObject,
								CONSTANTS.BOX_INVENTORY.COLUMN_INDEX_BOX_CODE
										+ "")

						+ ",'"
						+ JSONHelper.getString(
								jsonObject,
								CONSTANTS.BOX_INVENTORY.COLUMN_INDEX_PRODUCT_CODE
										+ "")
						+ "','"
						+ JSONHelper.getString(jsonObject,
								CONSTANTS.BOX_INVENTORY.COLUMN_INDEX_MACHINE_ID
										+ "") + "'";

				break;
			case CLIENT_TRANSACTION.ACCOUNT:
				/* Request for accounts */
			case CLIENT_TRANSACTION.PRODUCT:
				/* Request for products */
				return processOutcoming(session, type);

			case CLIENT_TRANSACTION.PRODUCT_PUTIN:

				logger.info("Message type insert to table = "
						+ CONSTANTS.PRODUCT_PUT_IN.TABLE_NAME);

				isCreateTableSuccess = DatabaseUtils.createTableProductPutIn();

				tableName = CONSTANTS.PRODUCT_PUT_IN.TABLE_NAME;

				columnSet = CONSTANTS.PRODUCT_PUT_IN.COLUMN_AMOUNT + ", "
						+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_BOX_CODE + ", "
						+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_PRODUCT_CODE + ", "
						+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_DATE + ", "
						+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_MACHINE_ID + ", "
						+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_STAFF_CHECKER + ", "
						+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_STAFF_SERVICE;

				time = JSONHelper.getLong(jsonObject,
						CONSTANTS.PRODUCT_PUT_IN.COLUMN_DATE);

				if (time == 0) time = System.currentTimeMillis();

				timestamp = new Timestamp(time);

				columnValue = +JSONHelper.getInt(jsonObject,
						CONSTANTS.PRODUCT_PUT_IN.COLUMN_INDEX_AMOUNT + "")
						+ ","

						+ JSONHelper.getInt(jsonObject,
								CONSTANTS.PRODUCT_PUT_IN.COLUMN_INDEX_BOX_CODE
										+ "")

						+ ",'"
						+ JSONHelper.getString(
								jsonObject,
								CONSTANTS.PRODUCT_PUT_IN.COLUMN_INDEX_PRODUCT_CODE
										+ "")
						+ "', ?,'"
						+ JSONHelper.getString(
								jsonObject,
								CONSTANTS.PRODUCT_PUT_IN.COLUMN_INDEX_MACHINE_ID
										+ "")
						+ "','"

						+ JSONHelper.getString(
								jsonObject,
								CONSTANTS.PRODUCT_PUT_IN.COLUMN_INDEX_STAFF_CHECKER
										+ "")
						+ "','"
						+ JSONHelper.getString(
								jsonObject,
								CONSTANTS.PRODUCT_PUT_IN.COLUMN_INDEX_STAFF_SERVICE
										+ "") + "'";

				break;
			case CLIENT_TRANSACTION.PRODUCT_TAKEOUT:

				logger.info("Message type insert to table = "
						+ CONSTANTS.PRODUCT_TAKE_OUT.TABLE_NAME);

				isCreateTableSuccess = DatabaseUtils.createTableProductTakeOut();

				tableName = CONSTANTS.PRODUCT_TAKE_OUT.TABLE_NAME;

				columnSet = CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_AMOUNT + ", "
						+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_BOX_CODE + ", "
						+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_CODE_PRODUCT + ", "
						+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_DATE + ", "
						+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_MACHINE_ID + ", "
						+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_STAFF_CHECKER
						+ ", "
						+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_STAFF_SERVICE;

				time = JSONHelper.getLong(jsonObject,
						CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_DATE);

				if (time == 0) time = System.currentTimeMillis();

				timestamp = new Timestamp(time);

				columnValue = +JSONHelper.getInt(jsonObject,
						CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_INDEX_AMOUNT + "")
						+ ","

						+ JSONHelper.getInt(
								jsonObject,
								CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_INDEX_CODE_BOX
										+ "")

						+ ",'"
						+ JSONHelper.getString(
								jsonObject,
								CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_INDEX_CODE_PRODUCT
										+ "")
						+ "', ?,'"
						+ JSONHelper.getString(
								jsonObject,
								CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_INDEX_MACHINE_ID
										+ "")
						+ "','"

						+ JSONHelper.getString(
								jsonObject,
								CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_INDEX_STAFF_CHECKER
										+ "")
						+ "','"
						+ JSONHelper.getString(
								jsonObject,
								CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_INDEX_STAFF_SERVICE
										+ "") + "'";
				break;
			case CLIENT_TRANSACTION.SELL:

				logger.info("Message type insert to table = "
						+ CONSTANTS.PRODUCT_SELL.TABLE_NAME);

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

				time = JSONHelper.getLong(jsonObject,
						CONSTANTS.PRODUCT_SELL.COLUMN_DATE);

				if (time == 0) time = System.currentTimeMillis();

				timestamp = new Timestamp(time);

				columnValue = "'"
						+ JSONHelper.getString(
								jsonObject,
								CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_ACOUNT_NUMBER
										+ "")
						+ "',"

						+ JSONHelper.getInt(jsonObject,
								CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_AMOUNT + "")

						+ ","
						+ JSONHelper.getInt(jsonObject,
								CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_BOX_CODE
										+ "")
						+ ",'"
						+ JSONHelper.getString(
								jsonObject,
								CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_PRODUCT_CODE
										+ "")
						+ "', ?,"

						+ JSONHelper.getInt(
								jsonObject,
								CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_IS_DELIVERIED
										+ "")
						+ ",'"
						+ JSONHelper.getString(jsonObject,
								CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_MACHINE_ID
										+ "")
						+ "',"

						+ JSONHelper.getInt(jsonObject,
								CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_PRICE + "")

						+ ",'"
						+ JSONHelper.getString(
								jsonObject,
								CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_WORK_NUMBER1
										+ "")
						+ "',"

						+ "'"
						+ JSONHelper.getString(
								jsonObject,
								CONSTANTS.PRODUCT_SELL.COLUMN_INDEX_WORK_NUMBER2
										+ "") + "'";
				break;

			default:
				logger.error("Message unknow request type");
				return STATUS.CLIENT_SEND_UNKNOWN_TYPE;
		}
		if (columnSet != null && columnValue != null && tableName != null)
		{
			logger.info("Inserting....");
			isInsertSuccess = (timestamp != null) ? DatabaseManager.insert(
					tableName, columnSet, columnValue, timestamp) : DatabaseManager.insert(
					tableName, columnSet, columnValue);
		}

		if (!isCreateTableSuccess)
		{
			// logger.error("Insert fail!");
			return STATUS.DB_CREATE_FAIL;
		}
		if (!isInsertSuccess)
		{
			logger.error("Insert fail!");
			return STATUS.DB_INSERT_FAIL;
		}

		return STATUS.CLIENT_SEND_OK;
	}
}
