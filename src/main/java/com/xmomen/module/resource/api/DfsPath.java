package com.xmomen.module.resource.api;

public class DfsPath {

    /**
     * whole url, just like http://.../bucketName/remotePath
     */
    private String httpPath;

    /**
     * file path on storage server
     */
    private String remotePath;

    public DfsPath() {

    }

    /**
     * Constructor with httpPath and remotePath
     *
     * @param httpPath
     * @param remotePath
     */
    public DfsPath(String httpPath, String remotePath) {
        this.httpPath = httpPath;
        this.remotePath = remotePath;
    }

    public String getHttpPath() {
        return httpPath;
    }

    public void setHttpPath(String httpPath) {
        this.httpPath = httpPath;
    }

    public String getRemotePath() {
        return remotePath;
    }

    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }
}
