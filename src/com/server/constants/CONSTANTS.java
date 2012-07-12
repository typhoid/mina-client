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

		public static final int JSON_TYPE = 0;
		public static final int JSON_VALUE = JSON_TYPE + 1;
		
		public static final int TRANSACTION_CHANGE_PRICE = 0;
		public static final int TRANSACTION_PRODUCT_TAKE_OUT = 1;
		public static final int TRANSACTION_PRODUCT_PUT_IN = 2;
	}

	public final class PRODUCT_SELL
	{
		public static final String TABLE_NAME = "productsellout";

		public static final int COLUMN_INDEX_ID = 0;
		public static final int COLUMN_INDEX_CODE_BOX = COLUMN_INDEX_ID + 1;
		public static final int COLUMN_INDEX_CODE_PRODUCT = COLUMN_INDEX_CODE_BOX + 1;
		public static final int COLUMN_INDEX_ACOUNT_NUMBER = COLUMN_INDEX_CODE_PRODUCT + 1;
		public static final int COLUMN_INDEX_PRICE = COLUMN_INDEX_ACOUNT_NUMBER + 1;
		public static final int COLUMN_INDEX_IS_DELIVERIED = COLUMN_INDEX_PRICE + 1;
		public static final int COLUMN_INDEX_DATE = COLUMN_INDEX_IS_DELIVERIED + 1;
		public static final int COLUMN_INDEX_MACHINE_ID = COLUMN_INDEX_DATE + 1;
		public static final int COLUMN_INDEX_WORK_NUMBER1 = COLUMN_INDEX_MACHINE_ID + 1;
		public static final int COLUMN_INDEX_WORK_NUMBER2 = COLUMN_INDEX_WORK_NUMBER1 + 1;
		public static final int COLUMN_INDEX_AMOUNT = COLUMN_INDEX_WORK_NUMBER2 + 1;

		public static final String COLUMN_ID = "ProductSellOutMachineID";
		public static final String COLUMN_BOX_CODE = "VendingMachineBoxID";
		public static final String COLUMN_PRODUCT_CODE = "ProductCode";
		public static final String COLUMN_ACCOUNT_NUMBER = "sAccount";
		public static final String COLUMN_PRICE = "iUnitPrice";
		public static final String COLUMN_IS_DELIVERIED = "bDilivery";
		public static final String COLUMN_DATE = "dDateTimeSell";
		public static final String COLUMN_MACHINE_ID = "VendingMachineCode";
		public static final String COLUMN_WORK_NUMBER1 = "sWorkNumber";
		public static final String COLUMN_WORK_NUMBER2 = "sWorkNumber2";
		public static final String COLUMN_AMOUNT = "iQuantity";
	}

	public final class BOX_INVENTORY
	{
		public static final String TABLE_NAME = "productinventorybox";

		public static final int COLUMN_INDEX_ID = 0;
		public static final int COLUMN_INDEX_CODE_BOX = COLUMN_INDEX_ID + 1;
		public static final int COLUMN_INDEX_CODE_PRODUCT = COLUMN_INDEX_CODE_BOX + 1;
		public static final int COLUMN_INDEX_AMOUNT = COLUMN_INDEX_CODE_PRODUCT + 1;
		public static final int COLUMN_INDEX_MACHINE_ID = COLUMN_INDEX_AMOUNT + 1;

		public static final String COLUMN_ID = "ProductInventoryBoxMachineID";
		public static final String COLUMN_CODE_BOX = "VendingMachineBoxID";
		public static final String COLUMN_CODE_PRODUCT = "ProductCode";
		public static final String COLUMN_AMOUNT = "Quantity";
		public static final String COLUMN_MACHINE_ID = "VendingMachineCode";
	}

	public final class PRODUCT_TAKE_OUT
	{
		public static final String TABLE_NAME = "producttakeout";

		public static final int COLUMN_INDEX_ID = 0;
		public static final int COLUMN_INDEX_CODE_BOX = COLUMN_INDEX_ID + 1;
		public static final int COLUMN_INDEX_CODE_PRODUCT = COLUMN_INDEX_CODE_BOX + 1;
		public static final int COLUMN_INDEX_STAFF_SERVICE = COLUMN_INDEX_CODE_PRODUCT + 1;
		public static final int COLUMN_INDEX_STAFF_CHECKER = COLUMN_INDEX_STAFF_SERVICE + 1;
		public static final int COLUMN_INDEX_DATE = COLUMN_INDEX_STAFF_CHECKER + 1;
		public static final int COLUMN_INDEX_AMOUNT = COLUMN_INDEX_DATE + 1;
		public static final int COLUMN_INDEX_MACHINE_ID = COLUMN_INDEX_AMOUNT + 1;

		public static final String COLUMN_ID = "ProductTakeOutMachine";
		public static final String COLUMN_CODE_BOX = "VendingMachineBoxID";
		public static final String COLUMN_CODE_PRODUCT = "ProductCode";
		public static final String COLUMN_STAFF_SERVICE = "StaffTakeOutProduct";
		public static final String COLUMN_STAFF_CHECKER = "StaffChecker";
		public static final String COLUMN_DATE = "DateTime";
		public static final String COLUMN_AMOUNT = "iQuantity";
		public static final String COLUMN_MACHINE_ID = "VendingMachineCode";

		
	}
	public final class PRODUCT_PUT_IN
	{
		public static final String TABLE_NAME = "productputin";

		public static final int COLUMN_INDEX_ID = 0;
		public static final int COLUMN_INDEX_CODE_BOX = COLUMN_INDEX_ID + 1;
		public static final int COLUMN_INDEX_CODE_PRODUCT = COLUMN_INDEX_CODE_BOX + 1;
		public static final int COLUMN_INDEX_STAFF_SERVICE = COLUMN_INDEX_CODE_PRODUCT + 1;
		public static final int COLUMN_INDEX_STAFF_CHECKER = COLUMN_INDEX_STAFF_SERVICE + 1;
		public static final int COLUMN_INDEX_DATE = COLUMN_INDEX_STAFF_CHECKER + 1;
		public static final int COLUMN_INDEX_AMOUNT = COLUMN_INDEX_DATE + 1;
		public static final int COLUMN_INDEX_MACHINE_ID = COLUMN_INDEX_AMOUNT + 1;

		public static final String COLUMN_ID = "ProductPutInMachineID";
		public static final String COLUMN_CODE_BOX = "VendingMachineBoxID";
		public static final String COLUMN_CODE_PRODUCT = "ProductCode";
		public static final String COLUMN_STAFF_SERVICE = "StaffPutProduct";
		public static final String COLUMN_STAFF_CHECKER = "StaffChecker";
		public static final String COLUMN_DATE = "DateTime";
		public static final String COLUMN_AMOUNT = "iQuantity";
		public static final String COLUMN_MACHINE_ID = "VendingMachineCode";

		
	}

}
