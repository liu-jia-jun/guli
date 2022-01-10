package EasyExcelTest;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘佳俊
 */

public class EasyExcelTest {

    public static void main(String[] args) throws Exception {
read();
    }

   static void read(){        // 写法1：
        // 需要读取的excel文件的地址
        String fileName = "E:\\student.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, Student.class, new ExcelListener()).sheet().doRead();
    }

    void write(){
        // 写法1
        // 生成的excel文件的保存地址
        String fileName = "E:\\student.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, Student.class).sheet("写入方法一").doWrite(data());
    }

    //循环设置要添加的数据，最终封装到list集合中
    private static List<Student> data() {
        List<Student> list = new ArrayList<Student>();
        for (int i = 0; i < 10; i++) {
            Student data = new Student();
            data.setSno(i);
            data.setSname("张三"+i);
            list.add(data);
        }
        return list;
    }
}
