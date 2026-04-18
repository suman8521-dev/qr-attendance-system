package com.suman.backend.utility;
import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.io.ByteArrayOutputStream;

public class QRUtil {

    public static byte[] generateQR(String url) throws Exception {
        BitMatrix matrix = new MultiFormatWriter()
                .encode(url, BarcodeFormat.QR_CODE, 300, 300);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", out);

        return out.toByteArray();
    }
}