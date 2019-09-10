package com.aicp.icbc.webhook;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * @author: liuxincheng01
 * @description: faq批量测试--  导入问题  ---  导出问题&答案
 * @date：Created in 2019-08-24 15:04
 * @modified By liuxincheng01
 */
public class ReadAndWriteExcel {
    public static void main(String[] args) {
        InputStream is;
        try {
            //输入文件路径-数据源
            is = new FileInputStream("C:\\Users\\Mypc\\Desktop\\AicpInOut\\hhh.xls");
            HSSFWorkbook workbook = new HSSFWorkbook(is);
            HSSFSheet sheet = workbook.getSheetAt(0);
            sheet.setColumnWidth(2, 50 * 256);
            Row firstRow = sheet.getRow(0);
            Cell firstCell = firstRow.createCell(2);
            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            firstCell.setCellStyle(style);
            firstCell.setCellValue("答案");

            //
            for (int row = 1; row < sheet.getLastRowNum()+1; row++) {
                Row currentRow = sheet.getRow(row);
                Cell cell = currentRow.getCell(1);
                cell.setCellType(CellType.STRING);
                String cellValue = cell.getStringCellValue();
                System.out.println(cellValue);
                String resp = post(cellValue);
                JSONObject json = JSON.parseObject(resp);
                JSONObject data = (JSONObject) json.get("data");
                String suggestAnswer = data.getString("suggest_answer");

                Cell newCell = currentRow.createCell(2);
                newCell.setCellValue(suggestAnswer);
                if ("".equals(suggestAnswer) || suggestAnswer == null) {
                    JSONObject clarifyQuestions = (JSONObject) data.get("clarify_questions");
                    JSONObject voice = (JSONObject) clarifyQuestions.get("voice");
                    String content = voice.getString("content");
                    newCell.setCellValue(content);
                }
            }

            //输入文件路径
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Mypc\\Desktop\\AicpInOut\\ty.xls");
            workbook.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String post(String queryText) {
        String url = "https://api-aicp.baidu.com/api/v1/core/query?version=20170407";
        OkHttpClient client = new OkHttpClient();

        HttpUrl httpUrl = HttpUrl.parse(url).newBuilder()
                .addQueryParameter("version", "20170407")
                .build();
        String json = "{\"query_text\":\"" + queryText + "\",\"session_id\":\"\"}";

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        Request request = new Request
                .Builder()
                .post(body)
                .url(httpUrl)
                .addHeader("Authorization", "AICP dfcd5295-8c3a-4613-bba7-fc709c30d686")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = "";
        try {
            str = response.body().string();
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
