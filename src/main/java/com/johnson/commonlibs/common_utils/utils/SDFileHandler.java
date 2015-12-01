package com.johnson.commonlibs.common_utils.utils;

import java.io.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;

/**
 * SD卡的工具类
 */
public class SDFileHandler {

    private static String SDPath = Environment.getExternalStorageDirectory().getAbsolutePath();

    /**
     * 判断SD卡是否可用
     *
     * @return
     */
    public static boolean SDCanOperate() {
        // SD Card Ready

        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;

        }
    }

    /**
     * 获取程序私有路径
     *
     * @return
     */
    public static String getDataPath() {
        return "/data/data/com.lianbi.mezone.c";
    }

    /**
     * 判断文件路径是否在SD卡中
     *
     * @param filename
     * @return
     */
    public static boolean FileInSD(String filename) {
        return filename.startsWith(Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    /**
     * 获取SD卡根路径
     *
     * @return
     */
    public static String getSDPath() {
        return SDPath;
    }

    /**
     * @param path 文件夹路径 判断文件夹是否存在,如果不存在则创建文件夹
     */
    public static void isExist(String path) {
        File file = new File(path);
        // 判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            file.mkdir();
        }
    }

    /**
     * * 创建文件 * * @throws IOException
     */
    public static File creatFile(String fileName) throws IOException {
        File file = new File(fileName);
        file.createNewFile();
        return file;
    }

    /**
     * * 删除文件 * * @param fileName
     */
    public static boolean delFile(String fileName) {
        File file = new File(fileName);
        if (file == null || !file.exists() || file.isDirectory())
            return false;
        file.delete();
        return true;
    }

    /**
     * * 创建目录 * * @param dirName
     */
    public static File creatDir(String dirName) {
        File dir = new File(dirName);
        dir.mkdir();
        return dir;
    }

    /**
     * * 修改文件或目录名 * @param fileName
     */
    public boolean renameSDFile(String oldfileName, String newFileName) {
        File oleFile = new File(SDPath + "//" + oldfileName);
        File newnewFile = new File(SDPath + "//" + newFileName);
        return oleFile.renameTo(newnewFile);
    }

    /*
     * 删除一个文件 * * @param file * @return
     */
    public boolean delSDFile(File file) {
        if (file.isDirectory())
            return false;
        return file.delete();
    }

    /**
     * * 删除一个目录（可以是非空目录） * @param dir
     */
    public boolean delSDDir(File dir) {
        if (dir == null || !dir.exists() || dir.isFile()) {
            return false;
        }
        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                delSDDir(file);
                // 递归
            }
        }
        dir.delete();
        return true;
    }

    public static void writeFile(String fileName, byte[] data)
            throws IOException {
        // FileOutputStream fout = openFileOutput(fileName, MODE_PRIVATE);
        FileOutputStream fout = new FileOutputStream(fileName);
        fout.write(data);
        fout.close();
    }


    /**
     * 图片转Base64
     * @notice 已压缩bitmap
     * @param path
     * @return
     */
    public static String encodeBitmapToBase64(String path) {
        Bitmap bm = decodeBitmap(path);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        bm.recycle();
        return imageEncoded;
    }

    /**
     * decode bitmap在一个适合的范围内
     *
     * @param path
     * @return
     */
    private static Bitmap decodeBitmap(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        int inSampleSize = calculcateInSampleSize(options, 100, 100);
        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;
        Bitmap bm = BitmapFactory.decodeFile(path, options);
        return bm;
    }


    /**
     * 进行bitmap压缩
     *
     * @param options
     * @param requestWidth
     * @param requestHeight
     * @return
     */
    public static int calculcateInSampleSize(BitmapFactory.Options options, int requestWidth, int requestHeight) {
        // 压缩图片的宽和高
        int imageWidth = options.outWidth;
        int imageHeight = options.outHeight;

        int inSampleSize = 1;
        if (imageWidth > requestWidth || imageHeight > requestHeight) {
            int halfWidth = imageWidth / 2;
            int halfHeight = imageHeight / 2;

            while ((halfHeight / inSampleSize) > requestHeight
                    && (halfWidth / inSampleSize) > requestWidth) {
                inSampleSize *= 2;
            }

        }
        return inSampleSize;
    }
}
