/**
 * 作成者		:　戴
 * 作成日		:  2016/11/15
 * 学籍番号	:  45008
 * 内容		:  検品商品一覧 このクラスによりJSON生成
 */
package Classes;

import DAO.DBAccess;

public class ProductListGet
{

	// 商品コード
	private String productID;

	// 商品名
	private String productName;

	// 数量
	private String productAllNum;

	// メーカーコード
	private String maker;

	// メーカー名
	private String makerName;

	// 商品の概要
	private String productSummary;
	
	//棚名
	private String rackName;
	
	//商品画像
	private String img;

	public String getImg()
	{
		return img;
	}

	public void setImg(String img)
	{
		this.img = img;
	}

	public String getRackName()
	{
		return rackName;
	}

	public void setRackName(String rackName)
	{
		this.rackName = rackName;
	}

	public String getMaker()
	{
		return maker;
	}

	public void setMaker(String maker)
	{
		this.maker = maker;
	}

	public String getMakerName()
	{
		return makerName;
	}

	public void setMakerName(String makerName)
	{
		this.makerName = makerName;
	}

	public String getProductSummary()
	{
		return productSummary;
	}

	public void setProductSummary(String productSummary)
	{
		this.productSummary = productSummary;
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

	public String getProductAllNum()
	{
		return productAllNum;
	}

	public void setProductAllNum(String productAllNum)
	{
		this.productAllNum = productAllNum;
	}

}
