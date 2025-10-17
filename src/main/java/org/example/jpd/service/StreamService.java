package org.example.jpd.service;

import jakarta.servlet.http.Part;
import org.example.jpd.common.util.AlgorithmUtil;
import org.example.jpd.entity.StreamEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamService {

    public StreamEntity parseFile(Part filePart) throws IOException {
        if(filePart.getSize() == 0) {
            throw new IOException("文件为空");
        }

        StringBuilder sb = new StringBuilder();

        try (InputStream fileContent = filePart.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(fileContent))) {
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }

        // 我的理解是把字符串转换成数字，而不是直接读取二进制数据
        String result;

        try {
            result = AlgorithmUtil.isPrimeNumber(Integer.parseInt(sb.toString())) ? "是" : "否";
        } catch (NumberFormatException e) {
            result = "文件内容不是整数";
        }

        StreamEntity streamEntity = new StreamEntity();
        streamEntity.setPrimeNumber(result);
        streamEntity.setFileContent(sb.toString());

        return streamEntity;
    }
}
