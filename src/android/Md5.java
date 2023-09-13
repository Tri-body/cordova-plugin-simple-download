package dev.tribody.simpledownloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {
    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String encryptMD5File2String(String str) {
        return encryptMD5File2String(new File(str));
    }

    public static String encryptMD5File2String(File file) {
        byte[] encryptMD5File = encryptMD5File(file);
        return encryptMD5File != null ? bytes2HexString(encryptMD5File) : "";
    }

    private static byte[] encryptMD5File(File file) {
        FileInputStream fileInputStream = null;
        try {
            try {
                fileInputStream = new FileInputStream(file);
                MappedByteBuffer map = fileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, file.length());
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(map);
                byte[] digest = messageDigest.digest();
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                    }
                }
                return digest;
            } catch (IOException e2) {
                e2.printStackTrace();
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                        return null;
                    } catch (IOException e3) {
                        return null;
                    }
                }
                return null;
            } catch (NoSuchAlgorithmException e4) {
                e4.printStackTrace();
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                        return null;
                    } catch (IOException e5) {
                        return null;
                    }
                }
                return null;
            }
        } catch (Throwable th) {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e6) {
                }
            }
            throw th;
        }
    }

    private static String bytes2HexString(byte[] bArr) {
        char[] cArr = new char[bArr.length << 1];
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = i;
            int i4 = i + 1;
            cArr[i3] = hexDigits[(bArr[i2] >>> 4) & 15];
            i = i4 + 1;
            cArr[i4] = hexDigits[bArr[i2] & 15];
        }
        return new String(cArr);
    }
}
