/**
 * 作成者		:　戴
 * 作成日		:  2016/11/15
 * 学籍番号	:  45008
 * ***************************
 * 内容
 * 現在の日時を取得するクラス
 * 受注テーブルの(検品開始日時)
 * 出荷テーブルの(出荷完了日時)
 * 在庫テーブルの(確定日時)
 */
package Classes;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeGet
{

	Calendar calendar = Calendar.getInstance();
	
	//データベースに登録する日時format
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
	
	//現在の日時取得
	private String formatDate = format.format(calendar.getTime());

	/**
	 * 日時のformatを変換後
	 * @return　formatを変換後の日時
	 */
	public String getFormatDate()
	{
		return formatDate;
	}

}
