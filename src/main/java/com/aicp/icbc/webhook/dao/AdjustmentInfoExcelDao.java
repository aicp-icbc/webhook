package com.aicp.icbc.webhook.dao;

import com.aicp.icbc.webhook.dto.AdjustmentInfoDto;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Description: 一键调额流程-ExcelDAO
 * @Author: 吴开云
 * @Date: 2019/8/19 0019
 * @Version： 1.0
 */
@Slf4j
@Component
public class AdjustmentInfoExcelDao {
    /**
     * 查询Excel文档中的全部信息
     * @return
     */
    public List<AdjustmentInfoDto> getAllInfoList() {
        List<AdjustmentInfoDto> infoDtoList = new ArrayList<>();
        //访问的classes-路径下的，Excel文件名
        String fileName = "ICBC-DATA.xlsx";
        //调用easyexcel 访问数据
        InputStream in = null;
        try {
            //获取输入流
//            in = this.getClass().getClassLoader().getResourceAsStream(fileName);
            in = new FileInputStream(new File(fileName));
            AnalysisEventListener<AdjustmentInfoDto> listener = new AnalysisEventListener<AdjustmentInfoDto>() {

                //访问，每一行数据
                @Override
                public void invoke(AdjustmentInfoDto object, AnalysisContext context) {
                    //System.err.println("Row:" + context.getCurrentRowNum() + "  Data:" + object);
//                    String regEx="[^0-9]";
//                    Pattern p = Pattern.compile(regEx);
                    if(object != null && (!StringUtils.isEmpty(object))){
                        //截取数字部分
//                        if(object.getQuotaMax().matches("[0-9]{1,}") ){
//                            object.setQuotaMax(p.matcher(object.getQuotaMax()).replaceAll("").trim());
//                        }
//                        if(object.getQuotaMin().matches("[0-9]{1,}") ){
//                            object.setQuotaMin(p.matcher(object.getQuotaMin()).replaceAll("").trim());
//                        }
//                        if(object.getQuotaMax().matches("[0-9]{1,}") ){
//                            object.setsDefineCredit(p.matcher(object.getQuotaMax()).replaceAll("").trim());
//                        }
                        infoDtoList.add(object);
                    }
                }
                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    log.info(fileName + "数据读取完毕..." + "共读取：" + infoDtoList.size() + "条数据");
                }
            };
            if(fileName.indexOf(".xlsx") > 0){
                //读取xlsx后缀的Excel内容
                ExcelReader excelReader = new ExcelReader(in, ExcelTypeEnum.XLSX, null, listener);
                // 第一个参数表示sheet页（第几页），第二个参数为表头行数，按照实际设置
                excelReader.read(new Sheet(7, 3, AdjustmentInfoDto.class));
            }else{
                //读取xls后缀的Excel内容
                ExcelReader excelReader = new ExcelReader(in, ExcelTypeEnum.XLS, null, listener);
                // 第一个参数表示sheet页（第几页），第二个参数为表头行数，按照实际设置
                excelReader.read(new Sheet(7, 3, AdjustmentInfoDto.class));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭输入流
                if(in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.error("找不到 " + fileName);
            }
        }

        return infoDtoList;
    }
}
