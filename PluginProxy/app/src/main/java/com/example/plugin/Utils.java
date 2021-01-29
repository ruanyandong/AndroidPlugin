package com.example.plugin;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Utils {

    /**
     * 将Assets目录下的fileName文件拷贝至app缓存目录下
     * @param context
     * @param fileName
     * getFileDir() ----- /data/data/(当前包名)/files
     * getCacheDir() ----- /data/data/（当前包名）/cache
     * @return
     */
    public static String copyAssetAndWrite(Context context,String fileName){
        try {
            File cacheDir = context.getCacheDir();
            if (!cacheDir.exists()){
                cacheDir.mkdirs();
            }
            File outFile = new File(cacheDir,fileName);
            if (!outFile.exists()){
                boolean res = outFile.createNewFile();
                if (res){
                    InputStream is = context.getAssets().open(fileName);
                    FileOutputStream fos = new FileOutputStream(outFile);
                    /**
                     *
                     public int available()
                     throws IOException
                     * 返回此输入流下一个方法调用可以不受阻塞地从此输入流读取（或跳过）的估计字节数。
                     * 下一个调用可能是同一个线程，也可能是另一个线程。
                     * 一次读取或跳过此估计数个字节不会受阻塞，但读取或跳过的字节数可能小于该数。
                     */
                    byte[] buffer = new byte[is.available()];
                    int byteCount;
                    while ((byteCount = is.read(buffer)) != -1){
                        fos.write(buffer,0,byteCount);
                    }
                    fos.flush();
                    is.close();
                    fos.close();
                    Toast.makeText(context,"下载成功",Toast.LENGTH_SHORT).show();
                    return outFile.getAbsolutePath();
                }
            }else {
                Toast.makeText(context,"文件已存在",Toast.LENGTH_SHORT).show();
                return outFile.getAbsolutePath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
