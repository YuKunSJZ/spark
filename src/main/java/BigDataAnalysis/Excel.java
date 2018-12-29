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
			String strText = "abc";// ���ü���


			// ��һ���ļ��ĸ���������ָ������д�ص�ԭ�ļ�
			WritableWorkbook book = Workbook.createWorkbook(new File("1.xls"));
			// ���һ��������
			WritableSheet sheet3 = book.getSheet(0);

			// ��õ�һ�����������

			// �õ���һ�е�һ�еĵ�Ԫ��
			// ���� sheet1.getRows();
			// ���� sheet1.getColumns();

			sheet3.addCell(new Label(1, 1, strText));// ���ö�Ӧ��Ԫ������

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