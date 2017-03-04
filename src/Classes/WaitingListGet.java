/**
 * 作成者		:　戴
 * 作成日		:  2016/11/15
 * 学籍番号	:  45008
 * 内容		:　検品待ちに必要な情報 このクラスによりJSON生成
 * 
 */
package Classes;

public class WaitingListGet
{
	//受注ID
	private String orderID;
	
	//宛名
	private String addressee;

	//郵便番号
	private String postalCode;
	
	//市区町村
	private String addressFirst;
	
	//市区町村以下
	private String addressEnd;
	
	//検品開始日時
	private String inspectionStartDatetime;
	
	//倉庫ID
	private String repositoryID;
	
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

	public String getInspectionStartDatetime()
	{
		return inspectionStartDatetime;
	}

	public void setInspectionStartDatetime(String inspectionStartDatetime)
	{
		this.inspectionStartDatetime = inspectionStartDatetime;
	}

	public String getRepositoryID()
	{
		return repositoryID;
	}

	public void setRepositoryID(String repositoryID)
	{
		this.repositoryID = repositoryID;
	}
}
