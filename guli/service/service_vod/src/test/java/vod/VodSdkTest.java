package vod;

import com.aliyun.oss.ClientException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import org.junit.Test;

import java.util.List;

/**
 * @author 刘佳俊
 */


public class VodSdkTest {

    String accessKeyId = "LTAI5tFna89PTaYf9iCeWbWy";
    String accessKeySecret = "9iwZ0H9A3gxqIQqbk178bEh6YZ0Vhy";
    /**
     * 获取视频播放地址
     * @throws ClientException
     */
    @Test
    public void testGetPlayInfo()   {

        //初始化客户端、请求对象和相应对象
        DefaultAcsClient client = null;
        try {
            client = AliyunVodSDKUtils.initVodClient(accessKeyId, accessKeySecret);
        } catch (com.aliyuncs.exceptions.ClientException e) {
            e.printStackTrace();
        }
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        try {

            //设置请求参数，参数为视频ID
            //注意：这里只能获取非加密视频的播放地址
            request.setVideoId("ce3ee4286b3a4f09923b52d2623d3b65");
            //获取请求响应
            response = client.getAcsResponse(request);

            //输出请求结果
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
            }
            //Base信息
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");

        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }

        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }

    /**
     * 获取视频播放凭证
     * @throws ClientException
     */
    @Test
    public void testGetVideoPlayAuth()  {

        //初始化客户端、请求对象和相应对象
        DefaultAcsClient client = null;
        try {
            client = AliyunVodSDKUtils.initVodClient(accessKeyId, accessKeySecret);
        } catch (com.aliyuncs.exceptions.ClientException e) {
            e.printStackTrace();
        }
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();

        try {

            //设置请求参数，参数为视频ID
            request.setVideoId("ce3ee4286b3a4f09923b52d2623d3b65");
            //获取请求响应
            response = client.getAcsResponse(request);

            //输出请求结果
            //播放凭证
            System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
            //VideoMeta信息
            System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }

        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }
}