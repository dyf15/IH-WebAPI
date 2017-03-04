/**
 * 作成者		:　戴
 * 作成日		:  2016/11/15
 * 学籍番号	:  45008
 * 内容		:  梱包詳細には必要な情報 このクラスによりJSONを生成
 */
package Classes;

public class PackingDetailGet
{
	//商品ID
	private String productID;
	
	//商品名
	private String productName;
	
	//受注数
	private String num;
	
	/****************************************************************
	 * GetterとSetter
	 ****************************************************************/
	
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
	public String getNum()
	{
		return num;
	}
	public void setNum(String num)
	{
		this.num = num;
	}
	
}
