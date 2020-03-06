package com.erely.qr;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class QRCodeUtil {


    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\Administrator.000\\Desktop\\test\\test.txt");
        long fileLen = file.length();
        long count = 0;
        int index = 0;
        String fileName = file.getName();
        FileInputStream in = new FileInputStream(file);
        while (count < fileLen) if (fileLen - count > 1536) {
            byte[] b = new byte[1536];
            in.read(b);
            makeQr(b, index++, fileName);
            count += 1536;
        } else {
            byte[] b = new byte[(int) (fileLen - count)];
            in.read(b);
            makeQr(b, index++, fileName);
            count += fileLen - count;
        }
        //解码
        decode("C:\\Users\\Administrator.000\\Desktop\\test\\qr","C:\\Users\\Administrator.000\\Desktop\\test\\test1.txt");

    }

    private static void makeQr(byte[] b, int i, String fileName) throws UnsupportedEncodingException {

        String content = new String(b, "UTF-8");
        String destPath = "C:\\Users\\Administrator.000\\Desktop\\test\\qr\\" + fileName.split(".zip")[0] + String.format("%04d", i) + ".png";
        storeQr(content, destPath);

    }

    public static void storeQr(String content, String path) {
        // 定义要生成二维码的基本参数
        int width = 200;
        int height = 200;
        String type = "png";

        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            // 定义一个路径对象
            Path file = new File(path).toPath();
            // 生成二维码，传入二维码对象、生成图片的格式、生成的路径
            MatrixToImageWriter.writeToPath(bitMatrix, type, file);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void decode(String filepath,String destPath) throws NotFoundException, IOException {

        File file = new File(filepath);
        File[] files = file.listFiles();
        List<File> fileList = Arrays.asList(files);
        FileOutputStream out = new FileOutputStream(new File(destPath));
        fileList.forEach(file1 -> {
            BufferedImage image = null;
            try {
                image = ImageIO.read(file1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            BinaryBitmap bb = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            HashMap map = new HashMap();
            map.put(DecodeHintType.CHARACTER_SET, "utf-8");
            map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            map.put(EncodeHintType.MARGIN, 2);
            try {
                Result result = new MultiFormatReader().decode(bb, map);
                String content = result.getText();
                out.write(content.getBytes(StandardCharsets.UTF_8));
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        out.close();
        System.out.println("完成");
    }
}
