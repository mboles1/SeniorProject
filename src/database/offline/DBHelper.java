package database.offline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper
{
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_LOGIN = "Login";
	public static final String COLUMN_PASS = "Pass";
	public static final String COLUMN_FIRST = "First_Name";
	public static final String COLUMN_LAST = "Last_Name";
	public static final String COLUMN_ADDRESS = "Address";
	public static final String COLUMN_CITY = "City";
	public static final String COLUMN_STATE = "State";
	public static final String COLUMN_ZIP = "Zip";
	public static final String COLUMN_PHONE = "Phone";
	public static final String COLUMN_EMAIL = "Email";
	public static final String COLUMN_ACC = "Account";
	public static final String COLUMN_ITEMDESC = "Item_Desc";
	public static final String COLUMN_ITEMYEAR = "Item_Year";
	public static final String COLUMN_ITEMMAKE = "Item_Make";
	public static final String COLUMN_ITEMMODEL = "Item_Model";
	public static final String COLUMN_VIN = "VIN";
	public static final String COLUMN_COLOR = "Color";
	public static final String COLUMN_PAMOUNT = "Coverage_Amount";
	public static final String COLUMN_PNUM = "Policy_Number";
	public static final String COLUMN_START = "Policy_Start";
	public static final String COLUMN_END = "Policy_End";
	
	private DatabaseHelper dbHelper;
	private SQLiteDatabase db;
	
	private static final String DATABASE_NAME = "insur_offline_db";
	private static final String CRED_TABLE = "user_creds";//Login
	private static final String ACCOUNT_TABLE = "account";//Account
	private static final String POLICY_TABLE = "policy";//Policy
	private static final String ITEM_TABLE = "item";//Item
	private static final int DATABASE_VERSION = 2;
	
	private final Context myContext;
	
	private static class DatabaseHelper extends SQLiteOpenHelper
	{

		public DatabaseHelper(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) 
		{
			db.execSQL("CREATE TABLE user_creds (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Login TEXT NOT NULL, Pass TEXT NOT NULL);");
			//Login Column will link creds with account
			db.execSQL("CREATE TABLE account (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Login, First_Name TEXT NOT NULL, Last_Name TEXT NOT NULL, Address "+
			"TEXT NOT NULL, City TEXT NOT NULL, State TEXT NOT NULL, Zip TEXT NOT NULL, Phone TEXT NOT NULL, Email TEXT NOT NULL, Account TEXT NOT NULL);");
			//Account Column will link account with policy
			db.execSQL("CREATE TABLE policy (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Account TEXT NOT NULL, Policy_Number TEXT NOT NULL, Policy_Start TEXT NOT NULL, " + 
			"Policy_End TEXT NOT NULL);");
			//Policy_Number Column will link policy with item
			db.execSQL("CREATE TABLE item (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Policy_Number TEXT NOT NULL, Item_Desc TEXT NOT NULL, Item_Year TEXT NOT NULL, " + 
			"Item_Make TEXT NOT NULL, Item_Model TEXT NOT NULL, VIN TEXT NOT NULL, Color TEXT NOT NULL);");
			
			db.execSQL("INSERT INTO user_creds (Login, Pass) VALUES('test','test');");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public DBHelper (Context context)
	{
		this.myContext = context;
	}
	
	public DBHelper open() throws SQLException
	{
		dbHelper = new DatabaseHelper(myContext);
		db = dbHelper.getWritableDatabase();
		return this;	
	}
	
	public void close()
	{
		dbHelper.close();
	}
	
	public long createLogin(String name, String pass)
	{
		ContentValues values = new ContentValues();
		
		values.put(COLUMN_LOGIN, name);
		values.put(COLUMN_PASS, pass);
		
		return db.insert(CRED_TABLE, null, values);
	}
	
	public long createAccount(String fName, String lName, String address, String city, String state, String zip, String phone, String email, String login, String account)
	{
		ContentValues values = new ContentValues();

		values.put(COLUMN_FIRST, fName);
		values.put(COLUMN_LAST, lName);
		values.put(COLUMN_ADDRESS, address);
		values.put(COLUMN_CITY, city);
		values.put(COLUMN_STATE, state);
		values.put(COLUMN_ZIP, zip);
		values.put(COLUMN_PHONE, phone);
		values.put(COLUMN_EMAIL, email);
		values.put(COLUMN_LOGIN, login);
		values.put(COLUMN_ACC, account);
		
		return db.insert(ACCOUNT_TABLE, null, values);
	}
	
	public long createPolicy(String account, String start, String end, String amount, String pNum)
	{
		ContentValues values = new ContentValues();
		
		values.put(COLUMN_ACC, account);
		values.put(COLUMN_START, start);
		values.put(COLUMN_END, end);
		values.put(COLUMN_PAMOUNT, amount);
		values.put(COLUMN_PNUM, pNum);
		
		return db.insert(POLICY_TABLE, null, values);
	}
	
	public long createItem(String desc, String year, String make, String model, String vin, String color, String pNum)
	{
		ContentValues values = new ContentValues();
		
		values.put(COLUMN_ITEMDESC, desc);
		values.put(COLUMN_ITEMYEAR, year);
		values.put(COLUMN_ITEMMAKE, make);
		values.put(COLUMN_ITEMMODEL, model);
		values.put(COLUMN_VIN, vin);
		values.put(COLUMN_COLOR, color);
		values.put(COLUMN_PNUM, pNum);
		
		return db.insert(ITEM_TABLE, null, values);
	}
	
	public boolean updateLogin(long rowID, String name, String pass)
	{
		ContentValues values = new ContentValues();
		
		values.put(COLUMN_LOGIN, name);
		values.put(COLUMN_PASS, pass);
		
		return db.update(CRED_TABLE, values, COLUMN_ID + "=\"" + rowID + "\"", null) > 0;
	}
	
	public boolean updateAccount(long rowID, String first, String last, String address, String city, String state, String zip, String phone, String email, String login, String account)
	{
		ContentValues values = new ContentValues();
		
		values.put(COLUMN_FIRST, first);
		values.put(COLUMN_LAST, last);
		values.put(COLUMN_ADDRESS, address);
		values.put(COLUMN_CITY, city);
		values.put(COLUMN_STATE, state);
		values.put(COLUMN_ZIP, zip);
		values.put(COLUMN_PHONE, phone);
		values.put(COLUMN_EMAIL, email);
		values.put(COLUMN_LOGIN, login);
		values.put(COLUMN_ACC, account);
		
		return db.update(ACCOUNT_TABLE, values, COLUMN_ID + "=\"" + rowID + "\"", null) > 0;
	}
	
	public boolean updatePolicy(long rowID, String account, String start, String end, String amount, String pNum)
	{
		ContentValues values = new ContentValues();
		
		values.put(COLUMN_ACC, account);
		values.put(COLUMN_START, start);
		values.put(COLUMN_END, end);
		values.put(COLUMN_PAMOUNT, amount);
		values.put(COLUMN_PNUM, pNum);
		
		return db.update(POLICY_TABLE, values, COLUMN_ID + "=\"" + rowID + "\"", null) > 0;
	}
	
	public boolean updateItem(long rowID, String desc, String year, String make, String model, String vin, String color, String pNum)
	{
		ContentValues values = new ContentValues();
		
		values.put(COLUMN_ITEMDESC, desc);
		values.put(COLUMN_ITEMYEAR, year);
		values.put(COLUMN_ITEMMAKE, make);
		values.put(COLUMN_ITEMMODEL, model);
		values.put(COLUMN_VIN, vin);
		values.put(COLUMN_COLOR, color);
		values.put(COLUMN_PNUM, pNum);
		
		return db.update(ITEM_TABLE, values, COLUMN_ID + "=\"" + rowID + "\"", null) > 0;		
	}
	
	public Cursor pullCreds(String login)
	{
		return db.query(CRED_TABLE, new String[] {COLUMN_LOGIN, COLUMN_PASS}, COLUMN_LOGIN + "=\"" + login + "\"", null, null, null, null);
	}
	
	public Cursor pullAccount(String login)
	{
		return db.query(ACCOUNT_TABLE, new String[] {COLUMN_FIRST, COLUMN_LAST, COLUMN_ADDRESS, COLUMN_CITY, COLUMN_STATE, COLUMN_ZIP, COLUMN_PHONE, COLUMN_EMAIL, COLUMN_LOGIN, COLUMN_ACC}, COLUMN_LOGIN + "=\"" + login + "\"", null, null, null, null);
	}
	
	public Cursor pullPolicy(String account)
	{
		return db.query(POLICY_TABLE, new String[] {COLUMN_ACC, COLUMN_START, COLUMN_END, COLUMN_PAMOUNT, COLUMN_PNUM}, COLUMN_ACC + "=\"" + account + "\"", null, null, null, null);
	}
	
	public Cursor pullItem(String policy)
	{
		return db.query(ITEM_TABLE, new String[] {COLUMN_ITEMDESC, COLUMN_ITEMYEAR, COLUMN_ITEMMAKE, COLUMN_ITEMMODEL, COLUMN_VIN, COLUMN_COLOR, COLUMN_PNUM}, COLUMN_PNUM + "=\"" + policy + "\"", null, null, null, null);
	}
	
	
	
	
	
	
	
}
