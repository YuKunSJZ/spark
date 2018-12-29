package BigDataAnalysis;

import java.io.File;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Excel {
    public static void main(String args[]) {
		try {
			long stime = System.currentTimeMillis();
			String strText = "abc";// 设置计数


			// 打开一个文件的副本，并且指定数据写回到原文件
			WritableWorkbook book = Workbook.createWorkbook(new File("1.xls"));
			// 添加一个工作表
			WritableSheet sheet3 = book.getSheet(0);

			// 获得第一个工作表对象

			// 得到第一列第一行的单元格
			// 行数 sheet1.getRows();
			// 列数 sheet1.getColumns();

			sheet3.addCell(new Label(1, 1, strText));// 设置对应单元格内容

			book.write();

			System.out.println(strText + "added!" );

			book.close();
			long etime = System.currentTimeMillis();
			System.out.println(etime - stime);
        } catch (Exception e) {
            System.out.println(e.toString());
            
        }
    }
}