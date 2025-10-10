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
        StringBuilder sb = new StringBuilder();

        try (InputStream fileContentB = filePart.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(fileContentB))) {
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }

        // 我的理解是把字符串转换成数字，而不是直接读取二进制数据
        int number = Integer.parseInt(sb.toString());

        StreamEntity streamEntity = new StreamEntity();
        streamEntity.setPrimeNumber(AlgorithmUtil.isPrimeNumber(number) ? "是" : "否");
        streamEntity.setFileContent(sb.toString());

        return streamEntity;
    }
}
