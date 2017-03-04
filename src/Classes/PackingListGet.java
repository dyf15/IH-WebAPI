/**
 * 作成者		:　戴
 * 作成日		:  2016/11/15
 * 学籍番号	:  45008
 * 内容		:  梱包一覧に必要な情報 このクラスによりJSONを生成
 */

package Classes;


public class PackingListGet
{
	//受注ID
	private String orderID;

	//商品ID
	private String productID;

	//商品名
	private String productName;

	//受注数量
	private String orderItemQuantity;

	//宛名
	private String addressee;

	//郵便番号
	private String postalCode;

	//住所
	private String addressFirst;
	
	private String addressEnd;

	
	
	/****************************************************************
	 * GetterとSetter
	 ****************************************************************/
	
	public String getOrderID()
	{
		return orderID;
	}

	public void setOrderID(String orderID)
	{
		this.orderID = orderID;
	}

	public String getProductID()
	{
		return productID;
	}

	public void setProductID(String productID)
	{
		this.productID = productID;
	}

	public String getProductName()
	{
		return productName;
	}

	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	public String getOrderItemQuantity()
	{
		return orderItemQuantity;
	}

	public void setOrderItemQuantity(String orderItemQuantity)
	{
		this.orderItemQuantity = orderItemQuantity;
	}

	public String getAddressee()
	{
		return addressee;
	}

	public void setAddressee(String addressee)
	{
		this.addressee = addressee;
	}

	public String getPostalCode()
	{
		return postalCode;
	}

	public void setPostalCode(String postalCode)
	{
		this.postalCode = postalCode;
	}

	public String getAddressFirst()
	{
		return addressFirst;
	}

	public void setAddressFirst(String addressFirst)
	{
		this.addressFirst = addressFirst;
	}

	public String getAddressEnd()
	{
		return addressEnd;
	}

	public void setAddressEnd(String addressEnd)
	{
		this.addressEnd = addressEnd;
	}

	
	
}