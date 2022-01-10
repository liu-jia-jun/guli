package EasyExcelTest;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author 刘佳俊
 */
//设置表头和添加的数据字
@Data
public class Student {
    //设置表头名称
    @ExcelProperty(value = "学生编号",index = 0)
    private int sno;

    //设置表头名称
    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;

}
