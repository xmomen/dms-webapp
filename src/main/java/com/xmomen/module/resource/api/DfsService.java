package com.xmomen.module.resource.api;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface DfsService {

    /**
     * Upload file to file system.
     *
     * @param filePath local file path
     * @param key  Key
     * @param metadata  metadata for the file
     * @return DfsPath object or null if fail
     */
    public DfsPath putObject(String filePath, String key, Map<String, String> metadata);

    /**
     * Upload file to file system
     *
     * @param file  file
     * @param key Key
     * @param metadata metadata for the file
     * @return DfsPath object or null if fail
     */
    public DfsPath putObject(File file, String key, Map<String, String> metadata);

    /**
     * Upload file to file system
     *
     * @param bytes  file
     * @param key Key
     * @param metadata  metadata for the file
     * @param extName  ext name of file
     * @return DfsPath object or null if fail
     */
    public DfsPath putObject(byte[] bytes, String key, Map<String, String> metadata, String extName);

    /**
     * Get file from file system with remotePath
     *
     * @param remotePath  remote file path on file system
     * @param key Key
     * @return DfsPath object or null if fail
     */
    public DfsFile getObject(String remotePath, String key);

    /**
     * Delete the file on file system
     *
     * @param remotePath remote file path on file system
     * @param key Key
     * @return true||false
     */
    public boolean deleteObject(String remotePath, String key);

    /**
     * Get the list of the file on file system
     *
     * @return List<DfsFile>
     */
    public List<DfsFile> listObjects();

    /**
     * Get the whole url
     *
     * @param remotePath
     * @return whole url
     */
    public String getHttpPath(String remotePath);
    
    public String getDmsHttpPath(String dmsFilePath);
    
    public String getDefaultPath(String type);
}
