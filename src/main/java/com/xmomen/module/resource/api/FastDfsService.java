package com.xmomen.module.resource.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.csource.common.IniFileReader;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastDfsService implements DfsService {

    private static FastDfsService instance = null;

    private String baseUrl = "";
    private String dmsBaseUrl = "";
    private String defaultPicUrl = "";

    //private static Logger logger = Logger.getLogger(FastDfsService.class.getName());

    private static String BASE_URL = "httpBaseUrl";
    
    private static String DMS_BASE_URL = "dmsBaseUrl";
    
    private static String DEFAULT_PIC_URL = "defaultPicUrl";

    private static String FAST_DFS_KEY = "FastDfsKey";

    /**
     * bucket name
     */
    private final String bucketName;

    /**
     * constructor with bucketName
     *
     * @param bucketName bucket name
     */
    private FastDfsService(String bucketName) {
        this.bucketName = bucketName;
        String configFilePath = null;
		try {
			configFilePath = FastDfsService.class.getResource("/").toURI().getPath() + "fdfs_client.conf";
		} catch (URISyntaxException e1) {
			Exception e = new Exception("Cann't found fdfs_client.conf file under " + FastDfsService.class.getResource("/"));
			handleException(e);
		}
        try {
            ClientGlobal.init(configFilePath);
            IniFileReader reader = new IniFileReader(configFilePath);
            setBaseUrl(reader.getStrValue(BASE_URL));
            setDmsBaseUrl(reader.getStrValue(DMS_BASE_URL));
            setDefaultPicUrl(reader.getStrValue(DEFAULT_PIC_URL));
        } catch (Exception e) {
            handleException(e);
        }
    }

    /**
     * Get instance of FastDfsService, for Singleton
     * @param bucketName bucket name
     * @return instance of FastDfsService
     */
    public static synchronized FastDfsService getInstance(String bucketName) {
        if (null == instance) {
            instance = new FastDfsService(bucketName);
        }
        return instance;
    }

    /**
     * Delete file on file system.
     *
     * @param remotePath  remote file path
     * @param key Key, not used in FastDFS
     * @return true|false
     */
    @Override
    public boolean deleteObject(String remotePath, String key) {
        int result = 0;
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerClient.getConnection();
            StorageClient storageClient = new StorageClient(trackerServer, null);
            result = storageClient.delete_file(bucketName, remotePath);
        } catch(Exception e) {
            handleException(e);
        } finally {
            if (null != trackerServer) {
                try {
                    trackerServer.close();
                } catch (Exception e) {
                    handleException(e);
                }
            }
        }
        if (0 != result) {
            //logger.info("delete faild, the faild code is: " + result);
            return false;
        }
        return true;
    }

    /**
     * Find the whole url
     *
     * @param remotePath  remote path
     * @return the whole url
     */
    @Override
    public String getHttpPath(String remotePath) {
        return baseUrl + "/" + bucketName + "/" + remotePath;
    }

    @Override
    public String getDmsHttpPath(String dmsFilePath) {
    	return dmsBaseUrl + dmsFilePath;
    }
    /**
     * Get the file, and return DfsFile object
     *
     * @param remotePath remote path
     * @param key Key, not used in FastDFS, can be null
     * @return DfsFile object or null if fail
     */
    @Override
    public DfsFile getObject(String remotePath, String key) {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        try {
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getFetchStorage(trackerServer, bucketName, remotePath);
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            byte[] bytes = storageClient.download_file(this.bucketName, remotePath);
            if (null != bytes) {
                NameValuePair[] value_pairs = storageClient.get_metadata(bucketName, remotePath);
                Map<String, String> metadata = new HashMap<String, String>();
                for (int i = 0; i < value_pairs.length; i++) {
                    metadata.put(value_pairs[i].getName(), value_pairs[i].getValue());
                }
                DfsFile dfsFile = new DfsFile(metadata.get(FAST_DFS_KEY), bytes, metadata);
                return dfsFile;
            }
        } catch (Exception e){
            handleException(e);
        } finally {
            if (null != storageServer) {
                try {
                    storageServer.close();
                } catch (Exception e) {
                    handleException(e);
                }
            }
            if (null != trackerServer) {
                try {
                    trackerServer.close();
                } catch (Exception e) {
                    handleException(e);
                }
            }
        }
        //logger.info("Get object failed, get null object");
        return null;
    }

    /**
     * Get the file list of file system.
     * Not implement at the moment, because FastDFS API not support.
     */
    @Override
    public List<DfsFile> listObjects() {
        //to do
        //Because fastDFS api do not support list object method.
        return null;
    }

    /**
     * Upload the file to file system by file path
     *
     * @param filePath local file path.
     * @param key Key, not used in FastDFS, can be null
     * @param metadata  metadata for file, can be null
     * @return DfsFile object or null if fail
     */
    @Override
    public DfsPath putObject(String filePath, String key, Map<String, String> metadata) {
        File file = new File(filePath);
        return putObject(file, key, metadata);
    }

    /**
     * Upload the file to file system by bytes
     *
     * @param bytes bytes
     * @param key Key, not used in FastDFS
     * @param metadata metadata for file, can be null
     * @param extName extName for file, can be null
     * @return DfsPath object or null if fail
     */
    @Override
    public DfsPath putObject(byte[] bytes, String key, Map<String, String> metadata, String extName) {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        try {
            trackerServer = trackerClient.getConnection();
            StorageServer[] storageServers = trackerClient.getStoreStorages(trackerServer, bucketName);
            if (null != storageServers) {
                storageServer = storageServers[0];
                StorageClient storageClient = new StorageClient(trackerServer, storageServer);

                NameValuePair[] meta_list;
                int i = 0;
                if (null == metadata) {
                    meta_list = new NameValuePair[1];
                } else {
                    meta_list = new NameValuePair[metadata.size() + 1];
                    for (Map.Entry<String, String> entry : metadata.entrySet()) {
                        meta_list[i++] = new NameValuePair(entry.getKey(), entry.getValue());
                    }
                }
                meta_list[i] = new NameValuePair(FAST_DFS_KEY, key);

                String[] results = storageClient.upload_file(bytes, extName, meta_list);

                if (null == results) {
                    //logger.info("upload file fail, error codes: " + storageClient.getErrorCode());
                    return null;
                } else {
                    String remote_fileName = results[1];
                    String httpPath = this.getHttpPath(remote_fileName);
                    DfsPath dfsPath = new DfsPath(httpPath, remote_fileName);
                    return dfsPath;
                }
            }
        } catch (Exception e) {
            handleException(e);
        } finally {
            if (null != storageServer) {
                try {
                    storageServer.close();
                } catch (Exception e) {
                    handleException(e);
                }
            }
            if (null != trackerServer) {
                try {
                    trackerServer.close();
                } catch (Exception e) {
                    handleException(e);
                }
            }
        }
        //logger.info("Upload file faild, because can not get storage servers!");
        return null;

    }

    /**
     * Upload the file to file system
     *
     * @param file file
     * @param key  Key, not used in FastDFS, can be null
     * @param metadata  metadata for file , can be null
     * @return DfsPath object or null if fail
     */
    @Override
    public DfsPath putObject(File file, String key, Map<String, String> metadata) {
        FileInputStream in = null;
        byte[] file_buff = null;
        try {
            in = new FileInputStream(file);
            if (null != in) {
                int len = in.available();
                file_buff = new byte[len];
                in.read(file_buff);
            }
        } catch (Exception e) {
            handleException(e);
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (Exception e) {
                    handleException(e);
                }
            }
        }
        String file_ext_name = "";
        if (file.getName().lastIndexOf(".") > 0) {
            file_ext_name = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        }
        return putObject(file_buff, key, metadata, file_ext_name);
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    private void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getDmsBaseUrl() {
    	return dmsBaseUrl;
    }
    public void setDmsBaseUrl(String dmsBaseUrl) {
    	this.dmsBaseUrl = dmsBaseUrl;
    }
    
    public String getDefaultPicUrl() {
    	return defaultPicUrl;
    }
    public void setDefaultPicUrl(String defaultPicUrl) {
    	this.defaultPicUrl = defaultPicUrl;
    }
    
    /**
     * Handle Exception
     * @param e exception
     */
    private void handleException(Exception e) {
        if (e instanceof IOException) {
            //logger.error("Exception occured : DFSException code: 100,"  + " exception message :" + e.getMessage());
            throw new DfsException("100", e.getMessage());
        } else if (e instanceof FileNotFoundException) {
            //logger.error("Exception occured : DFSException code: 200,"  + " exception message : file not found." + e.getMessage());
            throw new DfsException("200", e.getMessage());
        } else if (e instanceof MyException) {
            //logger.error("Exception occured : DFSException code: 300,"  + " exception message :" + e.getMessage());
            throw new DfsException("300", e.getMessage());
        } else if (e instanceof Exception) {
            //logger.error("Exception occured : DFSException code: 400,"  + " exception message :" + e.getMessage());
            throw new DfsException("400", e.getMessage());
        }
    }

	@Override
	public String getDefaultPath(String type) {
		if("PICTURE".equalsIgnoreCase(type)) {
			return defaultPicUrl;
		}
		return defaultPicUrl;
	}
	
	


}
