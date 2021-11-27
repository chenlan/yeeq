package io.renren.jenkins;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.*;
import io.renren.jenkins.entity.ProjectEntity;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Thread.sleep;

public class JenkinsTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testm(){
        JenkinsDeployServer jenkinsDeployServer = new JenkinsDeployServer();
        List<ProjectEntity> projectEntities = new ArrayList<>();
        String projectsString = "app-ucc,origin/master,test,false;" +
                "app-user-center,origin/release/saas_test,test,false;";

        for (String projecString:projectsString.split(";")) {
            if(StringUtils.isNotBlank(projecString)){
                String[] parameter =projecString.split(",");
                ProjectEntity projectEntity = new ProjectEntity();
                projectEntity.setProjectName(parameter[0]);
                projectEntity.setBranch(parameter[1]);
                projectEntity.setEnv(parameter[2]);
                projectEntity.setIsAuto(Boolean.valueOf(parameter[3]));
                projectEntities.add(projectEntity);
            }
        }
        jenkinsDeployServer.deploys(projectEntities);
    }

    private String getDicString2() {
        Date date = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmss");
        String path = "F:/downloadwar/"+ft.format(date);
        File file = new File(path);
        file.mkdir();
        return path;
    }

    @Test
    public void dowloadHttp(){
        try {
            downloadFile("http://192.168.5.37:8081/jenkins/job/app-ucc/82/artifact/app-ucc-web/target/app-ucc-web-2.1.4-SNAPSHOT.war");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downloadFile(String serverUrl) throws Exception {
        String result;
        URL url = new URL(serverUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(3 * 1000);
        //防止屏蔽程序抓取而放回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0(compatible;MSIE 5.0;Windows NT;DigExt)");
        Long totalSize = Long.parseLong(conn.getHeaderField("Content-Length"));
        if (totalSize > 0) {
            File outputFile =new File(getDicString2()+"/app-ucc-web-2.1.4-SNAPSHOT.war");
            FileUtils.copyURLToFile(url, outputFile);
        } else {
            throw new Exception("can not find serverUrl :{}" + serverUrl);
        }
    }


    @Test
    public void dowloadTest(){
        InputStream inputStream =null;
        FileOutputStream outputStream = null;
        try {
            logger.info("start 2------------");
            File inputFile = new File( "F:/downloadwar/app-ucc-web-2.1.4-SNAPSHOT.war");
            File outputFile =new File(getDicString2()+"/app-ucc-web-2.1.4-SNAPSHOT.war");
            inputStream = new FileInputStream(inputFile);
            outputStream = new FileOutputStream(outputFile);
            IOUtils.copy(inputStream,outputStream,1024*1024);
            logger.info("start 222------------");
            // FileUtils.copyInputStreamToFile(inputStream, targetFile);
//                BufferedInputStream input = null;
//                BufferedOutputStream output = null;
//                input = new BufferedInputStream(inputStream);
//                output = new BufferedOutputStream(new FileOutputStream(targetFile));
//                int len = -1;
//                byte[] b = new byte[1024*1024*3];
//                while ((len=input.read(b))!=-1){
//                    output.write(b,0,len);
//                    output.flush();
//                }
//                logger.info("start 3------------");
//                input.close();
//                output.close();
        } catch (Exception e) {
            logger.info("start erro------------");
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }
    }





}
