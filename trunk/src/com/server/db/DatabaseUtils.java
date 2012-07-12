package com.server.db;

import com.server.constants.CONSTANTS;

public final class DatabaseUtils
{
	public static final boolean createTableProductSell()
	{
		String columnSet =
		// "id INT UNSIGNED NOT NULL AUTO_INCREMENT, "
		// + "PRIMARY KEY (id), "
		CONSTANTS.PRODUCT_SELL.COLUMN_ID
				+ "  varchar(50) NOT NULL, PRIMARY KEY ("
				+ CONSTANTS.PRODUCT_SELL.COLUMN_ID + ")" + ", "
				+ CONSTANTS.PRODUCT_SELL.COLUMN_ACCOUNT_NUMBER
				+ " varchar(50) NOT NULL, "
				+ CONSTANTS.PRODUCT_SELL.COLUMN_AMOUNT
				+ " int(10) unsigned NOT NULL, "
				+ CONSTANTS.PRODUCT_SELL.COLUMN_BOX_CODE
				+ " int(10) unsigned NOT NULL, "
				+ CONSTANTS.PRODUCT_SELL.COLUMN_PRODUCT_CODE
				+ " varchar(50) NOT NULL, "
				+ CONSTANTS.PRODUCT_SELL.COLUMN_DATE + " datetime NOT NULL, "
				+ CONSTANTS.PRODUCT_SELL.COLUMN_IS_DELIVERIED
				+ " tinyint(1) DEFAULT NULL, "
				+ CONSTANTS.PRODUCT_SELL.COLUMN_MACHINE_ID
				+ " varchar(50) NOT NULL, "
				+ CONSTANTS.PRODUCT_SELL.COLUMN_PRICE
				+ " int(10) unsigned DEFAULT NULL, "
				+ CONSTANTS.PRODUCT_SELL.COLUMN_WORK_NUMBER1
				+ " varchar(100) DEFAULT NULL, "
				+ CONSTANTS.PRODUCT_SELL.COLUMN_WORK_NUMBER2
				+ " varchar(100) DEFAULT NULL";
		return DatabaseManager.createTable(CONSTANTS.PRODUCT_SELL.TABLE_NAME, columnSet);
	}

	public static final boolean createTableProductPutIn()
	{
		String columnSet =
		// "id INT UNSIGNED NOT NULL AUTO_INCREMENT, "
		// + "PRIMARY KEY (id), "
		CONSTANTS.PRODUCT_PUT_IN.COLUMN_ID
				+ "  varchar(20) NOT NULL, PRIMARY KEY ("
				+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_ID + ")" + ", "
				+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_MACHINE_ID
				+ " varchar(50) NOT NULL, "
				+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_CODE_PRODUCT
				+ " varchar(50) NOT NULL, "
				+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_DATE
				+ " varchar(50) NOT NULL, "
				+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_STAFF_SERVICE
				+ " varchar(50) NOT NULL, "
				+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_STAFF_CHECKER
				+ " varchar(50) NOT NULL, "
				+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_AMOUNT
				+ " int(11) unsigned NOT NULL, "
				+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_CODE_BOX
				+ " int(10) unsigned NOT NULL";

		return DatabaseManager.createTable(CONSTANTS.PRODUCT_PUT_IN.TABLE_NAME, columnSet);
	}

	public static final boolean createTableProductTakeOut()
	{
		String columnSet =
		// "id INT UNSIGNED NOT NULL AUTO_INCREMENT, "
		// + "PRIMARY KEY (id), "
		CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_ID
				+ "  varchar(20) NOT NULL, PRIMARY KEY ("
				+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_ID + ")" + ", "
				+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_MACHINE_ID
				+ " varchar(50) NOT NULL, "
				+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_CODE_PRODUCT
				+ " varchar(50) NOT NULL, "
				+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_DATE
				+ " datetime NOT NULL, "
				+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_STAFF_SERVICE
				+ " varchar(50) NOT NULL, "
				+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_STAFF_CHECKER
				+ " varchar(50) NOT NULL, "
				+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_AMOUNT
				+ " int(11) unsigned NOT NULL, "
				+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_CODE_BOX
				+ " int(10) unsigned NOT NULL";

		return DatabaseManager.createTable(CONSTANTS.PRODUCT_TAKE_OUT.TABLE_NAME, columnSet);
	}

}
