package io.renren.jenkins;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.util.RequestReleasingInputStream;
import com.offbytwo.jenkins.model.*;
import io.renren.jenkins.entity.ProjectEntity;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Thread.sleep;

public class JenkinsDeployServer {

    private JenkinsServer server;
    private String curPath;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public JenkinsDeployServer(){
        try {
            this.server = new JenkinsServer(new URI("http://192.168.5.37:8081/jenkins/"),"chenlan","Sk.lan");

        } catch (URISyntaxException e) {  //
            e.printStackTrace(); //11a4f0d0e4ab4f3c0dd3297d92d813ef0f  http://localhost:8080/manage  http://192.168.4.37:8081//jenkins/   appl
        }
        this.curPath = getDicString();
    }

    public void deploys(List<ProjectEntity> projectEntityList){
 //       List<Thread> threadList = new ArrayList<>();
//        for (int i=0;i<projectEntityList.size();i++){
//           ProjectEntity projectEntity = projectEntityList.get(i);
//           Thread thread = new Thread(() -> runBuld(projectEntity));
//           thread.setName(projectEntity.getProjectName());
//           thread.start();
//           threadList.add(thread);
//        }

        for (ProjectEntity projectEntity :projectEntityList) {
            deploy(projectEntity);
//            Thread thread = new Thread(() -> deploy(projectEntity));
//            thread.setName(projectEntity.getProjectName());
//            threadList.add(thread);
//            thread.start();

        }


//        for (Thread thread:threadList) {
//            try {
//                thread.join();
//                logger.info("--------------------------执行完成："+thread.getName());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }


    }

    public void  runBuld(ProjectEntity projectEntity){
        try {
            for (int i = 0; i < 30; i++) {
                Thread.sleep(1000);
                logger.info(projectEntity.getProjectName()+"--->"+i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deploy(ProjectEntity projectEntity) {
        System.out.println("-------------------------------------------------------:"+projectEntity.getProjectName());
        Map<String,String> parameters = new HashMap<>();
        parameters.put("ENV",projectEntity.getEnv());
        parameters.put("Branch",projectEntity.getBranch());
        parameters.put("TestAuto",projectEntity.getIsAuto().toString());
        JobWithDetails job = null;
        try {
            job = this.server.getJob(projectEntity.getProjectName());
            //构建
            QueueReference queueReference = job.build(parameters,true);
//            deployWaitProgress(queueReference, server);

//            //获取构建ID
//            Long number = server.getQueueItem(queueReference).getExecutable().getNumber();
//            logger.info("this time build number is {}"+number);
//
//            //构建进度
//            Build build = job.details().getBuildByNumber(number.intValue());
//            BuildWithDetails buildWithDetails = build.details();
//            buildWithDetails = getBuildWithDetails(build, buildWithDetails);
//            logger.info("构建完成！！！"+buildWithDetails.getResult());
////            System.out.println("-------------------------------getResult状态："+buildWithDetails.getResult());
////            System.out.println("-------------------------------getActions状态："+buildWithDetails.getActions());
////            System.out.println("-------------------------------getDuration状态："+buildWithDetails.getDuration());
////            System.out.println("-------------------------------getTimestamp状态："+buildWithDetails.getTimestamp());
//            if(buildWithDetails.getResult()== BuildResult.FAILURE){
//                logger.info(buildWithDetails.getConsoleOutputText());
//            }
//
//            if(buildWithDetails.getResult()==BuildResult.SUCCESS){
//               // downloadWar(buildWithDetails, curPath);
//            }
            logger.info("");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void downloadWar(BuildWithDetails buildWithDetails, String curPath) throws IOException {
        logger.info("start 1------------");
        for (Artifact artifact : buildWithDetails.getArtifacts()){
            InputStream inputStream =null;
            FileOutputStream outputStream = null;
            try {
                logger.info("start 2------------");
                File targetFile = new File(curPath +"/"+artifact.getFileName());
                //inputStream = buildWithDetails.downloadArtifact(artifact);
                downloadre(targetFile,"http://192.168.5.37:8081/jenkins/job/app-ucc/82/artifact/app-ucc-web/target/app-ucc-web-2.1.4-SNAPSHOT.war");

                return;


                //inputStream =  buildWithDetails.downloadArtifact(artifact);
                //outputStream = new FileOutputStream(targetFile);
          //      IOUtils.copy(inputStream,outputStream,1024*1024);
            //    logger.info("start 222------------");
               // FileUtils.copyInputStreamToFile(inputStream, targetFile);
//                BufferedInputStream input = null;
//                BufferedOutputStream output = null;
//                input = new BufferedInputStream(inputStream);
//                output = new BufferedOutputStream(new FileOutputStream(targetFile));
//                int len = -1;
//                byte[] b = new byte[1024*1024*3];
//                while ((len=inputStream.read(b))!=-1){
//
//                    logger.info("读取流------："+len);
//                    outputStream.write(b,0,len);
//                    int alen = inputStream.available();
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

    private void downloadre(File outFile,String url){
        CloseableHttpClient client = HttpClients.createDefault();
        try (CloseableHttpResponse response = client.execute(new HttpGet(url))) {
            HttpEntity entity = response.getEntity();
            if(entity !=null){
                try (FileOutputStream outputStream = new FileOutputStream(outFile)) {
                    entity.writeTo(outputStream);
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private BuildWithDetails getBuildWithDetails(Build build, BuildWithDetails buildWithDetails) throws IOException {
        while (null == buildWithDetails.getResult()){
            try {
                sleep(1000);
//                logger.info("time isBuilding.....");
                buildWithDetails = build.details();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return buildWithDetails;
    }

    private void deployWaitProgress(QueueReference queueReference, JenkinsServer server) throws IOException {
        while (server.getQueueItem(queueReference).getExecutable()==null){
            try {
                sleep(1000);
                logger.info("time waiting.....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private String getDicString() {
        Date date = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmss");
        String path = "F:/downloadwar/"+ft.format(date);
        File file = new File(path);
        file.mkdir();
        return path;
    }

}
