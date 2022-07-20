package com.UIautomation.service;


import com.UIautomation.utilities.globaldata.MyConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IOService {
    private static volatile IOService instance = null;
    private IOService() {

    }

    public static IOService getInit() {
        if (instance == null) {
            synchronized (IOService.class) {
                if (instance == null) {
                    instance = new IOService();
                }
            }
        }
        return instance;
    }

    public boolean createTextFile(String strPath, String strBody) {
        try {
            File file = new File(strPath);
            if (!file.exists())
                file.createNewFile();

            FileWriter fileWriter = new FileWriter(strPath, false);
            fileWriter.write(strBody);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void initFolder() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyHHmm");
        MyConfig.strPathFolderResultTesting = System.getProperty("user.dir") + "\\Results\\" + simpleDateFormat.format(new Date());
        MyConfig.strPathFolderResultCap = MyConfig.strPathFolderResultTesting + "\\CAP\\";

        File file = new File(MyConfig.strPathFolderResultTesting);
        if (!file.exists()) {
            file.mkdirs();
            System.out.println("Directory Result is created!");
        }

    }
}
