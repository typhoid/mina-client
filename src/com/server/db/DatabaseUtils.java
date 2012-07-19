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
				+ " int(10) unsigned NOT NULL AUTO_INCREMENT, PRIMARY KEY ("
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
				+ " int(10) unsigned NOT NULL AUTO_INCREMENT, PRIMARY KEY ("
				+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_ID + ")" + ", "
				+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_MACHINE_ID
				+ " varchar(50) NOT NULL, "
				+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_PRODUCT_CODE
				+ " varchar(50) NOT NULL, "
				+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_DATE
				+ " datetime NOT NULL, "
				+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_STAFF_SERVICE
				+ " varchar(50) NOT NULL, "
				+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_STAFF_CHECKER
				+ " varchar(50) NOT NULL, "
				+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_AMOUNT
				+ " int(11) unsigned NOT NULL, "
				+ CONSTANTS.PRODUCT_PUT_IN.COLUMN_BOX_CODE
				+ " int(10) unsigned NOT NULL";

		return DatabaseManager.createTable(CONSTANTS.PRODUCT_PUT_IN.TABLE_NAME, columnSet);
	}

	public static final boolean createTableProductTakeOut()
	{
		String columnSet =
		// "id INT UNSIGNED NOT NULL AUTO_INCREMENT, "
		// + "PRIMARY KEY (id), "
		CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_ID
				+ " int(10) unsigned NOT NULL AUTO_INCREMENT, PRIMARY KEY ("
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
				+ " int(10) unsigned NOT NULL, "
				+ CONSTANTS.PRODUCT_TAKE_OUT.COLUMN_BOX_CODE
				+ " int(10) unsigned NOT NULL";

		return DatabaseManager.createTable(CONSTANTS.PRODUCT_TAKE_OUT.TABLE_NAME, columnSet);
	}

	public static final boolean createTableBoxInventory()
	{
		String columnSet =
		// "id INT UNSIGNED NOT NULL AUTO_INCREMENT, "
		// + "PRIMARY KEY (id), "
		CONSTANTS.BOX_INVENTORY.COLUMN_ID
				+ " int(10) unsigned NOT NULL AUTO_INCREMENT, PRIMARY KEY ("
				+ CONSTANTS.BOX_INVENTORY.COLUMN_ID + ")" + ", "
				+ CONSTANTS.BOX_INVENTORY.COLUMN_MACHINE_ID
				+ " varchar(50) NOT NULL, "
				+ CONSTANTS.BOX_INVENTORY.COLUMN_PRODUCT_CODE
				+ " varchar(50) NOT NULL, "
				+ CONSTANTS.BOX_INVENTORY.COLUMN_AMOUNT
				+ " int(11) unsigned NOT NULL, "
				+ CONSTANTS.BOX_INVENTORY.COLUMN_BOX_CODE
				+ " int(11) unsigned NOT NULL";

		return DatabaseManager.createTable(CONSTANTS.PRODUCT_TAKE_OUT.TABLE_NAME, columnSet);
	}

	public static final boolean createTableProduct()
	{
		String columnSet =
		// "id INT UNSIGNED NOT NULL AUTO_INCREMENT, "
		// + "PRIMARY KEY (id), "
		CONSTANTS.PRODUCT.COLUMN_ID
				+ " int(10) unsigned NOT NULL AUTO_INCREMENT, PRIMARY KEY ("
				+ CONSTANTS.PRODUCT.COLUMN_ID + ")" + ", "
				+ CONSTANTS.PRODUCT.COLUMN_IMAGE_PATH
				+ " varchar(100) NOT NULL, "
				+ CONSTANTS.PRODUCT.COLUMN_PRICE_BUYING
				+ " int(11) DEFAULT NULL, "
				+ CONSTANTS.PRODUCT.COLUMN_PRICE_SELLING
				+ " int(11) DEFAULT NULL, "
				+ CONSTANTS.PRODUCT.COLUMN_PRODUCT_CODE
				+ " varchar(50) NOT NULL, "
				+ CONSTANTS.PRODUCT.COLUMN_PRODUCT_GROUP_ID
				+ " int(10) unsigned DEFAULT NULL, "
				+ CONSTANTS.PRODUCT.COLUMN_PRODUCT_NAME
				+ " varchar(50) NOT NULL, "
				+ CONSTANTS.PRODUCT.COLUMN_PRODUCT_SUPPLIER_ID
				+ " int(10) unsigned NOT NULL";

		return DatabaseManager.createTable(CONSTANTS.PRODUCT.TABLE_NAME, columnSet);
	}

	public static final boolean createTableAccount()
	{
		String columnSet =
		// "id INT UNSIGNED NOT NULL AUTO_INCREMENT, "
		// + "PRIMARY KEY (id), "
		CONSTANTS.ACCOUNT.COLUMN_ID
				+ " int(10) unsigned NOT NULL AUTO_INCREMENT, PRIMARY KEY ("
				+ CONSTANTS.ACCOUNT.COLUMN_ID + ")" + ", "
				+ CONSTANTS.ACCOUNT.COLUMN_ACCOUNT_AMOUNT
				+ " int(10) unsigned NOT NULL, "
				+ CONSTANTS.ACCOUNT.COLUMN_ACTIVE
				+ " tinyint(4) NOT NULL, "
				+ CONSTANTS.ACCOUNT.COLUMN_DATE_CREATED
				+ " datetime NOT NULL, "
				+ CONSTANTS.ACCOUNT.COLUMN_GROUP_ID
				+ " int(10) unsigned NOT NULL, "
				+ CONSTANTS.ACCOUNT.COLUMN_PASSWORD
				+ " varchar(100) COLLATE utf8_unicode_ci NOT NULL, "
				+ CONSTANTS.ACCOUNT.COLUMN_PIN_CODE
				+ " varchar(10) COLLATE utf8_unicode_ci NOT NULL, "
				+ CONSTANTS.ACCOUNT.COLUMN_POSITION_ID
				+ " int(10) unsigned NOT NULL, "
				+ CONSTANTS.ACCOUNT.COLUMN_STAFF_ID
				+ " varchar(50) COLLATE utf8_unicode_ci NOT NULL, "
				+ CONSTANTS.ACCOUNT.COLUMN_TERMINATED
				+ " tinyint(4) NOT NULL, "
				+ CONSTANTS.ACCOUNT.COLUMN_USER_NAME
				+ " varchar(100) COLLATE utf8_unicode_ci NOT NULL";

		return DatabaseManager.createTable(CONSTANTS.ACCOUNT.TABLE_NAME, columnSet);
	}

}
