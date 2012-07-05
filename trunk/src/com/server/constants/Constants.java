package com.server.constants;

public final class CONSTANTS
{
	public final class TYPES
	{
		public static final int TYPE_NONE = 0;
		public static final int TYPE_SELL = TYPE_NONE + 1;
		public static final int TYPE_PRODUCT_PUTIN = TYPE_SELL + 1;
		public static final int TYPE_PRODUCT_TAKEOUT = TYPE_PRODUCT_PUTIN + 1;
		public static final int TYPE_BOX_INVENTORY = TYPE_PRODUCT_TAKEOUT + 1;
	}

	public final class PRODUCT_SELL
	{
		public static final String TABLE_NAME = "productsellout";

		public static final int COLUMN_INDEX_CODE_BOX = 0;
		public static final int COLUMN_INDEX_CODE_PRODUCT = COLUMN_INDEX_CODE_BOX + 1;
		public static final int COLUMN_INDEX_ACOUNT_NUMBER = COLUMN_INDEX_CODE_PRODUCT + 1;
		public static final int COLUMN_INDEX_PRICE = COLUMN_INDEX_ACOUNT_NUMBER + 1;
		public static final int COLUMN_INDEX_IS_DELIVERIED = COLUMN_INDEX_PRICE + 1;
		public static final int COLUMN_INDEX_DATE = COLUMN_INDEX_IS_DELIVERIED + 1;
		public static final int COLUMN_INDEX_MACHINE_ID = COLUMN_INDEX_DATE + 1;
		public static final int COLUMN_INDEX_MACHINE_NAME = COLUMN_INDEX_MACHINE_ID + 1;
		public static final int COLUMN_INDEX_WORK_NUMBER1 = COLUMN_INDEX_MACHINE_NAME + 1;
		public static final int COLUMN_INDEX_WORK_NUMBER2 = COLUMN_INDEX_WORK_NUMBER1 + 1;
		public static final int COLUMN_INDEX_AMOUNT = COLUMN_INDEX_WORK_NUMBER2 + 1;

		public static final String COLUMN_CODE_BOX = "code_box";
		public static final String COLUMN_CODE_PRODUCT = "sProductCode";
		public static final String COLUMN_ACCOUNT_NUMBER = "sAccount";
		public static final String COLUMN_PRICE = "iUnitPrice";
		public static final String COLUMN_IS_DELIVERIED = "bDilivery";
		public static final String COLUMN_DATE = "dDateTimeSell";
		public static final String COLUMN_MACHINE_ID = "sProductSellOutMachineID";
		public static final String COLUMN_MACHINE_NAME = "sVendingMachine";
		public static final String COLUMN_WORK_NUMBER1 = "sWorkNumber";
		public static final String COLUMN_WORK_NUMBER2 = "sWorkNumber2";
		public static final String COLUMN_AMOUNT = "iQuantity";
	}

	public final class BOX_INVENTORY
	{
		public static final String TABLE_NAME = "productinventorybox";

		public static final int COLUMN_INDEX_CODE_BOX = 0;
		public static final int COLUMN_INDEX_CODE_PRODUCT = COLUMN_INDEX_CODE_BOX + 1;
		public static final int COLUMN_INDEX_AMOUNT = COLUMN_INDEX_CODE_PRODUCT + 1;
		public static final int COLUMN_INDEX_MACHINE_ID = COLUMN_INDEX_AMOUNT + 1;
		public static final int COLUMN_INDEX_MACHINE_NAME = COLUMN_INDEX_MACHINE_ID + 1;

		public static final String COLUMN_CODE_BOX = "iVendingMachineBox";
		public static final String COLUMN_CODE_PRODUCT = "sProductCode";
		public static final String COLUMN_AMOUNT = "Quantity";
		public static final String COLUMN_MACHINE_ID = "ProductInventoryBoxMachineID";
		public static final String COLUMN_MACHINE_NAME = "sVendingMachineCode";
	}

	public final class PRODUCT_SERVICE
	{
		public static final String TABLE_NAME_TAKEOUT = "producttakeout";
		public static final String TABLE_NAME_PUTIN = "productputin";

		public static final int COLUMN_INDEX_CODE_BOX = 0;
		public static final int COLUMN_INDEX_CODE_PRODUCT = COLUMN_INDEX_CODE_BOX + 1;
		public static final int COLUMN_INDEX_STAFF_SERVICE = COLUMN_INDEX_CODE_PRODUCT + 1;
		public static final int COLUMN_INDEX_STAFF_CHECKER = COLUMN_INDEX_STAFF_SERVICE + 1;
		public static final int COLUMN_INDEX_PRICE = COLUMN_INDEX_STAFF_CHECKER + 1;
		public static final int COLUMN_INDEX_DATE = COLUMN_INDEX_PRICE + 1;
		public static final int COLUMN_INDEX_TRANSACTION = COLUMN_INDEX_DATE + 1;
		public static final int COLUMN_INDEX_AMOUNT = COLUMN_INDEX_TRANSACTION + 1;
		public static final int COLUMN_INDEX_MACHINE_ID = COLUMN_INDEX_AMOUNT + 1;
		public static final int COLUMN_INDEX_MACHINE_NAME = COLUMN_INDEX_MACHINE_ID + 1;

		public static final String COLUMN_CODE_BOX = "code_box";
		public static final String COLUMN_CODE_PRODUCT = "sProductCode";
		public static final String COLUMN_STAFF_SERVICE = "sStaffPutProduct";
		public static final String COLUMN_STAFF_CHECKER = "sStaffChecker";
		public static final String COLUMN_PRICE = "price";
		public static final String COLUMN_DATE = "dDateTime";
		public static final String COLUMN_TRANSACTION = "transaction";
		public static final String COLUMN_AMOUNT = "iQuantity";
		public static final String COLUMN_MACHINE_ID = "sProductPutInMachineID";
		public static final String COLUMN_MACHINE_NAME = "sVendingMachine";

		public static final int TRANSACTION_CHANGE_PRICE = 0;
		public static final int TRANSACTION_PRODUCT_TAKE_OUT = 1;
		public static final int TRANSACTION_PRODUCT_PUT_IN = 2;
	}

}
